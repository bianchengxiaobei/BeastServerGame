package com.chen.battle.message.res;

import java.util.List;

import org.apache.mina.core.buffer.IoBuffer;

import com.chen.message.Message;
import com.chen.structs.CVector3;

public class ResBeastMoveMessage extends Message
{
	public long beastId;
	public List<CVector3> pos;
	@Override
	public int getId() {
		// TODO Auto-generated method stub
		return 1029;
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
		writeInt(buffer, this.pos.size());
		for (CVector3 v : this.pos)
		{
			writeBean(buffer,v);
		}
		return true;
	}

}
