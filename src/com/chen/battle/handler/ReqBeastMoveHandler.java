package com.chen.battle.handler;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.chen.battle.manager.BattleManager;
import com.chen.battle.message.req.ReqBeastMoveMessage;
import com.chen.battle.structs.BattleContext;
import com.chen.command.Handler;
import com.chen.player.manager.PlayerManager;
import com.chen.player.structs.Player;

public class ReqBeastMoveHandler extends Handler
{
	private Logger log = LogManager.getLogger(ReqBeastMoveHandler.class);
	@Override
	public void action()
	{
		try {
			ReqBeastMoveMessage msg = (ReqBeastMoveMessage)getMessage();
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
			battle.beastMove(battle.getSSBeast(msg.beastId),msg.vec);
		} catch (Exception e) {
			this.log.error("神兽移动失败");
		}
		
	}

}
