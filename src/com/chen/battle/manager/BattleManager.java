package com.chen.battle.manager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.ibatis.javassist.expr.NewArray;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.chen.battle.impl.Battle;
import com.chen.battle.message.res.ResEnterRoomMessage;
import com.chen.battle.structs.BattleContext;
import com.chen.data.bean.MapBean;
import com.chen.data.manager.DataManager;
import com.chen.match.manager.MatchManager;
import com.chen.match.structs.EBattleMatchType;
import com.chen.match.structs.MatchPlayer;
import com.chen.match.structs.MatchTeam;
import com.chen.player.manager.PlayerManager;
import com.chen.player.structs.EBattleServerState;
import com.chen.player.structs.EBattleState;
import com.chen.player.structs.EBattleType;
import com.chen.player.structs.Player;
import com.chen.room.bean.RoomMemberData;
import com.chen.server.BattleServer;
import com.chen.server.config.BattleConfig;
import com.chen.utils.MessageUtil;

/**
 * 战斗管理器
 * @author Administrator
 *
 */
public class BattleManager 
{
	private Logger log = LogManager.getLogger(BattleManager.class);
	//战斗线程
	private ConcurrentHashMap<Long, BattleServer> mServers = new ConcurrentHashMap<Long, BattleServer>();
	private ConcurrentHashMap<Long, Battle> allBattleMap = new ConcurrentHashMap<Long, Battle>();
	private static Object obj = new Object();
	private static BattleManager manager;
	public static long battleId = 0;
	private BattleManager()
	{
		
	}
	public static BattleManager getInstance()
	{
		synchronized (obj)
		{
			if (manager == null)
			{
				manager = new BattleManager();
			}
		}
		return manager;
	}
	/**
	 * 用户请求创建匹配组队
	 * @param player
	 * @param mapId
	 * @param matchType
	 */
	public void askCreateMatchTeam(Player player,int mapId,byte matchType)
	{
		MatchTeam team = MatchManager.getInstance().UserCreateTeam(player.getMatchPlayer(),EBattleMatchType.values()[matchType], mapId);
		MapBean bean = DataManager.getInstance().mapContainer.getMap().get(mapId);
		if (bean == null)
		{
			log.error("不出在该地图");
			return ;
		}
		if (team == null)
		{
			log.error("创建的匹配组队不存在");
			return;
		}
		askStartMatch(player);
	}
	/**
	 * 玩家请求开始匹配
	 * @param player
	 */
	public void askStartMatch(Player player)
	{
		int nRet = MatchManager.getInstance().TeamStartMatch(player.getMatchPlayer());
		if (nRet == 0)
		{
			log.error("匹配开始失败："+player.getId());
		}
	}
	/**
	 * 匹配到队友的时候
	 * @param type
	 * @param mapId
	 * @param teamList
	 */
	public void onBattleMached(EBattleMatchType type,int mapId,HashMap<Integer, Vector<MatchTeam>> teamList)
	{
		HashMap<Integer, Player> userListMap = new HashMap<Integer, Player>();
		MatchTeam team = null;
		MatchPlayer player = null;
		for (int i=0;i<teamList.keySet().size();i++)
		{
			Iterator<MatchTeam> iter = teamList.get(i).iterator();
			int index = 0;
			while (iter.hasNext()) {
				team = (MatchTeam) iter.next();
				//停止搜索界面
				//matchTeam.search(false);
				Iterator<MatchPlayer> iterator = team.getPlayers().iterator();
				while (iterator.hasNext()) {
					player = iterator.next();
					userListMap.put(index++, player.getPlayer());
				}
			}
		}
		this.onBattleMached(userListMap, mapId, type);
	}
	public void onBattleMached(HashMap<Integer, Player> listMap,int mapId,EBattleMatchType type)
	{
		Battle battle = new Battle(type,EBattleType.eBattleType_Match,this.generateBattleId(),mapId,listMap);
		allBattleMap.put(battle.getBattleId(), battle);
		battle.start();
	}
	public void createBattle(ResEnterRoomMessage msg)
	{
		boolean isCreateSucc = false;
		BattleContext battle = null;
		if (msg == null)
		{
			log.error("进入战斗房间消息为空");
			return ;
		}
		if (this.mServers.get(msg.getBattleId()) != null)
		{
			log.error("已经存在该战斗，不需要重新创建");
			return ;
		}
		do 
		{
			List<BattleConfig> configs = new ArrayList<BattleConfig>();	
			battle = new BattleContext(EBattleType.values()[msg.getM_btGameType()],msg.getBattleId(),configs);
			mServers.put(battle.getBattleId(),battle);
			isCreateSucc = true;
		}while(false);
		if (isCreateSucc == false && battle != null)
		{
			battle = null;
			allBattleMap.remove(msg).getBattleId();
			return;
		}
		Battle cBattle = allBattleMap.get(msg.getBattleId());
		cBattle.onCreate();
		//发送进入房间的请求
		List<RoomMemberData> data1 = msg.getM_oEmpireMemberList();
		List<RoomMemberData> data2 = msg.getM_oLeagueMemberList();
		for (int i=0; i<data1.size(); i++)
		{
			Player player =  PlayerManager.getInstance().getPlayer(data1.get(i).getPlayerId());
			battle.getM_battleUserInfo()[i] = data1.get(i);
			MessageUtil.tell_player_message(player, msg);
		}
		for (int i=0; i<data2.size(); i++)
		{
			Player player =  PlayerManager.getInstance().getPlayer(data2.get(i).getPlayerId());
			battle.getM_battleUserInfo()[i+data1.size()] = data2.get(i);
			MessageUtil.tell_player_message(player, msg);
		}
		battle.setBattleState(EBattleServerState.eSSBS_SelectHero, false);
		new Thread(battle).start();
		//else
		//{
		//	new Thread(battle).start();
		//}
	}
	private long generateBattleId()
	{
		return ++battleId;		
	}
	public BattleContext getBattleContext(Player player)
	{
		if (player.getBattleInfo().getBattleState() != EBattleState.eBattleState_Play)
		{
			return null;
		}
		if (player.getBattleInfo().getBattleId() <= 0)
		{
			return null;
		}
		return (BattleContext) mServers.get(player.getBattleInfo().getBattleId());
	}
}
