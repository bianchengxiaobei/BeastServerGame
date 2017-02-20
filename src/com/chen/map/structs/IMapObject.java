package com.chen.map.structs;

import com.chen.structs.CVector3;

public interface IMapObject
{
	public long getId();
	public int getServerId();
	public int getMapId();
	public int getBattleLine();
	public CVector3 getCVector3();
}
