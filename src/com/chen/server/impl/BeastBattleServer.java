package com.chen.server.impl;

import java.util.List;

import com.chen.map.manager.MapManager;
import com.chen.server.BattleServer;
import com.chen.server.config.BattleConfig;

public class BeastBattleServer extends BattleServer
{
	private long createTime;
	private boolean delete = false;
	
	public BeastBattleServer(String name,List<BattleConfig> battleConfigs)
	{
		super(name, battleConfigs);
		this.createTime = System.currentTimeMillis();
	}

	@Override
	protected void init() 
	{
		for (int i=0; i < this.getBattleConfig().size(); i++)
		{
			BattleConfig config = this.getBattleConfig().get(i);
			//初始化地图，包括地图阻塞坐标
			config.setMapId(MapManager.getInstance().initMaps(config.getServerId(),0 , config.getMapId()));
		}
	}
}
