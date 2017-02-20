package com.chen.server.handler;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.chen.command.Handler;
import com.chen.server.impl.GameServer;
import com.chen.server.message.res.ResRegisterGateMessage;


public class ResRegisterGateHandler extends Handler
{
	Logger log = LogManager.getLogger(ResRegisterGateHandler.class);

	public void action() {
		try {
			ResRegisterGateMessage msg = (ResRegisterGateMessage) this
					.getMessage();
			log.info("游戏服务器" + GameServer.getInstance().getServer_name() + "注册到"
					+ msg.getServerName() + "返回成功！");
		} catch (ClassCastException e) {
			log.error(e);
		}
	}
}
