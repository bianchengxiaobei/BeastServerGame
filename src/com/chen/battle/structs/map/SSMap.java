package com.chen.battle.structs.map;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map.Entry;
import java.util.Queue;

import com.chen.structs.CVector3;

public class SSMap
{
	private int mapId;
	private SSMapData mapData;
	private HashMap<Long, CVector3> mapBeastPos;
	private HashMap<Integer, HashMap<Integer, SSPathNode>> mapTablePathNode;
	
	public SSMap()
	{
		this.mapId = 0;
		this.mapData = null;
		this.setMapBeastPos(new HashMap<Long, CVector3>());
		this.mapTablePathNode = new HashMap<Integer, HashMap<Integer,SSPathNode>>();
	}
	
	public boolean Init(int mapId,String xmlConfigPath)
	{
		this.mapId = mapId;
		this.mapData = new SSMapData();
		this.mapData.Init(mapId, xmlConfigPath);
		this.OnInitFinished();
		return true;
	}
	
	public List<CVector3> FindPath(int nMaxDistance,CVector3 oSrcPos,CVector3 oTarPos,boolean bIgnoreHeroObstacle)
	{
		List<CVector3> oListPath = new ArrayList<CVector3>();
		if (nMaxDistance <= 0)
		{
			return null;
		}
		if (oSrcPos == oTarPos)
		{
			return null;
		}
		if (!this.mapTablePathNode.containsKey(oSrcPos.m_nX) || !this.mapTablePathNode.get(oSrcPos.m_nX).containsKey(oSrcPos.m_nY))
		{
			System.err.println("该地图没有源格子："+oSrcPos.toString());
			return null;
		}
		if (!this.mapTablePathNode.containsKey(oTarPos.m_nX) || !this.mapTablePathNode.get(oTarPos.m_nX).containsKey(oTarPos.m_nY))
		{
			System.err.println("该地图没有目的格子："+oTarPos.toString());
			return null;
		}
		SSPathNode srcPathNode = this.mapTablePathNode.get(oSrcPos.m_nX).get(oSrcPos.m_nY);
		if (srcPathNode == null)
		{
			System.err.println("SrcPathNode == null");
			return null;
		}
		SSPathNode desPathNode = this.mapTablePathNode.get(oTarPos.m_nX).get(oTarPos.m_nY);
		if (null == desPathNode || !desPathNode.bCanWalk)
		{
			System.err.println("DesPathNode == null");
			return null;
		}
		for (Entry<Integer, HashMap<Integer, SSPathNode>> current : this.mapTablePathNode.entrySet())
		{
			for (Entry<Integer, SSPathNode> current2 : current.getValue().entrySet())
			{
				current2.getValue().reset();
			}
		}
		srcPathNode.distance = 0;
		srcPathNode.maxDistance = 0;
		Queue<SSPathNode> queue = new LinkedList<SSPathNode>();
		queue.add(srcPathNode);
		oListPath.clear();
		int num = 0;
		while (queue.size() > 0)
		{
			SSPathNode firstPathNode = queue.peek();
			for (int i=0;i<6;i++)
			{
				SSPathNode nearPathNode = firstPathNode.listNextPathNode.get(i);
				if (nearPathNode != null && firstPathNode.parentNode != nearPathNode && (nearPathNode.sum == 0 || (nearPathNode.x == oTarPos.m_nX && nearPathNode.y == oTarPos.m_nY)))
				{
					if (nearPathNode.bCanWalk || (bIgnoreHeroObstacle && nearPathNode.beastId > 0))
					{
						int distance = firstPathNode.distance + 1;
						int maxDistance = firstPathNode.maxDistance + 1;
						if (distance <= nMaxDistance && maxDistance < nearPathNode.maxDistance)
						{
							nearPathNode.distance = distance;
							nearPathNode.maxDistance = maxDistance;
							nearPathNode.parentNode = firstPathNode;
							queue.add(nearPathNode);
						}	
					}
					num += 1;
				}
			}
			queue.poll();
		}
		SSPathNode endNode = desPathNode;
		if (endNode.distance <= nMaxDistance)
		{
			do
			{
				CVector3 cVector = new CVector3();
				cVector.m_nX = endNode.x;
				cVector.m_nY = endNode.y;
				cVector.m_nU = endNode.u;
				oListPath.add(cVector);
				endNode = endNode.parentNode;
			} while (endNode != null);
			return oListPath;
		}
		return null;
	}
	private void OnInitFinished()
	{
		for (Entry<Integer, HashMap<Integer, SSMapNode>> current : this.mapData.getMapData().entrySet())
		{
			HashMap<Integer, SSPathNode> pathNodes = new HashMap<Integer, SSPathNode>();
			for (Entry<Integer, SSMapNode> current2 : current.getValue().entrySet())
			{
				SSPathNode pathNode = new SSPathNode();
				pathNode.x = current2.getValue().getX();
				pathNode.y = current2.getValue().getY();
				pathNode.u = current2.getValue().getU();
				pathNode.bCanWalk = current2.getValue().bCanWalk;
				pathNodes.put(current2.getKey(), pathNode);
			}
			this.mapTablePathNode.put(current.getKey(), pathNodes);
		}
		for (Entry<Integer, HashMap<Integer, SSPathNode>> current : this.mapTablePathNode.entrySet())
		{
			for (Entry<Integer, SSPathNode> current2 : current.getValue().entrySet())
			{
				int x = current.getKey();
				int y = current2.getKey();
				if (this.mapData.hasMapNode(x+1, y+1))
				{
					current2.getValue().listNextPathNode.add(this.mapTablePathNode.get(x+1).get(y+1));
				}
				else
				{
					current2.getValue().listNextPathNode.add(null);
				}
				if (this.mapData.hasMapNode(x, y+1))
				{
					current2.getValue().listNextPathNode.add(this.mapTablePathNode.get(x).get(y+1));
				}
				else
				{
					current2.getValue().listNextPathNode.add(null);
				}
				if (this.mapData.hasMapNode(x-1, y))
				{
					current2.getValue().listNextPathNode.add(this.mapTablePathNode.get(x-1).get(y));
				}
				else
				{
					current2.getValue().listNextPathNode.add(null);
				}
				if (this.mapData.hasMapNode(x-1, y-1))
				{
					current2.getValue().listNextPathNode.add(this.mapTablePathNode.get(x-1).get(y-1));
				}
				else
				{
					current2.getValue().listNextPathNode.add(null);
				}
				if (this.mapData.hasMapNode(x, y-1))
				{
					current2.getValue().listNextPathNode.add(this.mapTablePathNode.get(x).get(y-1));
				}
				else
				{
					current2.getValue().listNextPathNode.add(null);
				}
				if (this.mapData.hasMapNode(x+1, y))
				{
					current2.getValue().listNextPathNode.add(this.mapTablePathNode.get(x+1).get(y));
				}
				else
				{
					current2.getValue().listNextPathNode.add(null);
				}
			}
			
		}
	}

	public HashMap<Long, CVector3> getMapBeastPos() {
		return mapBeastPos;
	}

	public void setMapBeastPos(HashMap<Long, CVector3> mapBeastPos) {
		this.mapBeastPos = mapBeastPos;
	}
}
