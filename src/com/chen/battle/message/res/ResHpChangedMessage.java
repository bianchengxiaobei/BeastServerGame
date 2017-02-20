package com.chen.battle.message.res;

import org.apache.mina.core.buffer.IoBuffer;

import com.chen.message.Message;
/**
 * 神兽血量改变消息
 * @author Administrator
 *
 */
public class ResHpChangedMessage extends Message
{
	private long beastId;
	private int hp;
	private byte reason;
	@Override
	public int getId() {
		// TODO Auto-generated method stub
		return 1020;
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
		this.beastId = readLong(buffer);
		this.hp = readInt(buffer);
		this.reason = readByte(buffer);
		return true;
	}

	@Override
	public boolean write(IoBuffer buffer) {
		writeLong(buffer, beastId);
		writeInt(buffer, hp);
		writeByte(buffer, reason);
		return true;
	}
	public void setBeastId(long playerId)
	{
		this.beastId = playerId;
	}
	public void setHp(int hp)
	{
		this.hp = hp;
	}
	public void setReason(byte reason)
	{
		this.reason = reason;
	}
}
