package com.chen.battle.message.res;

import org.apache.mina.core.buffer.IoBuffer;

import com.chen.message.Message;
import com.chen.structs.CVector3;

public class ResAddRoleToSceneMessage extends Message
{
	public long beastId;
	public CVector3 pos;
	@Override
	public int getId() {
		// TODO Auto-generated method stub
		return 1024;
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
	public boolean read(IoBuffer buffer) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean write(IoBuffer buffer) {
		// TODO Auto-generated method stub
		writeLong(buffer, beastId);
		writeBean(buffer, pos);
		return true;
	}

}
