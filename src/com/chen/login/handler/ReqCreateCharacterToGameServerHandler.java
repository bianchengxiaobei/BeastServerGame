package com.chen.login.handler;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.chen.command.Handler;
import com.chen.login.manager.LoginManager;
import com.chen.login.message.req.ReqCreateCharacterToGameServerMessage;

public class ReqCreateCharacterToGameServerHandler extends Handler
{
	private Logger log = LogManager.getLogger(ReqCreateCharacterToGameServerHandler.class);

	@Override
	public void action() 
	{
		try {
			ReqCreateCharacterToGameServerMessage msg = (ReqCreateCharacterToGameServerMessage)this.getMessage();
			LoginManager.getInstance().createCharacter(msg);
		} catch (Exception e) {
			log.error(e,e);
		}
	}
	
}
