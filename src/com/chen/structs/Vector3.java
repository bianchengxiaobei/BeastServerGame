package com.chen.structs;

public class Vector3 
{
	private float x;
	private float y;
	private float z;
	public Vector3(float x,float y,float z)
	{
		this.setX(x);
		this.setY(y);
		this.setZ(z);
	}
	public float getX() {
		return x;
	}
	public void setX(float x) {
		this.x = x;
	}
	public float getY() {
		return y;
	}
	public void setY(float y) {
		this.y = y;
	}
	public float getZ() {
		return z;
	}
	public void setZ(float z) {
		this.z = z;
	}
}
