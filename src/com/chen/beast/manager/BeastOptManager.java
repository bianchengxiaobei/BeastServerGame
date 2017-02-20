package com.chen.beast.manager;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.chen.beast.struct.Beast;
import com.chen.data.bean.BeastBean;
import com.chen.player.structs.Player;


/**
 * 神兽操作管理器
 * @author Administrator
 *
 */
public class BeastOptManager 
{
	private static Logger log = LogManager.getLogger(BeastOptManager.class);
	private static final BeastOptManager instance = new BeastOptManager();
	public static BeastOptManager getInstance()
	{
		return instance;
	}
	private BeastOptManager()
	{
		
	}
	/**
	 * 玩家是否拥有该神兽
	 * @param player
	 * @param beastId
	 * @return
	 */
	public boolean playerHasTheBeast(Player player,int beastId)
	{
		if (player != null && player.getBeastList() != null && player.getBeastList().size() > 0)
		{
			List<Beast> beastList = player.getBeastList();
			for (Beast beast : beastList)
			{
				if (beast.getBeastId() == beastId)
				{
					return true;
				}
			}
		}
		return false;
	}
	public void addBeast(Player player,int beastId,int beastTypeId)
	{
		BeastBean bean = null;
		if (bean == null)
		{
			log.error("未知神兽类型："+beastId);
		}
		if (playerHasTheBeast(player, beastId))
		{
			log.error("玩家已经拥有该神兽");
		}
		Beast beast = new Beast(player,beastId,beastTypeId);
		player.addBeast(beast);
		//发送消息给客户端，添加神兽的具体属性，然胡通知世界玩家新添加了神兽
	}
}
