package com.chen.battle.structs.map;

import java.util.HashMap;

import com.chen.structs.Vector3;

public class SSMapData
{
	private int mapId;
	private HashMap<Integer, HashMap<Integer, SSMapNode>> mapMapData;
	private HashMap<EMapNodeType, MapNodeTypeInfo> mapMapNodeTypeInfo;
	public Vector3 InitPos;
	public Vector3 InitRotation;
	public SSMapData()
	{
		this.mapId = 0;
		this.setMapData(new HashMap<Integer, HashMap<Integer,SSMapNode>>());
		this.mapMapNodeTypeInfo = new HashMap<EMapNodeType, MapNodeTypeInfo>();
	}
	public void Init(int mapId,String configXmlPath)
	{
		this.mapId = mapId;
		if (configXmlPath == null || configXmlPath.length() == 0)
		{
			return;
		}
		SSMapConfigLoader loader = new SSMapConfigLoader();
		loader.Load(this, configXmlPath);
	}
	/**
	 * 是否存在这个地图格子
	 * @param x
	 * @param y
	 * @return
	 */
	public boolean hasMapNode(int x, int y)
	{
		return this.mapMapData.containsKey(x) && this.mapMapData.get(x).containsKey(y);
	}
	
	
	public HashMap<Integer, HashMap<Integer, SSMapNode>> getMapData() {
		return mapMapData;
	}

	public void setMapData(HashMap<Integer, HashMap<Integer, SSMapNode>> mapMapData) {
		this.mapMapData = mapMapData;
	}

	public HashMap<EMapNodeType, MapNodeTypeInfo> getMapNodeTypeInfo() {
		return mapMapNodeTypeInfo;
	}

	public void setMapNodeTypeInfo(HashMap<EMapNodeType, MapNodeTypeInfo> mapMapNodeTypeInfo) {
		this.mapMapNodeTypeInfo = mapMapNodeTypeInfo;
	}
}
