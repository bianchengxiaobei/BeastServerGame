package com.chen.login.message.req;

import org.apache.mina.core.buffer.IoBuffer;

import com.chen.message.Message;

/**
 * 发送登陆和选择用户角色消息到游戏服务器
 * @author chen
 *
 */
public class ReqLoginCharacterToGameServerMessage extends Message
{
	private int gateId;
	private String userName;
	private int serverId;
	private String userId;
	public int getGateId() {
		return gateId;
	}
	public void setGateId(int gateId) {
		this.gateId = gateId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public int getServerId() {
		return serverId;
	}
	public void setServerId(int serverId) {
		this.serverId = serverId;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public long getPlayerId() {
		return playerId;
	}
	public void setPlayerId(long playerId) {
		this.playerId = playerId;
	}
	public byte getIsAdult() {
		return isAdult;
	}
	public void setIsAdult(byte isAdult) {
		this.isAdult = isAdult;
	}
	public String getLoginIp() {
		return loginIp;
	}
	public void setLoginIp(String loginIp) {
		this.loginIp = loginIp;
	}
	public int getLoginType() {
		return loginType;
	}
	public void setLoginType(int loginType) {
		this.loginType = loginType;
	}
	private long playerId;
	private byte isAdult;
	private String loginIp;
	private int loginType;
	@Override
	public int getId() {
		// TODO Auto-generated method stub
		return 10001;
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
	public boolean read(IoBuffer buffer) {
		this.gateId = readInt(buffer);
		this.isAdult = readByte(buffer);
		this.loginIp = readString(buffer);
		this.loginType = readInt(buffer);
		this.playerId = readLong(buffer);
		this.serverId = readInt(buffer);
		this.userId = readString(buffer);
		this.userName = readString(buffer);
		return true;
	}
	@Override
	public boolean write(IoBuffer buffer) {
		writeInt(buffer, gateId);
		writeByte(buffer, isAdult);
		writeString(buffer, loginIp);
		writeInt(buffer, loginType);
		writeLong(buffer, playerId);
		writeInt(buffer, serverId);
		writeString(buffer, userId);
		writeString(buffer, userName);
		return true;
	}
}
