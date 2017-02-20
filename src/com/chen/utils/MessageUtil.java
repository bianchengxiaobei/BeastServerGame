package com.chen.utils;

import java.nio.ByteOrder;
import java.util.Iterator;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;

import com.chen.battle.structs.BattleContext;
import com.chen.message.Message;
import com.chen.player.structs.Player;
import com.chen.server.impl.GameServer;

public class MessageUtil 
{
	private static Logger log = LogManager.getLogger(MessageUtil.class);
	/**
	 * 发送消息到网关服务器
	 * @param server
	 * @param id
	 * @param message
	 */
	public static void send_to_gate(int server,long id,Message message)
	{
		message.setSendId(id);
		List<IoSession> ioSessions = GameServer.getInstance().getGateSession().get(server);
		if (ioSessions != null)
		{
			write(ioSessions.get(0), message);
		}
		else
		{
			log.error("与网关服务器"+server+"通信session不存在！");
		}
	} 
	/**
	 * 发送给所有网关服务器
	 * @param message
	 */
	public static void send_to_gate(Message message)
	{
		Iterator<List<IoSession>> iter = GameServer.getInstance().getGateSession().values().iterator();
		while (iter.hasNext()) {
			List<IoSession> ioSessions = (List<IoSession>) iter.next();
			if (ioSessions.size() > 0)
			{
				write(ioSessions.get(0), message);
			}
		}
	}
	/**
	 * 发送消息给指定玩家
	 * @param player
	 * @param message
	 */
	public static void tell_player_message(Player player, Message message)
	{
		if (player.getGateId() != 0)
		{
			System.out.println("发送消息给客户端"+player.getId());
			message.getRoleId().add(player.getId());
			send_to_gate(player.getGateId(), player.getId(), message);
		}
		else
		{
			log.error("网关id为0");
		}
	}
	/**
	 * 发送消息给该战斗的所有玩家
	 * @param battle
	 * @param message
	 */
	public static void tell_battlePlayer_message(BattleContext battle,Message message)
	{
		for (int i=0; i<battle.getM_battleUserInfo().length; i++)
		{
			if (battle.getM_battleUserInfo()[i] != null)
			{
				message.getRoleId().add(battle.getM_battleUserInfo()[i].getPlayerId());
			}
		}
		send_to_gate(message);
		
	}
	/**
	 * 写入消息到游戏服务器缓存
	 */
	public static void write(IoSession session,Message message)
	{
		IoBuffer buf = IoBuffer.allocate(100);
		buf.setAutoExpand(true);
		buf.setAutoShrink(true);
		buf.order(ByteOrder.LITTLE_ENDIAN);
		buf.putInt(0);
		buf.putInt(message.getId());
		buf.putLong(message.getSendId());
		buf.putInt(message.getRoleId().size());
		for (int i=0;i<message.getRoleId().size();i++)
		{
			buf.putLong(message.getRoleId().get(i));
		}
		message.write(buf);
		message.getRoleId().clear();
		buf.flip();
		buf.putInt(buf.limit() - Integer.SIZE / Byte.SIZE);
		buf.rewind();
		IoBuffer sendbuf = null;
		synchronized (session) {
			if (session.containsAttribute("send_buf")) {
				sendbuf = (IoBuffer) session.getAttribute("send_buf");
				//session.removeAttribute("send_buf");
			} else {
				sendbuf = IoBuffer.allocate(1024);
				sendbuf.setAutoExpand(true);
				sendbuf.setAutoShrink(true);
				sendbuf.order(ByteOrder.LITTLE_ENDIAN);
				session.setAttribute("send_buf", sendbuf);
			}
			sendbuf.put(buf);
		}
	}
}
