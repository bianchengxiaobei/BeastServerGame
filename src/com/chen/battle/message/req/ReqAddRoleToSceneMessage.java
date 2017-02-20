package com.chen.battle.message.req;

import org.apache.mina.core.buffer.IoBuffer;

import com.chen.message.Message;
import com.chen.structs.CVector3;

public class ReqAddRoleToSceneMessage extends Message
{
	public CVector3 pos;
	public long beastId;
	@Override
	public int getId() {
		// TODO Auto-generated method stub
		return 1023;
	}

	@Override
	public String getQueue() {
		// TODO Auto-generated method stub
		return "Local";
	}

	@Override
	public String getServer() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean read(IoBuffer buffer) {
		// TODO Auto-generated method stub
		this.beastId = readLong(buffer);
		this.pos = (CVector3) readBean(buffer,CVector3.class);
		return true;
	}

	@Override
	public boolean write(IoBuffer buffer) {
		// TODO Auto-generated method stub
		return true;
	}
	
}
