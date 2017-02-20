package com.chen.map.manager;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.chen.data.bean.MapBean;
import com.chen.data.manager.DataManager;
import com.chen.map.structs.Map;

/**
 * 地图管理器
 * @author chen
 *
 */
public class MapManager 
{
	private Logger log = LogManager.getLogger(MapManager.class);
	private static Object obj = new Object();

	private static MapManager manager;
	//地图数据缓存(key=>服务器编号+"_"+地图编号)
	private static ConcurrentHashMap<String, Map> mapping = new ConcurrentHashMap<String, Map>();
	
	private AtomicInteger mapSendId = new AtomicInteger(0);
	
	private MapManager()
	{
		
	}
	public static MapManager getInstance() {
		synchronized (obj) {
			if (manager == null) {
				manager = new MapManager();
			}
		}
		return manager;
	}
	//初始化地图
	public int initMaps(int serverId,int battleLine,int mapModelId)
	{
		//取得该地图的数据信息
		MapBean bean = DataManager.getInstance().mapContainer.getMap().get(mapModelId);
		//初始化地图
		Map map = new Map(bean.getM_nMapId());
		map.setCreateTime(System.currentTimeMillis());
		map.setServerId(serverId);
		map.setBattleLineId(battleLine);
		map.setSendId(mapSendId.getAndIncrement());
		
		mapping.put(serverId+"_"+battleLine+"_"+(int)map.getId(), map);
		
		log.debug("创建地图"+map.getId()+",地图数量："+mapping.size());
		return (int)map.getId();
	}
	/**
	 * 获取map实体
	 * @param serverId
	 * @param battleId
	 * @param mapId
	 * @return
	 */
	public Map getMap(int serverId, int battleId, int mapId)
	{
		return mapping.get(serverId+"_"+battleId+"_"+mapId);
	}
}
