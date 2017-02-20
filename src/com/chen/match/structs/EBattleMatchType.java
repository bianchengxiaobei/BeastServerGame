package com.chen.match.structs;

public enum EBattleMatchType 
{
	MATCH_MODE_INVALID((byte)0),
	MATCH_MODE_NORMAL((byte)1),
	MATCH_MODE_LADDER((byte)2),
	MATCH_MODE_QUICK_AI((byte)3),
	MATCH_MODE_TOTAL((byte)4);
	private byte value;
	EBattleMatchType(byte value)
	{
		this.value = value;
	}
	public byte getValue() {
		return value;
	}
}
