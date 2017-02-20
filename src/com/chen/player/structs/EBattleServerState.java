package com.chen.player.structs;

public enum EBattleServerState 
{
	eSSBS_SelectHero(0),
	eSSBS_SelectRune(1),
	eSSBS_Prepare(2),
	eSSBS_Loading(3),
	eSSBS_Playing(4),
	eSSBS_Finished(5);
	
	private int value;
	EBattleServerState(int value)
	{
		this.setValue(value);
	}
	public int getValue() {
		return value;
	}
	public void setValue(int value) {
		this.value = value;
	}
	
}
