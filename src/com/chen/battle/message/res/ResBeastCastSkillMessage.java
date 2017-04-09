package com.chen.battle.message.res;

import java.util.ArrayList;
import java.util.List;

import org.apache.mina.core.buffer.IoBuffer;

import com.chen.message.Message;
import com.chen.structs.CVector3;

public class ResBeastCastSkillMessage extends Message
{
	public long m_dwRoleId;
    public int m_dwSkillId;
    public long m_dwTargetRoleId;
    public CVector3 m_oTargetPos;
    public List<Long> m_oHurtList = new ArrayList<Long>();
	@Override
	public int getId() {
		// TODO Auto-generated method stub
		return 1034;
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
		this.m_dwRoleId = readLong(buffer);
		this.m_dwSkillId = readInt(buffer);
		this.m_dwTargetRoleId = readLong(buffer);
		this.m_oTargetPos = (CVector3) readBean(buffer,CVector3.class);
		this.m_oHurtList = readLongList(buffer);
		return true;
	}

	@Override
	public boolean write(IoBuffer buffer) {
		writeLong(buffer, m_dwRoleId);
		writeInt(buffer, m_dwSkillId);
		writeLong(buffer, m_dwTargetRoleId);
		writeBean(buffer, m_oTargetPos);
		writeLongList(buffer, m_oHurtList);
		return true;
	}
	
}
