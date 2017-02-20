package com.chen.login.bean;

import org.apache.mina.core.buffer.IoBuffer;

import com.chen.message.Bean;
/**
 * 角色所有信息实体类
 * @author chen
 *
 */
public class RoleAllInfo extends Bean
{
	public RoleBasicInfo m_oBasicInfo;
	public BeastInfo m_oBeastInfo;
	
	public RoleAllInfo()
	{
		this.m_oBasicInfo = new RoleBasicInfo();
		this.m_oBeastInfo = new BeastInfo();
	}
	@Override
	public boolean write(IoBuffer buf) {
		writeBean(buf, this.m_oBasicInfo);
		writeBean(buf, this.m_oBeastInfo);
		return true;
	}
	@Override
	public boolean read(IoBuffer buf) {
		this.m_oBasicInfo = (RoleBasicInfo)readBean(buf, RoleBasicInfo.class);
		this.m_oBeastInfo = (BeastInfo)readBean(buf, BeastInfo.class);
		return true;
	}	
}
