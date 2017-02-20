package com.chen.battle.message.res;

import org.apache.mina.core.buffer.IoBuffer;

import com.chen.message.Message;
/**
 * 开始某个神兽的战斗阶段
 * @author Administrator
 *
 */
public class ResStartBeastRoundMessage extends Message
{
	public long beastId;
	@Override
	public int getId() {
		// TODO Auto-generated method stub
		return 1025;
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
	public boolean read(IoBuffer arg0) {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean write(IoBuffer buffer) {
		// TODO Auto-generated method stub
		writeLong(buffer, this.beastId);
		return true;
	}

}
