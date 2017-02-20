package com.chen.server.handler;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.chen.command.Handler;
import com.chen.server.impl.GameServer;
import com.chen.server.message.res.ResRegisterCenterMessage;

public class ResRegisterCenterHandler extends Handler
{
	private Logger log = LogManager.getLogger(ResRegisterCenterHandler.class);

	@Override
	public void action() 
	{
		try {
			ResRegisterCenterMessage msg = (ResRegisterCenterMessage)this.getMessage();
			log.info("游戏服务器"+GameServer.getInstance().getServer_name()+"注册到中心服务器"+msg.getServerName()+"返回成功");
		} catch (Exception e) {
			log.error(e,e);
		}
	}
	
}
