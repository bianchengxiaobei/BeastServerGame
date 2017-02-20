package com.chen.match.message.res;

import org.apache.mina.core.buffer.IoBuffer;

import com.chen.message.Message;

/**
 * 游戏服务器向客户端发送匹配开始消息1008
 * @author chen
 *
 */
public class ResMatchStartMessage extends Message
{
	private int m_reason;
	private int m_waitTime;
	public int getM_reason() {
		return m_reason;
	}

	public void setM_reason(int m_reason) {
		this.m_reason = m_reason;
	}

	public int getM_waitTime() {
		return m_waitTime;
	}

	public void setM_waitTime(int m_waitTime) {
		this.m_waitTime = m_waitTime;
	}

	@Override
	public int getId() {
		// TODO Auto-generated method stub
		return 1008;
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
		this.m_reason = readInt(buf);
		this.m_waitTime = readInt(buf);
		return true;
	}

	@Override
	public boolean write(IoBuffer buf) {
		writeInt(buf, m_reason);
		writeInt(buf, m_waitTime);
		return true;
	}
	
}
