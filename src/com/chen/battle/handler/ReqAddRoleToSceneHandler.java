package com.chen.battle.handler;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.chen.battle.manager.BattleManager;
import com.chen.battle.message.req.ReqAddRoleToSceneMessage;
import com.chen.battle.structs.BattleContext;
import com.chen.command.Handler;
import com.chen.player.manager.PlayerManager;
import com.chen.player.structs.Player;

public class ReqAddRoleToSceneHandler extends Handler
{
	private Logger log = LogManager.getLogger(ReqAddRoleToSceneHandler.class);
	@Override
	public void action() 
	{
		try {
			ReqAddRoleToSceneMessage msg = (ReqAddRoleToSceneMessage)getMessage();
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
			battle.BeastEnterBattle(battle.getSSBeast(msg.beastId),msg.pos);
		} catch (Exception e) {
			this.log.error("添加神兽到战场失败");
		}
		
	}

}
