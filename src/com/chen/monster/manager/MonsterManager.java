package com.chen.monster.manager;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.chen.map.manager.MapManager;
import com.chen.map.structs.Map;

/**
 * 怪物管理器
 * @author chen
 *
 */
public class MonsterManager 
{
	private Logger log = LogManager.getLogger(MonsterManager.class);
	//金钱怪
	public static final int Moster_Type_MapMonster = 4;
	//精英怪
	public static final int Monster_Type_Elite = 3;
	//普通怪
	public static final int Monster_Type_Normal = 1;
	
	private static Object obj = new Object();
	private static MonsterManager manager;
	private MonsterManager()
	{
		
	}
	public static MonsterManager getInstance()
	{
		synchronized (obj) 
		{
			if (manager == null)
			{
				manager = new MonsterManager();
			}
		}
		return manager;
	}
	
	public void initMonster(int serverId, int battleLineId, int mapId,int mapModelId)
	{
		Map map = MapManager.getInstance().getMap(serverId, battleLineId, mapId);
		if (map == null)
		{
			return ;
		}
		
	}
}
