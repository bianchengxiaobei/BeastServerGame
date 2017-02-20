package com.chen.battle.message.res;

import java.util.List;

import org.apache.mina.core.buffer.IoBuffer;

import com.chen.message.Message;
import com.chen.room.bean.RoomMemberData;
/**
 * 服务器发送给客户端进入匹配选人的房间1009
 * @author Administrator
 *
 */
public class ResEnterRoomMessage extends Message
{
	private long battleId;
	private List<RoomMemberData> m_oEmpireMemberList;
	private List<RoomMemberData> m_oLeagueMemberList;
	private int m_nTimeLimit;
	private int m_nMapId;
	private byte m_btGameType;

	public List<RoomMemberData> getM_oEmpireMemberList() {
		return m_oEmpireMemberList;
	}

	public void setM_oEmpireMemberList(List<RoomMemberData> m_oEmpireMemberList) {
		this.m_oEmpireMemberList = m_oEmpireMemberList;
	}

	public List<RoomMemberData> getM_oLeagueMemberList() {
		return m_oLeagueMemberList;
	}

	public void setM_oLeagueMemberList(List<RoomMemberData> m_oLeagueMemberList) {
		this.m_oLeagueMemberList = m_oLeagueMemberList;
	}

	public int getM_nTimeLimit() {
		return m_nTimeLimit;
	}

	public void setM_nTimeLimit(int m_nTimeLimit) {
		this.m_nTimeLimit = m_nTimeLimit;
	}

	public int getM_nMapId() {
		return m_nMapId;
	}

	public void setM_nMapId(int m_nMapId) {
		this.m_nMapId = m_nMapId;
	}

	public byte getM_btGameType() {
		return m_btGameType;
	}

	public void setM_btGameType(byte m_btGameType) {
		this.m_btGameType = m_btGameType;
	}

	@Override
	public int getId() {
		// TODO Auto-generated method stub
		return 1009;
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
		writeLong(buf, this.battleId);
		writeInt(buf, this.m_oEmpireMemberList.size());
		for (int i=0; i<this.m_oEmpireMemberList.size(); i++)
		{
			writeBean(buf, this.m_oEmpireMemberList.get(i));
		}
		writeInt(buf, this.m_oLeagueMemberList.size());
		for (int i=0; i<this.m_oLeagueMemberList.size(); i++)
		{
			writeBean(buf, this.m_oLeagueMemberList.get(i));
		}
		writeInt(buf, this.m_nTimeLimit);
		writeInt(buf, this.m_nMapId);
		writeByte(buf, this.m_btGameType);
		return true;
	}

	public long getBattleId() {
		return battleId;
	}

	public void setBattleId(long battleId) {
		this.battleId = battleId;
	}
	
}
