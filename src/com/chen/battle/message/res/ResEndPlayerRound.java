package com.chen.battle.message.res;

import org.apache.mina.core.buffer.IoBuffer;

import com.chen.message.Message;
/**
 * 服务器发送给客户端结束该玩家的战斗阶段
 * @author Administrator
 *
 */
public class ResEndPlayerRound extends Message
{
	public long playerId;
	@Override
	public int getId() {
		// TODO Auto-generated method stub
		return 1033;
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
	public boolean read(IoBuffer buffer)
	{
		this.playerId = readLong(buffer);
		return true;
	}

	@Override
	public boolean write(IoBuffer buffer) {
		writeLong(buffer, playerId);
		return true;
	}

}
