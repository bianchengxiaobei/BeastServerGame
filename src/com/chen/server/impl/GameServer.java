package com.chen.server.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.future.WriteFuture;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;
import org.omg.CORBA.PUBLIC_MEMBER;

import com.chen.cache.executor.NonOrderedQueuePoolExecutor;
import com.chen.cache.executor.OrderedQueuePoolExecutor;
import com.chen.cache.structs.AbstractWork;
import com.chen.command.Handler;
import com.chen.match.manager.MatchManager;
import com.chen.message.Message;
import com.chen.message.MessagePool;
import com.chen.mina.IServer;
import com.chen.mina.impl.ClientServer;
import com.chen.player.manager.PlayerManager;
import com.chen.player.structs.Player;
import com.chen.server.config.GameConfig;
import com.chen.server.loader.GameConfigXmlLoader;
import com.chen.server.message.req.ReqRegisterCenterMessage;
import com.chen.server.message.req.ReqRegisterGateMessage;
import com.chen.server.thread.SchedularThread;
import com.chen.server.thread.ServerThread;

public class GameServer extends ClientServer
{
	private static Logger log = LogManager.getLogger(GameServer.class);
	private static Object obj = new Object();
	private static GameServer server;
	private static MessagePool messagePool = new MessagePool();
	private static GameConfig gameConfig;
	private static final String defaultGameConfig = "server-config/game-config.xml";
	private static final String defaultClientServerConfig = "server-config/client-server-config.xml";
	private static final String defaultPublicServerConfig = "server-config/public-server-config.xml";
	private OrderedQueuePoolExecutor decodeExecutor = new OrderedQueuePoolExecutor("网关消息解析队列",100,10000);
	private OrderedQueuePoolExecutor worldExecutor = new OrderedQueuePoolExecutor("世界消息解析队列",1,-1);
	private NonOrderedQueuePoolExecutor commandExecutor = new NonOrderedQueuePoolExecutor(100);
	private NonOrderedQueuePoolExecutor worldCommandExecutor = new NonOrderedQueuePoolExecutor(10);
	private ThreadGroup thread_group;
	private ServerThread wServerThread;
	private SchedularThread wSchedularThread;
	private boolean connectSuccess = false;
	public GameServer(String serverConfig)
	{
		super(serverConfig);
	}
	public GameServer()
	{
		this(defaultClientServerConfig);
	}
	public static GameServer getInstance()
	{
		synchronized (obj) 
		{
			if (server == null)
			{
				server = new GameServer();
				gameConfig = new GameConfigXmlLoader().load(defaultGameConfig);
			}
		}
		return server;
	}
	protected void init() 
	{
		super.init();
		//是否开启跨服
	}
	public void run() 
	{
		super.run();
		//内网定时发送
		new Timer("Inner-Send-Timer").schedule(new TimerTask() {	
			@Override
			public void run() 
			{
				List<IoSession> sessions = new ArrayList<IoSession>();
				synchronized (gateSessions) 
				{
					Iterator<List<IoSession>> iter = gateSessions.values().iterator();
					while (iter.hasNext())
					{
						List<IoSession> list = (List<IoSession>)iter.next();
						sessions.addAll(list);
					}
				}
				synchronized (centerSessions)
				{
					sessions.addAll(centerSessions);
				}
				for (IoSession ioSession : sessions)
				{
					IoBuffer sendBuf = null;
					synchronized (ioSession) 
					{
						if (ioSession.containsAttribute("send_buf"))
						{
							sendBuf = (IoBuffer)ioSession.getAttribute("send_buf");
							ioSession.removeAttribute("send_buf");
						}
					}
					try {
						if (sendBuf != null && sendBuf.position() > 0)
						{
							sendBuf.flip();
							WriteFuture wf = ioSession.write(sendBuf);
							wf.await();
						}
					} catch (Exception e) {
						continue;
					}
				}
			}
		},1,1);
		new Timer("Match Update").schedule(new TimerTask() {		
			@Override
			public void run() {
				MatchManager.getInstance().update();			
			}
		},1000,1000);
	}
	@Override
	public void doCommand(IoSession session, IoBuffer buf) 
	{
		try 
		{
			int id = buf.getInt();//消息id
			System.out.println("游戏服务器收到消息id"+id);
			long sessionId = buf.getLong();//客户端通信id号
			if (sessionId > 0)
			{
				decodeExecutor.addTask(sessionId, new Work(id, session, buf));
			}else{
				worldExecutor.addTask(0L, new Work(id, session, buf));
			}
		} 
		catch (Exception e) 
		{
			log.error(e,e);
		}		
	}
	private class Work extends AbstractWork
	{
		private int id;
		private IoSession iosession;
		private IoBuffer buf;
		public Work(int id,IoSession iosession,IoBuffer buf)
		{
			this.id = id;
			this.iosession = iosession;
			this.buf = buf;
		}
		@Override
		public void run() 
		{
			try 
			{
				//获取消息体
				Message msg = messagePool.getMessage(id);
				if (null == msg)
				{
					log.error("收到了不存在的消息："+id);
					return;
				}
				int roleNum = buf.getInt();//用户拥有的角色数量
				for (int i=0;i<roleNum;i++)
				{
					msg.getRoleId().add(buf.getLong());//取得用户所有角色的id，设置到消息中
				}
				msg.read(buf);
				msg.setSession(iosession);
				Handler handler = messagePool.getHandler(id);
				handler.setMessage(msg);
				handler.setCreateTime(System.currentTimeMillis());
				if ("Local".equalsIgnoreCase(msg.getQueue()))
				{
					commandExecutor.execute(handler);
				}else if ("Server".equalsIgnoreCase(msg.getQueue()))
				{
					Player player = null;
					if (msg.getRoleId().size()>0)
					{
						player = PlayerManager.getInstance().getPlayer(msg.getRoleId().get(0));
						if (player == null)
						{
							//服务器之间消息直接执行
							worldCommandExecutor.execute(handler);
							return;
						}
						handler.setParameter(player);
					}
				}
				else
				{
					Player player = null;
					if (msg.getRoleId().size()>0)
					{
						player = PlayerManager.getInstance().getPlayer(msg.getRoleId().get(0));
						if (player == null)
						{
							worldCommandExecutor.execute(handler);
							return;
						}
						handler.setParameter(player);
					}
				}
			}			
			catch (Exception e) 
			{
				log.error(e,e);
			}
		}
		
	}
	@Override
	public void exceptionCaught(IoSession arg0, Throwable arg1) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void sessionClosed(IoSession arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void sessionCreate(IoSession arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void sessionIdle(IoSession arg0, IdleStatus arg1) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void sessionOpened(IoSession arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	protected void connectComplete() {
		connectSuccess = true;	
	}
	@Override
	public void register(IoSession session, int type)
	{
		switch (type) {
		case IServer.GATE_SERVER:
			System.out.println("开始注册网关服务器");
			ReqRegisterGateMessage msg = new ReqRegisterGateMessage();
			msg.setServerId(this.getServer_id());
			msg.setServerName(this.getServer_name());
			session.write(msg);
			break;
		case IServer.WORLD_SERVER:
			System.out.println("开始注册世界服务器");
			ReqRegisterCenterMessage wmsg = new ReqRegisterCenterMessage();
			wmsg.setServerId(this.getServer_id());
			wmsg.setServerName(this.getServer_name());
			session.write(wmsg);
			break;
		default:
			break;
		}
	}
	@Override
	protected void stop() {
				
	}
}
