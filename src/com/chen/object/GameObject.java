package com.chen.object;

import java.io.Serializable;

import com.chen.config.Config;

public class GameObject implements Serializable
{
	private static final long serialVersionUID = -6448692040296711057L;
	protected long id;
	protected String clazz;
	protected GameObject()
	{
		this.id = Config.getId();
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getClazz() {
		return clazz;
	}
	public void setClazz(String clazz) {
		this.clazz = clazz;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
