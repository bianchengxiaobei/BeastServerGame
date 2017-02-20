package com.chen.battle.message.res;

import java.util.ArrayList;
import java.util.List;

import org.apache.mina.core.buffer.IoBuffer;

import com.chen.message.Message;

/**
 * 服务器发送给客户端选定神兽的结果1013
 * @author chen
 *
 */
public class ResSelectBeastMessage extends Message
{
	private long beastId;
	@Override
	public int getId() {
		// TODO Auto-generated method stub
		return 1013;
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
	public boolean read(IoBuffer buf) {
		
		return true;
	}

	@Override
	public boolean write(IoBuffer buf) {
		writeLong(buf, this.beastId);
		return true;
	}

	public long getBeastId() {
		return beastId;
	}

	public void setBeastId(long beastId) {
		this.beastId = beastId;
	}
	
}
