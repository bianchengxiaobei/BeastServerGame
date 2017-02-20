package com.chen.login.message.res;

import org.apache.mina.core.buffer.IoBuffer;

import com.chen.login.bean.RoleAllInfo;
import com.chen.message.Message;

/**
 * 游戏服务器通知网关服务器角色创建成功，就成功登陆消息
 * @author chen
 *
 */
public class ResLoginSuccessToGateMessage extends Message
{
	private int serverId;
	private int createServerId;
	private String userId;
	private long playerId;
	private RoleAllInfo roleAllInfo;
	@Override
	public int getId() {
		// TODO Auto-generated method stub
		return 10008;
	}

	@Override
	public String getQueue() {
		// TODO Auto-generated method stub
		return "Local";
	}

	public int getServerId() {
		return serverId;
	}

	public void setServerId(int serverId) {
		this.serverId = serverId;
	}

	public int getCreateServerId() {
		return createServerId;
	}

	public void setCreateServerId(int createServerId) {
		this.createServerId = createServerId;
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

	@Override
	public String getServer() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean read(IoBuffer buf) {
		this.serverId = readInt(buf);
		this.createServerId = readInt(buf);
		this.userId = readString(buf);
		this.playerId = readLong(buf);
		this.roleAllInfo = (RoleAllInfo) readBean(buf, RoleAllInfo.class);
		return true;
	}

	@Override
	public boolean write(IoBuffer buf) {
		writeInt(buf, this.serverId);
		writeInt(buf, createServerId);
		writeString(buf, this.userId);
		writeLong(buf, playerId);
		writeBean(buf, this.roleAllInfo);
		return true;
	}

	public RoleAllInfo getRoleAllInfo() {
		return roleAllInfo;
	}

	public void setRoleAllInfo(RoleAllInfo roleAllInfo) {
		this.roleAllInfo = roleAllInfo;
	}

}
