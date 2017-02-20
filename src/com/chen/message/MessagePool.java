package com.chen.message;

import java.util.HashMap;

import com.chen.battle.handler.ReqAddRoleToSceneHandler;
import com.chen.battle.handler.ReqBeastEnterStageHandler;
import com.chen.battle.handler.ReqBeastMoveHandler;
import com.chen.battle.handler.ReqEnterSceneHandler;
import com.chen.battle.handler.ReqSelectBeastHandler;
import com.chen.battle.handler.ReqTrySelectBeastHandler;
import com.chen.battle.message.req.ReqAddRoleToSceneMessage;
import com.chen.battle.message.req.ReqBeastEnterStageMessage;
import com.chen.battle.message.req.ReqBeastMoveMessage;
import com.chen.battle.message.req.ReqEnterSceneMessage;
import com.chen.battle.message.req.ReqSelectBeastMessage;
import com.chen.battle.message.req.ReqTrySelectBeastMessage;
import com.chen.command.Handler;
import com.chen.login.handler.ReqCreateCharacterToGameServerHandler;
import com.chen.login.handler.ReqLoginCharacterToGameServerHandler;
import com.chen.login.message.req.ReqCreateCharacterToGameServerMessage;
import com.chen.login.message.req.ReqLoginCharacterToGameServerMessage;
import com.chen.match.handler.ReqAutoMatchHandler;
import com.chen.match.message.req.ReqAutoMatchMessage;
import com.chen.server.handler.ResRegisterCenterHandler;
import com.chen.server.handler.ResRegisterGateHandler;
import com.chen.server.message.res.ResRegisterCenterMessage;
import com.chen.server.message.res.ResRegisterGateMessage;

public class MessagePool 
{
	HashMap<Integer, Class<?>> messages = new HashMap<Integer, Class<?>>();
	HashMap<Integer, Class<?>> handlers = new HashMap<Integer, Class<?>>();
	public MessagePool()
	{
		register(10001, ReqLoginCharacterToGameServerMessage.class, ReqLoginCharacterToGameServerHandler.class);
		register(10004, ResRegisterGateMessage.class, ResRegisterGateHandler.class);
		register(10006, ResRegisterCenterMessage.class, ResRegisterCenterHandler.class);
		register(10007, ReqCreateCharacterToGameServerMessage.class, ReqCreateCharacterToGameServerHandler.class);
		register(1006, ReqAutoMatchMessage.class, ReqAutoMatchHandler.class);
		register(1010, ReqTrySelectBeastMessage.class, ReqTrySelectBeastHandler.class);
		register(1011, ReqSelectBeastMessage.class, ReqSelectBeastHandler.class);
		register(1017, ReqEnterSceneMessage.class, ReqEnterSceneHandler.class);
		register(1023, ReqAddRoleToSceneMessage.class, ReqAddRoleToSceneHandler.class);
		register(1026, ReqBeastEnterStageMessage.class, ReqBeastEnterStageHandler.class);
		register(1028, ReqBeastMoveMessage.class, ReqBeastMoveHandler.class);
	}
	private void register(int id,Class<?> messageClass,Class<?> handlerClass)
	{
		messages.put(id, messageClass);
		if (handlerClass != null)
		{
			handlers.put(id, handlerClass);
		}
	}
	public Message getMessage(int id) throws InstantiationException, IllegalAccessException
	{
		if (messages.containsKey(id))
		{
			return (Message)messages.get(id).newInstance();
		}else{
			return null;
		}
	}
	public Handler getHandler(int id) throws InstantiationException, IllegalAccessException
	{
		if (handlers.containsKey(id))
		{
			return (Handler)handlers.get(id).newInstance();
		}else{
			return null;
		}
	}
}
