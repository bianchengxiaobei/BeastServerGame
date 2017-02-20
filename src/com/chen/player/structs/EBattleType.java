package com.chen.player.structs;

public enum EBattleType
{
	eBattleType_Free((byte)0),		//自由中//
	eBattleType_Room((byte)1),		//房间中//
	eBattleType_Match((byte)2),		//匹配中//
	eBattleType_Guide1((byte)3),		//引导1中//
	eBattleType_Guide2((byte)4);		//引导2中//
	private byte value;
	EBattleType(byte value)
	{
		this.value = value;
	}
	public byte getValue()
	{
		return this.value;
	}
}
