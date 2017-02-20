package com.chen.battle.message.res;

import java.util.ArrayList;
import java.util.List;

import org.apache.mina.core.buffer.IoBuffer;

import com.chen.message.Message;

/**
 * 服务器发送给客户端试图选择该神兽的结果1012
 * @author chen
 *
 */
public class ResTrySelectBeastMessage extends Message
{
	private long beastId;
	private int beastTypeId;
	private int beastLevel;
	private List<Integer> skillList = new ArrayList<Integer>();
	private byte isRandom;
	private int suitId;
	public int getBeastLevel() {
		return beastLevel;
	}

	public void setBeastLevel(int beastLevel) {
		this.beastLevel = beastLevel;
	}

	public List<Integer> getSkillList() {
		return skillList;
	}

	public void setSkillList(List<Integer> skillList) {
		this.skillList = skillList;
	}

	public byte getIsRandom() {
		return isRandom;
	}

	public void setIsRandom(byte isRandom) {
		this.isRandom = isRandom;
	}

	public int getSuitId() {
		return suitId;
	}

	public void setSuitId(int suitId) {
		this.suitId = suitId;
	}

	public long getBeastId() {
		return beastId;
	}

	public void setBeastId(long beastId) {
		this.beastId = beastId;
	}

	public int getBeastTypeId() {
		return beastTypeId;
	}

	public void setBeastTypeId(int beastTypeId) {
		this.beastTypeId = beastTypeId;
	}

	@Override
	public int getId() {
		// TODO Auto-generated method stub
		return 1012;
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
		writeInt(buf, this.beastTypeId);
		writeInt(buf, this.beastLevel);
		writeInt(buf, this.suitId);
		writeByte(buf, this.isRandom);
		writeIntList(buf, this.skillList);
		return true;
	}

}
