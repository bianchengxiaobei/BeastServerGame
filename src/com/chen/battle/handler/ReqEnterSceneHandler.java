package com.chen.battle.handler;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.chen.battle.manager.BattleManager;
import com.chen.battle.message.req.ReqEnterSceneMessage;
import com.chen.battle.structs.BattleContext;
import com.chen.command.Handler;
import com.chen.player.manager.PlayerManager;
import com.chen.player.structs.Player;

public class ReqEnterSceneHandler extends Handler
{
	private Logger log = LogManager.getLogger(ReqEnterSceneHandler.class);
	@Override
	public void action() 
	{
		try 
		{
			ReqEnterSceneMessage msg = (ReqEnterSceneMessage)getMessage();
			Player player = PlayerManager.getInstance().getPlayer(msg.getRoleId().get(0));
			if (player == null)
			{
				log.error("玩家没有注册角色");
				return;
			}
			BattleContext battle = BattleManager.getInstance().getBattleContext(player);
			if (battle == null)
			{
				log.error("该角色还没有战斗对决");
				return;
			}
			battle.EnsurePlayerLoaded(player.getId());
		} catch (Exception e)
		{
			log.error("玩家加载游戏场景失败");
		}		
	}
}
