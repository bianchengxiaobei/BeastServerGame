package com.chen.data.bean;


public class BeastBean 
{
	private int beastId;
	private String name;
	//需要玩家等级
	private int needLevel;
	private int maxLevel;
	private int headId;
	//所有技能
	private String skills;
	
	public int getBeastId() {
		return beastId;
	}
	public void setBeastId(int beastId) {
		this.beastId = beastId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getNeedLevel() {
		return needLevel;
	}
	public void setNeedLevel(int needLevel) {
		this.needLevel = needLevel;
	}
	public int getMaxLevel() {
		return maxLevel;
	}
	public void setMaxLevel(int maxLevel) {
		this.maxLevel = maxLevel;
	}
	public int getHeadId() {
		return headId;
	}
	public void setHeadId(int headId) {
		this.headId = headId;
	}
	public String getSkills() {
		return skills;
	}
	public void setSkills(String skills) {
		this.skills = skills;
	}
	
}
