package com.chen.match.message.req;

import org.apache.mina.core.buffer.IoBuffer;

import com.chen.message.Message;
/**
 * 客户端向游戏服务器发送请求匹配的消息1006
 * @author chen
 *
 */
public class ReqAutoMatchMessage extends Message
{
	private int m_nMapId;
	private byte m_btMatchType;
	private byte m_btAIDifficulty;
	public int getM_nMapId() {
		return m_nMapId;
	}

	public void setM_nMapId(int m_nMapId) {
		this.m_nMapId = m_nMapId;
	}

	public byte getM_btMatchType() {
		return m_btMatchType;
	}

	public void setM_btMatchType(byte m_btMatchType) {
		this.m_btMatchType = m_btMatchType;
	}

	public byte getM_btAIDifficulty() {
		return m_btAIDifficulty;
	}

	public void setM_btAIDifficulty(byte m_btAIDifficulty) {
		this.m_btAIDifficulty = m_btAIDifficulty;
	}

	@Override
	public int getId() {
		// TODO Auto-generated method stub
		return 1006;
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
		this.m_nMapId = readInt(buf);
		this.m_btMatchType = readByte(buf);
		this.m_btAIDifficulty = readByte(buf);
		return true;
	}

	@Override
	public boolean write(IoBuffer buf) {
		writeInt(buf, m_nMapId);
		writeByte(buf, m_btMatchType);
		writeByte(buf, m_btAIDifficulty);
		return true;
	}
	
}
