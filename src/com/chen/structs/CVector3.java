package com.chen.structs;

import org.apache.mina.core.buffer.IoBuffer;

import com.chen.message.Bean;

public class CVector3 extends Bean
{
	public static CVector3 MaxValue = new CVector3();
	public int m_nX;
	public int m_nY;
	public int m_nU;
	public CVector3()
	{
		this.m_nX = 2147483647;
		this.m_nY = 2147483647;
		this.m_nU = 2147483647;
	}
	public CVector3(CVector3 pos)
	{
		this.m_nX = pos.m_nX;
		this.m_nY = pos.m_nY;
		this.m_nU = pos.m_nU;
	}
	public CVector3(int nX, int nY, int nU)
	{
		this.m_nX = nX;
		this.m_nY = nY;
		this.m_nU = nU;
	}
	@Override
	public boolean read(IoBuffer buf) {
		this.m_nX = readInt(buf);
		this.m_nY = readInt(buf);
		this.m_nU = readInt(buf);
		return true;
	}
	@Override
	public boolean write(IoBuffer buf) {
		writeInt(buf, m_nX);
		writeInt(buf, m_nY);
		writeInt(buf, m_nU);
		return true;
	}
	@Override
	public boolean equals(Object pos) 
	{
		CVector3 vec = (CVector3)pos;
		if (pos == null)
		{
			return false;
		}
		if (this.getRow() == vec.getRow() && this.getCol() == vec.getCol())
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	public int getCol()
	{
		return this.m_nX;
	}
	public void setCol(int col)
	{
		this.m_nX = col;
		this.m_nY = this.m_nU + this.m_nX;
	}
	public int getRow()
	{
		return this.m_nU;
	}
	public void setRow(int row)
	{
		this.m_nU = row;
		this.m_nY = this.m_nU + this.m_nX;
	}
	@Override
	public String toString()
	{
		return String.format("x=%s,y=%s,u=%s", this.m_nX,this.m_nY,this.m_nU);
	}
}
