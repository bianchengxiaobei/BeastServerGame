package com.chen.battle.message.res;

import org.apache.mina.core.buffer.IoBuffer;

import com.chen.message.Message;

public class ResPlayerMPChangedMessage extends Message
{
	private long playerId;
	private int mp;
	@Override
	public int getId()
	{		
		return 0;
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
	public boolean read(IoBuffer buffer) 
	{
		this.playerId = readLong(buffer);
		this.mp = readInt(buffer);
		return true;
	}
	@Override
	public boolean write(IoBuffer buffer)
	{
		writeLong(buffer, playerId);
		writeInt(buffer, mp);
		return true;
	}
	public void setPlayerId(long id)
	{
		this.playerId = id;
	}
	public void setMp(int value)
	{
		this.mp = value;
	}
}
