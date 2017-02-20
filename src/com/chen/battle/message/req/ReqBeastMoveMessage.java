package com.chen.battle.message.req;

import org.apache.mina.core.buffer.IoBuffer;

import com.chen.message.Message;
import com.chen.structs.CVector3;

public class ReqBeastMoveMessage extends Message
{
	public long beastId;
	public CVector3 vec;
	@Override
	public int getId() {
		// TODO Auto-generated method stub
		return 1028;
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
		this.vec = (CVector3)readBean(buffer, CVector3.class);
		return true;
	}

	@Override
	public boolean write(IoBuffer arg0) {
		// TODO Auto-generated method stub
		return true;
	}

}
