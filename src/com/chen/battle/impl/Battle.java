package com.chen.battle.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.chen.battle.manager.BattleManager;
import com.chen.battle.message.res.ResEnterRoomMessage;
import com.chen.battle.structs.BattleContext;
import com.chen.data.bean.MapBean;
import com.chen.data.manager.DataManager;
import com.chen.match.structs.EBattleMatchType;
import com.chen.player.structs.EBattleState;
import com.chen.player.structs.EBattleType;
import com.chen.player.structs.Player;
import com.chen.room.bean.RoomMemberData;
import com.chen.room.structs.ECampType;

public class Battle 
{
	private Logger log = LogManager.getLogger(Battle.class);
	private long battleId;
	private int serverId;
	private int mapId;
	private EBattleType battleType;
	private EBattleMatchType matchType;
	private HashMap<Integer, Player> userMap;
	private MapBean mapBean;
	public long getBattleId() {
		return battleId;
	}
	public void setBattleId(long battleId) {
		this.battleId = battleId;
	}
	public int getMapId() {
		return mapId;
	}
	public void setMapId(int mapId) {
		this.mapId = mapId;
	}
	public int getServerId() {
		return serverId;
	}
	public void setServerId(int serverId) {
		this.serverId = serverId;
	}
	public EBattleType getBattleType() {
		return battleType;
	}
	public void setBattleType(EBattleType battleType) {
		this.battleType = battleType;
	}
	public EBattleMatchType getMatchType() {
		return matchType;
	}
	public void setMatchType(EBattleMatchType matchType) {
		this.matchType = matchType;
	}
	public HashMap<Integer, Player> getUserMap() {
		return userMap;
	}
	public void setUserMap(HashMap<Integer, Player> userMap) {
		this.userMap = userMap;
	}
	public MapBean getMapBean() {
		return mapBean;
	}
	public void setMapBean(MapBean mapBean) {
		this.mapBean = mapBean;
	}
	
	public Battle(EBattleMatchType match_type,EBattleType type,
			long battleId,int mapId,HashMap<Integer, Player> userList)
	{
		this.matchType = match_type;
		this.battleType = type;
		this.battleId = battleId;
		this.mapId = mapId;
		this.mapBean = DataManager.getInstance().mapContainer.getMap().get(mapId);
		//int levelMin = Integer.MAX_VALUE;
		//int levelMax = 0;
		//int pos = 0;
		log.info("开始创建战场："+this.battleId);
		userMap = userList;				
	}
	public void start()
	{
		System.out.println("Battle Start");
		Player player = null;
		if (battleType != EBattleType.eBattleType_Room)
		{
			ResEnterRoomMessage msg = new ResEnterRoomMessage();
			msg.setBattleId(battleId);
			msg.setM_nMapId(this.mapId);
			msg.setM_btGameType(this.matchType.getValue());
			msg.setM_nTimeLimit(BattleContext.timeLimit);
			List<RoomMemberData> list1 = new ArrayList<RoomMemberData>();
			List<RoomMemberData> list2 = new ArrayList<RoomMemberData>();
			for (int i=0; i<this.userMap.size(); i++)
			{
				player = userMap.get(i);
				player.getBattleInfo().setBattleState(EBattleState.eBattleState_Async);
				player.getBattleInfo().setBattleId(battleId);
				RoomMemberData data = new RoomMemberData();
				data.setPlayerId(player.getId());
				data.setName(player.getName());
				data.setLevel(player.getLevel());
				data.setIcon(player.getIcon());
				data.setBeastList(player.getBeastListLong());
				data.setTempBeastList(data.getCopyList(data.getBeastList()));
				data.setBeastMap(player.getBeastData());
				data.setIsReconnecting((byte)0);
				if (i < this.userMap.size() * 0.5)
				{
					data.setCampType(ECampType.Empire);
					list1.add(data);
				}else
				{
					data.setCampType(ECampType.Leagues);
					list2.add(data);
				}
			}
			msg.setM_oEmpireMemberList(list1);
			msg.setM_oLeagueMemberList(list2);
			BattleManager.getInstance().createBattle(msg);
		}
	}
	public void onCreate()
	{
		Iterator<Player> iter = this.userMap.values().iterator();
		while (iter.hasNext()) {
			Player player = (Player) iter.next();
			player.getBattleInfo().setBattleState(EBattleState.eBattleState_Play);
		}
	}
}
