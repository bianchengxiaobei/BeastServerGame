package com.chen.match.structs;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Vector;

import org.slf4j.impl.StaticLoggerBinder;

import com.chen.data.bean.MapBean;
import com.chen.data.manager.DataManager;
import com.chen.match.message.res.ResMatchStartMessage;
import com.chen.player.structs.EBattleState;
import com.chen.player.structs.EBattleType;
import com.chen.utils.MessageUtil;

public class MatchTeam 
{
	public static int TeamId = 0;
	private MapBean mapBean;
	private Vector<MatchPlayer> players = new Vector<MatchPlayer>();
	private int mapId;
	private int teamId;
	private EBattleMatchType matchType;
	private boolean isInMatch;
	private HashMap<Long, Boolean> stopedPlayers = new HashMap<Long, Boolean>();
	
	public MatchTeam(EBattleMatchType matchType,int mapId)
	{
		this.teamId = ++TeamId;
		this.matchType = matchType;
		this.mapId = mapId;
		this.mapBean = DataManager.getInstance().mapContainer.getList().get(mapId);
		this.isInMatch = false;
	}

	public MapBean getMapBean() {
		return mapBean;
	}

	public void setMapBean(MapBean mapBean) {
		this.mapBean = mapBean;
	}

	public Vector<MatchPlayer> getPlayers() {
		return players;
	}

	public void setPlayers(Vector<MatchPlayer> players) {
		this.players = players;
	}

	public int getMapId() {
		return mapId;
	}

	public void setMapId(int mapId) {
		this.mapId = mapId;
	}

	public int getTeamId() {
		return teamId;
	}

	public void setTeamId(int teamId) {
		this.teamId = teamId;
	}

	public EBattleMatchType getMatchType() {
		return matchType;
	}

	public void setMatchType(EBattleMatchType matchType) {
		this.matchType = matchType;
	}

	public boolean isInMatch() {
		return isInMatch;
	}

	public void setInMatch(boolean isInMatch) {
		this.isInMatch = isInMatch;
	}

	public HashMap<Long, Boolean> getStopedPlayers() {
		return stopedPlayers;
	}

	public void setStopedPlayers(HashMap<Long, Boolean> stopedPlayers) {
		this.stopedPlayers = stopedPlayers;
	}
	
	
	public boolean addOneUser(MatchPlayer player)
	{
		if (this.isInMatch)
		{
			return false;
		}
		boolean isOk = false;
		Iterator<Integer> iter = this.mapBean.getBattleModeString().iterator();
		while (iter.hasNext()) {
			Integer model = (Integer) iter.next();
			if (players.size() < model)
			{
				isOk = true;
				break;
			}			
		}
		if (isOk == false)
		{
			return false;
		}
		//发送给玩家的信息
		
		players.add(player);
		if (players.size() == 1)
		{
			player.setMonster(true);
		}
		player.getPlayer().getBattleInfo().changeTypeWithState(EBattleType.eBattleType_Match, EBattleState.eBattleState_Wait);
		return true;
	}
	/**
	 * 取得匹配队伍里面的玩家数量
	 * @return
	 */
	public int getPlayerCount()
	{
		return this.players.size();
	}
	/**
	 * 开始寻找队友和敌方对于
	 * @param isMatch
	 */
	public void search(boolean isMatch)
	{
		this.isInMatch = isMatch;
		//给客户端发送开始搜索队友的消息，和计时等待时间
		ResMatchStartMessage msg = new ResMatchStartMessage();
		msg.setM_reason(3);
		msg.setM_waitTime(90);
		for (int i=0; i<this.players.size(); i++)
		{
			MessageUtil.tell_player_message(this.players.get(i).getPlayer(),msg);
		}
	}
}
