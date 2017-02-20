package com.chen.battle.structs.map;

import com.chen.structs.Vector3;

public class MapNodeTypeInfo 
{
	public EMapNodeType MapNodeType;
	public Vector3 LocalPos;;
	public Vector3 LocalRotation;
	
	public MapNodeTypeInfo(EMapNodeType eMapNodeType)
	{
		this.MapNodeType = eMapNodeType;
	}
}
