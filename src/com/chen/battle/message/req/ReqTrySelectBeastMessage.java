package com.chen.battle.message.req;

import org.apache.mina.core.buffer.IoBuffer;

import com.chen.message.Message;

/**
 * 客户端发送给游戏服务器试图选择该神兽消息1010
 * @author chen
 *
 */
public class ReqTrySelectBeastMessage extends Message
{
	private int beastTypeId;
	private long beastId;
	private int suitId;
	public long getBeastId() {
		return beastId;
	}

	public void setBeastId(long beastId) {
		this.beastId = beastId;
	}

	@Override
	public int getId() {
		// TODO Auto-generated method stub
		return 1010;
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
	public boolean read(IoBuffer buf) {
		this.beastId = readLong(buf);
		this.beastTypeId = readInt(buf);
		this.suitId = readInt(buf);
		return true;
	}

	@Override
	public boolean write(IoBuffer buf) {
		this.writeLong(buf, this.beastId);
		writeInt(buf, this.beastTypeId);
		writeInt(buf, this.suitId);
		return true;
	}

	public int getSuitId() {
		return suitId;
	}

	public void setSuitId(int suitId) {
		this.suitId = suitId;
	}

	public int getBeastTypeId() {
		return beastTypeId;
	}

	public void setBeastTypeId(int beastTypeId) {
		this.beastTypeId = beastTypeId;
	}

}
