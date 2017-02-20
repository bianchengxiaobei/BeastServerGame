package com.chen.login.bean;

import java.util.ArrayList;
import java.util.List;

import org.apache.mina.core.buffer.IoBuffer;

import com.chen.message.Bean;

/**
 * 神兽基础信息数据
 * @author chen
 *
 */
public class BeastData extends Bean
{
	public int m_dwId;
	public int m_dwExp;
	public int m_dwLevel;
	public List<Integer> m_oSkillList = new ArrayList<Integer>();
	public List<Integer> m_oSuitList = new ArrayList<Integer>();
	@Override
	public boolean write(IoBuffer buf) {
		writeInt(buf, this.m_dwId);
		writeInt(buf, this.m_dwExp);
		writeInt(buf, this.m_dwLevel);
		writeIntList(buf, m_oSkillList);
		return true;
	}
	@Override
	public boolean read(IoBuffer buf) {
		this.m_dwId = readInt(buf);
		this.m_dwExp = readInt(buf);
		this.m_dwLevel = readInt(buf);
		this.m_oSkillList = readIntList(buf);
		return true;
	}
}
