package com.chen.battle.message.req;

import org.apache.mina.core.buffer.IoBuffer;

import com.chen.message.Message;

public class ReqBeastEndStageMessage extends Message
{
	public long beastId;
	public int stage;
	@Override
	public int getId() {
		// TODO Auto-generated method stub
		return 1030;
	}

	@Override
	public String getQueue() {
		// TODO Auto-generated method stub
		return null;
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
		this.stage = readInt(buffer);
		return true;
	}

	@Override
	public boolean write(IoBuffer arg0) {
		// TODO Auto-generated method stub
		return false;
	}

}
