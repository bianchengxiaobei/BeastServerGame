package com.chen.login.handler;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.chen.command.Handler;
import com.chen.login.bean.RoleAllInfo;
import com.chen.login.message.req.ReqLoginCharacterToGameServerMessage;
import com.chen.login.message.res.ResLoginSuccessToGateMessage;
import com.chen.player.manager.PlayerManager;
import com.chen.player.structs.Player;
import com.chen.server.impl.GameServer;
import com.chen.utils.MessageUtil;

public class ReqLoginCharacterToGameServerHandler extends Handler
{
	private Logger log = LogManager.getLogger(ReqLoginCharacterToGameServerHandler.class);

	@Override
	public void action()
	{
		ReqLoginCharacterToGameServerMessage msg = (ReqLoginCharacterToGameServerMessage)this.getMessage();
		try {
			Player player = null;
			int serverId = GameServer.getInstance().getServer_id();
			player = PlayerManager.getInstance().getPlayer(msg.getPlayerId());
			if (player == null)
			{
				player = PlayerManager.getInstance().loadPlayer(msg.getPlayerId());
				if (player == null)
				{
					//选择角色失败
					return;
				}
			}
			player.setGateId(msg.getGateId());
			if (msg.getLoginIp() == null || msg.getLoginIp().length() == 0)
			{
				log.error("错误IP");
			}
			player.setId(msg.getPlayerId());
			player.setLoginIp(msg.getLoginIp());
			player.setLoginType(msg.getLoginType());
			player.setUserName(msg.getUserName());
			player.setServerName(GameServer.getInstance().getServer_name());
			player.setWebName(GameServer.getInstance().getServer_web());
			PlayerManager.getInstance().registerPlayer(player);
			ResLoginSuccessToGateMessage gate_msg = new ResLoginSuccessToGateMessage();
			gate_msg.setServerId(serverId);
			gate_msg.setCreateServerId(player.getCreateServerId());
			gate_msg.setUserId(msg.getUserId());
			gate_msg.setPlayerId(player.getId());
			gate_msg.setRoleAllInfo(getRoleAllInfo(player));
			MessageUtil.send_to_gate(msg.getGateId(), player.getId(), gate_msg);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	private RoleAllInfo getRoleAllInfo(Player player)
	{
		RoleAllInfo allinfo = new RoleAllInfo();
		allinfo.m_oBasicInfo = PlayerManager.getInstance().getPlayerBasicInfo(player);
		allinfo.m_oBeastInfo = PlayerManager.getInstance().getPlayerBeastInfo(player);
		return allinfo;
	}
}
