package com.chen.cooldown.structs;

import com.chen.object.GameObject;
import com.chen.pool.MemoryObject;

public class Cooldown extends GameObject implements MemoryObject
{
	private static final long serialVersionUID = 2290578182650268855L;
	//冷却类型
	private String type;
	//冷却关键key
	private String key;
	//冷却开始回合
	private int start;
	//冷却持续回合
	private int delay;
	//冷却结束回合
	protected transient int endTime;
	//冷却剩余回合
	private transient int remainTime;
	@Override
	public void release() 
	{
		this.setKey(null);
		this.setDelay(0);
		this.setStart(0);
		this.setType(null);
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public int getStart() {
		return start;
	}
	public void setStart(int start) {
		this.start = start;
	}
	public int getDelay() {
		return delay;
	}
	public void setDelay(int delay) {
		this.delay = delay;
	}
	/**
	 * 获取结束回合的冷却时间
	 * @return
	 */
	public int getEndTime()
	{
		return start+delay;
	}
	/**
	 * 获取还剩几回合的冷却时间
	 * @return
	 */
	public int getRemainTime()
	{
		return 0;
	}
}
