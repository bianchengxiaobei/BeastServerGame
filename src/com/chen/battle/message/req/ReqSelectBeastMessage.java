package com.chen.battle.message.req;

import org.apache.mina.core.buffer.IoBuffer;

import com.chen.message.Message;

/**
 * 客户端发送给服务器选定该神兽请求消息1011
 * @author chen
 *
 */
public class ReqSelectBeastMessage extends Message
{
	private long beastId;
	@Override
	public int getId() {
		// TODO Auto-generated method stub
		return 1011;
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
		return true;
	}

	@Override
	public boolean write(IoBuffer arg0) {
		// TODO Auto-generated method stub
		return true;
	}

	public long getBeastId() {
		return beastId;
	}

	public void setBeastId(long beastId) {
		this.beastId = beastId;
	}
	
}
