package com.chen.match.structs;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Vector;

import com.chen.battle.manager.BattleManager;
import com.chen.data.bean.MapBean;
import com.chen.data.manager.DataManager;
import com.chen.player.structs.EBattleType;

public class MatchRoom_Normal 
{
	public MapBean getMapBean() {
		return mapBean;
	}

	public void setMapBean(MapBean mapBean) {
		this.mapBean = mapBean;
	}

	public int getUserCount() {
		return userCount;
	}

	public void setUserCount(int userCount) {
		this.userCount = userCount;
	}

	public int getRoomId() {
		return roomId;
	}

	public void setRoomId(int roomId) {
		this.roomId = roomId;
	}

	public boolean isInvalid() {
		return isInvalid;
	}

	public void setInvalid(boolean isInvalid) {
		this.isInvalid = isInvalid;
	}

	public HashMap<Integer, Vector<MatchTeam>> getTeamMap() {
		return teamMap;
	}

	public void setTeamMap(HashMap<Integer, Vector<MatchTeam>> teamMap) {
		this.teamMap = teamMap;
	}

	public static int RoomId = 0;
	//地图配置
	private MapBean mapBean;
	//玩家数量
	private int userCount;
	//房间id
	private int roomId;
	//是否有效，当没有玩家的时候设置为无效
	private boolean isInvalid = false;
	//队伍集合
	private HashMap<Integer, Vector<MatchTeam>> teamMap;
	
	public MatchRoom_Normal(int mapId)
	{
		this.roomId = ++RoomId;
		this.userCount = 0;
		teamMap = new HashMap<Integer, Vector<MatchTeam>>();
		mapBean = DataManager.getInstance().mapContainer.getMap().get(mapId);
		this.isInvalid = false;
	}
	
	public boolean addOneTeam(MatchTeam team)
	{
		if (isInvalid)
			return false;
		for (int i=0; i<this.mapBean.getBattleModeString().size();i++)
		{
			int curTeamSize = 0;
			Vector<MatchTeam> teams = this.teamMap.get(i);
			if (teams == null)
			{
				Vector<MatchTeam> myTeam = new Vector<MatchTeam>();
				myTeam.add(team);
				this.teamMap.put(i, myTeam);
				this.userCount += team.getPlayerCount();
				team.search(true);
				System.out.println("First");
				return true;
			}
			Iterator<MatchTeam> iter = teams.iterator();
			while (iter.hasNext()) {
				MatchTeam matchTeam = (MatchTeam) iter.next();
				curTeamSize += matchTeam.getPlayerCount();
			}
			if (curTeamSize+team.getPlayerCount() <= mapBean.getBattleModeString().get(i))
			{
				teams.add(team);
				this.userCount += team.getPlayerCount();
				//向客户端发送计时匹配界面开始
				team.search(true);
				//向客户端发送队友匹配数量变化的消息
				System.out.println("second");
				return true;
			}
		}
		return false;
	}
	public boolean update()
	{
		if (this.userCount == 0 || isInvalid)
			return true;
		if (userCount == mapBean.getMaxCount())
		{
			BattleManager.getInstance().onBattleMached(EBattleMatchType.MATCH_MODE_NORMAL, mapBean.getM_nMapId(), teamMap);
			return true;
		}
		return false;
	}
}





