package com.chen.login.message.req;

import org.apache.mina.core.buffer.IoBuffer;

import com.chen.message.Message;
/**
 * 网关向游戏服务器发送创建角色请求
 * @author chen
 *
 */
public class ReqCreateCharacterToGameServerMessage extends Message
{
	private int gateId;
	private int createServer;
	private String userId;
	private String userName;
	private String name;
	private byte sex;//0-男，1-女
	private int roleIndex;
	private String icon;
	private byte isAdult;//0未成年，1成年
	private String optIp;
	private int loginType;
	@Override
	public int getId() {
		// TODO Auto-generated method stub
		return 10007;
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
		this.gateId = readInt(buf);
		this.createServer = readInt(buf);
		this.userId = readString(buf);
		this.userName = readString(buf);
		this.name = readString(buf);
		this.sex = readByte(buf);
		this.roleIndex = readInt(buf);
		this.icon = readString(buf);
		this.isAdult = readByte(buf);
		this.optIp = readString(buf);
		this.loginType = readInt(buf);
		return true;
	}
	@Override
	public boolean write(IoBuffer buf) {
		writeInt(buf, gateId);
		writeInt(buf, createServer);
		writeString(buf, userId);
		writeString(buf, userName);
		writeString(buf, name);
		writeByte(buf, this.sex);
		writeInt(buf, roleIndex);
		writeString(buf, icon);
		writeByte(buf, isAdult);
		writeString(buf, optIp);
		writeInt(buf, loginType);
		return true;
	}
	public int getGateId() {
		return gateId;
	}
	public void setGateId(int gateId) {
		this.gateId = gateId;
	}
	public int getCreateServer() {
		return createServer;
	}
	public void setCreateServer(int createServer) {
		this.createServer = createServer;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
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
	public byte getIsAdult() {
		return isAdult;
	}
	public void setIsAdult(byte isAdult) {
		this.isAdult = isAdult;
	}
	public String getOptIp() {
		return optIp;
	}
	public void setOptIp(String optIp) {
		this.optIp = optIp;
	}
	public int getLoginType() {
		return loginType;
	}
	public void setLoginType(int loginType) {
		this.loginType = loginType;
	}
	public byte getSex() {
		return sex;
	}
	public void setSex(byte sex) {
		this.sex = sex;
	}
	public int getRoleIndex() {
		return roleIndex;
	}
	public void setRoleIndex(int roleIndex) {
		this.roleIndex = roleIndex;
	}
}
