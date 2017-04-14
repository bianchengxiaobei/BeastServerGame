package com.chen.battle.message.res;

import org.apache.mina.core.buffer.IoBuffer;

import com.chen.message.Message;

public class ResSkillCDChangeMessage extends Message
{
	public long beastId;
	public int skillId;
	public byte value;
	@Override
	public int getId() {
		// TODO Auto-generated method stub
		return 1036;
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
		this.skillId = readInt(buffer);
		this.value = readByte(buffer);
		return true;
	}

	@Override
	public boolean write(IoBuffer buffer) {
		// TODO Auto-generated method stub
		writeLong(buffer, beastId);
		writeInt(buffer, skillId);
		writeByte(buffer, value);
		return true;
	}

}
