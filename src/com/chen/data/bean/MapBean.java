package com.chen.data.bean;

import java.util.ArrayList;
import java.util.List;

public class MapBean 
{
	//地图中文名
	private String m_strMapName;
	//地图编号
	private int m_nMapId;
	//该地图支持的战斗模式1-单人，2-双人，3-三人
	private String battleModel;
	//该地图最多玩家数量
	private int maxCount;
	public String getM_strMapName() {
		return m_strMapName;
	}
	public void setM_strMapName(String m_strMapName) {
		this.m_strMapName = m_strMapName;
	}
	public int getM_nMapId() {
		return m_nMapId;
	}
	public void setM_nMapId(int m_nMapId) {
		this.m_nMapId = m_nMapId;
	}
	public int getMaxCount() {
		return maxCount;
	}
	public void setMaxCount(int maxCount) {
		this.maxCount = maxCount;
	}
	public String getBattleModel() {
		return battleModel;
	}
	public List<Integer> getBattleModeString()
	{
		List<Integer> modeList = new ArrayList<Integer>();
		String[] modes = this.battleModel.split(","); 
		for (int i=0; i<modes.length; i++)
		{
			modeList.add(Integer.valueOf(modes[i]));
		}
		return modeList;
	}
	public void setBattleModel(String battleModel) {
		this.battleModel = battleModel;
	}
}
