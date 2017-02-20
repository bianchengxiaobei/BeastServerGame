package com.chen.battle.message.res;

import java.util.List;

import org.apache.mina.core.buffer.IoBuffer;

import com.chen.message.Message;

public class ResStartGameMessage extends Message
{
	public long beastId;
	public List<Long> playerOrder;
	public int timeLimit;
	public byte empireHp;
	public byte leagueHp;
	@Override
	public int getId() {
		// TODO Auto-generated method stub
		return 1021;
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
		writeLong(buffer, beastId);
		writeByte(buffer, empireHp);
		writeByte(buffer, leagueHp);
		writeInt(buffer, timeLimit);
		writeLongList(buffer, playerOrder);	
		return true;
	}
	
}
