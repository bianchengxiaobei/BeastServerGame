package com.chen.data.container;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import com.chen.data.bean.MapBean;
import com.chen.data.dao.MapDao;

/**
 * 地图数据容器
 * @author Administrator
 *
 */
public class MapContainer 
{
	private List<MapBean> list;
	private HashMap<Integer, MapBean> map = new HashMap<Integer, MapBean>();
	private MapDao dao = new MapDao();
	public void load()
	{
		list = dao.select();
		Iterator<MapBean> iter = list.iterator();
		while (iter.hasNext())
		{
			MapBean bean = iter.next();
			map.put(bean.getM_nMapId(), bean);
		}
	}
	public List<MapBean> getList() {
		return list;
	}
	public HashMap<Integer, MapBean> getMap() {
		return map;
	}
	
}
