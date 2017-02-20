package com.chen.battle.handler;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.chen.battle.manager.BattleManager;
import com.chen.battle.message.req.ReqTrySelectBeastMessage;
import com.chen.battle.structs.BattleContext;
import com.chen.command.Handler;
import com.chen.player.manager.PlayerManager;
import com.chen.player.structs.Player;

public class ReqTrySelectBeastHandler extends Handler
{
	private Logger log = LogManager.getLogger(ReqTrySelectBeastHandler.class);
	@Override
	public void action() 
	{
		try {
			ReqTrySelectBeastMessage msg = (ReqTrySelectBeastMessage) this.getMessage();
			Player player = PlayerManager.getInstance().getPlayer(msg.getRoleId().get(0));
			if (player == null)
			{
				this.log.error("玩家还没有注册角色");
			}
			BattleContext battle = BattleManager.getInstance().getBattleContext(player);
			if (battle == null)
			{
				this.log.error("战场不存在");
			}
			battle.AskTrySelectBeast(player, msg.getBeastId(), msg.getBeastTypeId());
		} catch (Exception e) {
			this.log.error(e,e);
		}
	}	
}
