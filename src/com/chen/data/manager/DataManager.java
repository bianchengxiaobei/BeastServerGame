package com.chen.data.manager;

import org.omg.CORBA.PUBLIC_MEMBER;

import com.chen.data.container.BeastContainer;
import com.chen.data.container.MapContainer;

/**
 * 数据加载类
 * @author chen
 *
 */
public class DataManager 
{
	public MapContainer mapContainer = new MapContainer();
	public BeastContainer beastContainer = new BeastContainer();
	private static Object obj = new Object();
	private static DataManager manager;
	private DataManager()
	{
		mapContainer.load();
		beastContainer.load();
	}
	public static DataManager getInstance()
	{
		synchronized (obj)
		{
			if (manager == null)
			{
				manager = new DataManager();
			}
		}
		return manager;
	}
}
