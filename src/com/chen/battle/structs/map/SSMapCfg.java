package com.chen.battle.structs.map;

import java.util.HashMap;

public class SSMapCfg
{
	private static HashMap<String, EMapNodeType> mapNodeType = new HashMap<String, EMapNodeType>();
	private static HashMap<String, Boolean> mapWalkType = new HashMap<String, Boolean>();
	private static HashMap<EMapNodeType, String> mapMapNodeType2String = new HashMap<EMapNodeType, String>();
	
	public static void register(String type,EMapNodeType eNodeType,boolean bWalkType)
	{
		SSMapCfg.mapNodeType.put(type, eNodeType);
		SSMapCfg.mapWalkType.put(type, bWalkType);
		SSMapCfg.mapMapNodeType2String.put(eNodeType, type);
	}
	public static boolean MapNodeTransfer(SSMapNode node)
	{
		if (!SSMapCfg.mapNodeType.containsKey(node.getStrType()))
		{
			System.err.println("找不到该类型的格子:"+node.getStrType());
			return false;
		}
		node.eMapNodeType = SSMapCfg.mapNodeType.get(node.getStrType());
		node.bCanWalk = SSMapCfg.mapWalkType.get(node.getStrType());
		return true;		
	}
	/**
	 * 字符串转成枚举地图节点类型
	 * @param type
	 * @return
	 */
	public static EMapNodeType String2MapNodeType(String type)
	{
		if (type == null || type.length() == 0)
		{
			return EMapNodeType.MAP_NODE_INVALID;
		}
		EMapNodeType result = EMapNodeType.MAP_NODE_INVALID;
		if (SSMapCfg.mapNodeType.containsKey(type))
		{
			result = SSMapCfg.mapNodeType.get(type);
			return result;
		}
		else
		{
			return result;
		}
	}
}
