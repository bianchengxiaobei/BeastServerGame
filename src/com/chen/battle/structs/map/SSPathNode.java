package com.chen.battle.structs.map;

import java.util.ArrayList;
import java.util.List;

public class SSPathNode 
{
	public int x;
	public int y;
	public int u;
	public boolean bCanWalk;
	public int distance;
	public int maxDistance;
	public SSPathNode parentNode;
	public List<SSPathNode> listNextPathNode;
	public byte sum;
	public long beastId;
	
	public SSPathNode()
	{
		this.x = 0;
		this.y = 0;
		this.u = 0;
		bCanWalk = false;
		distance = 0;
		maxDistance = 0;
		sum = 0;
		this.listNextPathNode = new ArrayList<SSPathNode>();
	}
	public void reset()
	{
		this.distance = 1000;
		this.maxDistance = 10000;
		this.parentNode = null;
	}
	public String toString()
	{
		return String.format("PathNode : x=%d,y=%d,u=%d", x,y,u);
	}
}
