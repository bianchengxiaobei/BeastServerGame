package com.chen.battle.message.res;

import org.apache.mina.core.buffer.IoBuffer;

import com.chen.message.Message;

public class ResBeastEnterStageMessage extends Message
{
	public long beastId;
	public int stage;
	@Override
	public int getId() {
		// TODO Auto-generated method stub
		return 1027;
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
		return true;
	}

	@Override
	public boolean write(IoBuffer buffer) {
		// TODO Auto-generated method stub
		writeLong(buffer, this.beastId);
		writeInt(buffer, stage);
		return true;
	}

}
