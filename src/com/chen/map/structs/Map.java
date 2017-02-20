package com.chen.map.structs;

import java.util.HashMap;
import java.util.concurrent.atomic.AtomicInteger;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.chen.beast.struct.Beast;
import com.chen.object.GameObject;
import com.chen.player.structs.Player;

/**
 * 地图实体类
 * @author chen
 *
 */
public class Map extends GameObject
{
	private static final long serialVersionUID = -968366298217295113L;
	private static Logger log = LogManager.getLogger(Map.class);
	//玩家列表
	private static HashMap<Long, Player> players = new HashMap<Long, Player>();
	//神兽列表
	private static HashMap<Long,Beast> beasts = new HashMap<Long, Beast>();
	//地图模型id
	private int mapModelId;
	//分配的战斗id
	private int battleLineId;
	//所在服务器id
	private int serverId;
	//地图创建的时间
	private long createTime;
	
	private long sendId;
	
	public Map(int mapId)
	{
		this.id = mapId;
	}

	public static Logger getLog() {
		return log;
	}

	public static void setLog(Logger log) {
		Map.log = log;
	}

	public static HashMap<Long, Player> getPlayers() {
		return players;
	}

	public static void setPlayers(HashMap<Long, Player> players) {
		Map.players = players;
	}

	public static HashMap<Long, Beast> getBeasts() {
		return beasts;
	}

	public static void setBeasts(HashMap<Long, Beast> beasts) {
		Map.beasts = beasts;
	}

	public int getMapModelId() {
		return mapModelId;
	}

	public void setMapModelId(int mapModelId) {
		this.mapModelId = mapModelId;
	}

	public int getBattleLineId() {
		return battleLineId;
	}

	public void setBattleLineId(int battleLineId) {
		this.battleLineId = battleLineId;
	}

	public int getServerId() {
		return serverId;
	}

	public void setServerId(int serverId) {
		this.serverId = serverId;
	}

	public long getCreateTime() {
		return createTime;
	}

	public void setCreateTime(long createTime) {
		this.createTime = createTime;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public long getSendId() {
		return sendId;
	}

	public void setSendId(long sendId) {
		this.sendId = sendId;
	}
	
}
