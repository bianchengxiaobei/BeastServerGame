package com.chen.room.bean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;

import org.apache.mina.core.buffer.IoBuffer;

import com.chen.battle.structs.beast.SSBeast;
import com.chen.login.bean.BeastData;
import com.chen.map.structs.Map;
import com.chen.message.Bean;
import com.chen.room.structs.ECampType;

public class RoomMemberData extends Bean
{
	public long getPlayerId() {
		return playerId;
	}

	public void setPlayerId(long playerId) {
		this.playerId = playerId;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public List<Long> getBeastList() {
		return beastList;
	}

	public void setBeastList(List<Long> beastList) {
		this.beastList = beastList;
	}

	public byte getIsReconnecting() {
		return isReconnecting;
	}

	public void setIsReconnecting(byte isReconnecting) {
		this.isReconnecting = isReconnecting;
	}

	public HashMap<Integer, BeastData> getBeastMap() {
		return beastMap;
	}

	public void setBeastMap(HashMap<Integer, BeastData> beastMap) {
		this.beastMap = beastMap;
	}
	//--------------------
	private long playerId;
	private int level;
	private String name;
	private String icon;

	private List<Long> beastList;
	private byte isReconnecting;
	//是否已经加载完成
	private boolean isLoadCompleted = false;
	private HashMap<Integer, BeastData> beastMap;
	//-------------------------
	//是否已经选择了神兽
	private boolean m_bHasBeastChoosed = false;
	//已经选择的神兽id
	private long m_nSelectBeastId = -1;
	private List<Long> selectedBeastList = new ArrayList<Long>();
	private int m_nSelectBeastTypeId = -1;
	private int m_nSuitId = -1;
	//所在阵营
	private ECampType campType;
	private List<Long> tempBeastList; 
	public List<Long> getTempBeastList() {
		return tempBeastList;
	}

	public void setTempBeastList(List<Long> tempBeastList) {
		this.tempBeastList = tempBeastList;
	}

	public boolean isM_bHasBeastChoosed() {
		return m_bHasBeastChoosed;
	}

	public void setM_bHasBeastChoosed(boolean m_bHasBeastChoosed) {
		this.m_bHasBeastChoosed = m_bHasBeastChoosed;
	}

	public long getM_nSelectBeastId() {
		return m_nSelectBeastId;
	}

	public void setM_nSelectBeastId(long m_nSelectBeastId) {
		this.m_nSelectBeastId = m_nSelectBeastId;
	}

	@Override
	public boolean read(IoBuffer buf) {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean write(IoBuffer buf) 
	{
		writeLong(buf, this.playerId);
		writeInt(buf, this.level);
		writeString(buf,this.name);
		writeString(buf, this.icon);
		writeByte(buf, this.isReconnecting);
		writeLongList(buf, this.beastList);
		writeInt(buf, this.beastMap.size());
		for (java.util.Map.Entry<Integer, BeastData> entry : this.beastMap.entrySet())
		{
			writeInt(buf, entry.getKey());
			writeBean(buf, entry.getValue());
		}
		return true;
	}
	/**
	 * 取得该神兽的技能列表
	 * @param beastId
	 * @return
	 */
	public List<Integer> getBeastSkillList(int beastId)
	{
		BeastData data = this.beastMap.get(beastId);
		return data.m_oSkillList;
	}
	/**
	 * 取得该神兽的等级
	 * @param beastId
	 * @return
	 */
	public int getBeastLevel(int beastTypeId)
	{
		BeastData data = this.beastMap.get(beastTypeId);
		return data.m_dwLevel;
	}

	public ECampType getCampType() {
		return campType;
	}

	public void setCampType(ECampType campType) {
		this.campType = campType;
	}

	public int getM_nSelectBeastTypeId() {
		return m_nSelectBeastTypeId;
	}

	public void setM_nSelectBeastTypeId(int m_nSelectBeastTypeId) {
		this.m_nSelectBeastTypeId = m_nSelectBeastTypeId;
	}

	public int getM_nSuitId() {
		return m_nSuitId;
	}

	public void setM_nSuitId(int m_nSuitId) {
		this.m_nSuitId = m_nSuitId;
	}
	public List<Long> getCopyList(List<Long> source)
	{
		List<Long> tempList = new ArrayList<Long>();
		tempList.addAll(source);
		return tempList;
	}

	public boolean isLoadCompleted() {
		return isLoadCompleted;
	}

	public void setLoadCompleted(boolean isLoadCompleted) {
		this.isLoadCompleted = isLoadCompleted;
	}

	public List<Long> getSelectedBeastList() {
		return selectedBeastList;
	}

	public void setSelectedBeastList(List<Long> selectedBeastList) {
		this.selectedBeastList = selectedBeastList;
	}

}
