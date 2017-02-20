package com.chen.server.message.req;

import org.apache.mina.core.buffer.IoBuffer;

import com.chen.message.Message;

/**
 * 游戏服务器注册到中心服务器的请求消息10005
 * @author chen
 *
 */
public class ReqRegisterCenterMessage extends Message {
	// 服务器编号
	private int serverId;

	// 服务器名字
	private String serverName;

	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf) {
		// 服务器编号
		writeInt(buf, this.serverId);
		// 服务器名字
		writeString(buf, this.serverName);
		return true;
	}

	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf) {
		// 服务器编号
		this.serverId = readInt(buf);
		// 服务器名字
		this.serverName = readString(buf);
		return true;
	}

	/**
	 * get 服务器编号
	 * 
	 * @return
	 */
	public int getServerId() {
		return serverId;
	}

	/**
	 * set 服务器编号
	 */
	public void setServerId(int serverId) {
		this.serverId = serverId;
	}

	/**
	 * get 服务器名字
	 * 
	 * @return
	 */
	public String getServerName() {
		return serverName;
	}

	/**
	 * set 服务器名字
	 */
	public void setServerName(String serverName) {
		this.serverName = serverName;
	}

	@Override
	public int getId() {
		return 10005;
	}

	@Override
	public String getQueue() {
		return null;
	}

	@Override
	public String getServer() {
		return null;
	}
}
