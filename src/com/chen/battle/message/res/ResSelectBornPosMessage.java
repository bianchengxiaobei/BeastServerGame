package com.chen.battle.message.res;

import org.apache.mina.core.buffer.IoBuffer;

import com.chen.message.Message;
/**
 *	通知玩家神兽选择出身点消息
 * @author Administrator
 *
 */
public class ResSelectBornPosMessage extends Message
{
	private long beastId;
	@Override
	public int getId() {
		// TODO Auto-generated method stub
		return 1022;
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
		
		return true;
	}

	@Override
	public boolean write(IoBuffer buffer) {
		writeLong(buffer, this.beastId);
		return true;
	}

	public long getBeastId() {
		return beastId;
	}

	public void setBeastId(long beastId) {
		this.beastId = beastId;
	}
	
}
