package com.chen.db.bean;

import java.awt.List;


public class Role 
{
	private Long roleId;
	private String userId;
	private Integer createServer;
	private Integer area;
	private String name;
	private Integer sex;
	private Integer roleIndex;
	private Integer level;
	private Long exp;
	private String icon;
	private int money;
	private int ticket;
	private String loginIp;
	private Long onlineTime;
	private int addBlackCount;
	private int isForbit;
	private String data;
	private String beastList;
	public int getMoney() {
		return money;
	}
	public void setMoney(int money) {
		this.money = money;
	}
	public int getTicket() {
		return ticket;
	}
	public void setTicket(int ticket) {
		this.ticket = ticket;
	}
	public Long getOnlineTime() {
		return onlineTime;
	}
	public void setOnlineTime(Long onlineTime) {
		this.onlineTime = onlineTime;
	}
	public int getAddBlackCount() {
		return addBlackCount;
	}
	public void setAddBlackCount(int addBlackCount) {
		this.addBlackCount = addBlackCount;
	}
	public Role() {
		
	}
	public Long getRoleId() {
		return roleId;
	}
	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public Integer getCreateServer() {
		return createServer;
	}
	public void setCreateServer(Integer createServer) {
		this.createServer = createServer;
	}
	public Integer getArea() {
		return area;
	}
	public void setArea(Integer area) {
		this.area = area;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getLevel() {
		return level;
	}
	public void setLevel(Integer level) {
		this.level = level;
	}
	public Long getExp() {
		return exp;
	}
	public void setExp(Long exp) {
		this.exp = exp;
	}
	public String getLoginIp() {
		return loginIp;
	}
	public void setLoginIp(String loginIp) {
		this.loginIp = loginIp;
	}
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	public Integer getSex() {
		return sex;
	}
	public void setSex(Integer sex) {
		this.sex = sex;
	}
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	public int getIsForbit() {
		return isForbit;
	}
	public void setIsForbit(int isForbit) {
		this.isForbit = isForbit;
	}
	public String getBeastList() {
		return beastList;
	}
	public void setBeastList(String beastList) {
		this.beastList = beastList;
	}
	public Integer getRoleIndex() {
		return roleIndex;
	}
	public void setRoleIndex(Integer roleIndex) {
		this.roleIndex = roleIndex;
	}
}
