package com.chen.player.structs;

import com.chen.object.GameObject;

public abstract class Person extends GameObject
{
	private static final long serialVersionUID = 5110683047251762627L;
	protected int modelId;
	protected long createTime;
	protected int map;
	protected int mapModelId;
	protected String name;
	protected String icon;
	protected int level;
}
