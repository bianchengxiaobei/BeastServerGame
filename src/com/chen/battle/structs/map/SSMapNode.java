package com.chen.battle.structs.map;

import com.chen.structs.Vector3;

public class SSMapNode 
{
	public EMapNodeType eMapNodeType;
	public boolean bCanWalk;
	public Vector3 InitPos;
	private int nIndexX;
	private int nIndexY;
	private int nIndexU;
	private String strType;
	private String strName;
	
	public SSMapNode()
	{
		this.nIndexX = 0;
		this.nIndexY = 0;
		this.nIndexU = 0;
		this.eMapNodeType = EMapNodeType.MAP_NODE_INVALID;
		this.bCanWalk = false;
	}
	public int getX()
	{
		return this.nIndexX;
	}
	public void setX(int x)
	{
		this.nIndexX = x;
	}
	public int getY()
	{
		return this.nIndexY;
	}
	public void setY(int y)
	{
		this.nIndexY = y;
	}
	public int getU()
	{
		return this.nIndexU;
	}
	public void setU(int u)
	{
		this.nIndexU = u;
	}
	public String getStrType() {
		return strType;
	}
	public void setStrType(String strType) {
		this.strType = strType;
	}
	public String getStrName() {
		return strName;
	}
	public void setStrName(String strName) {
		this.strName = strName;
	}
	public int getCol()
	{
		return this.nIndexX;
	}
	public int getRow()
	{
		return this.nIndexU;
	}
	@Override
	public String toString()
	{
		return String.format("x=%d,y=%d,u=%d", this.nIndexX,this.nIndexY,this.nIndexU);
	}
}
