package com.chen.room.structs;

public enum ECampType 
{
	Empire(0),
	Leagues(1);
	private int value;
	ECampType(int value)
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
