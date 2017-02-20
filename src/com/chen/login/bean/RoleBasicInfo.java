package com.chen.login.bean;

import org.apache.mina.core.buffer.IoBuffer;

import com.chen.message.Bean;
/**
 * 角色基础信息实体类
 * @author chen
 *
 */
public class RoleBasicInfo extends Bean
{
	public long m_dwId;
	public String m_strAccount;
	public String m_strName;
	public String m_strIcon;
	public int m_dwLevel;
	public int m_dwExp;
	public int m_dwMoney;
	public int m_dwTicket;
	public long m_dwOnlineTime;
	public long m_dwLoginTime;
	@Override
	public boolean write(IoBuffer buf) 
	{
		writeLong(buf, m_dwId);
		writeString(buf,this.m_strAccount);
		writeString(buf, m_strName);
		writeString(buf, this.m_strIcon);
		writeInt(buf, this.m_dwLevel);
		writeInt(buf, m_dwExp);
		writeInt(buf, this.m_dwMoney);
		writeInt(buf, this.m_dwTicket);
		writeLong(buf, m_dwOnlineTime);
		writeLong(buf, m_dwLoginTime);
		return true;
	}
	@Override
	public boolean read(IoBuffer buf) {
		this.m_dwId = readLong(buf);
		this.m_strAccount = readString(buf);
		this.m_strName = readString(buf);
		this.m_strIcon = readString(buf);
		this.m_dwLevel = readInt(buf);
		this.m_dwExp = readInt(buf);
		this.m_dwMoney = readInt(buf);
		this.m_dwTicket = readInt(buf);
		this.m_dwOnlineTime = readLong(buf);
		this.m_dwLoginTime = readLong(buf);		
		return true;
	}
}
