package com.chen.battle.message.req;

import org.apache.mina.core.buffer.IoBuffer;

import com.chen.message.Message;

public class ReqBeastEnterStageMessage extends Message
{
	public long beastId;
	public int stage;
	@Override
	public int getId() {
		// TODO Auto-generated method stub
		return 1026;
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
		this.stage = readInt(buffer);
		return true;
	}

	@Override
	public boolean write(IoBuffer buffer) {
		// TODO Auto-generated method stub
		return true;
	}

}
