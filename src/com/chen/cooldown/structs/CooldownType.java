package com.chen.cooldown.structs;
/**
 * 冷却类型枚举
 * @author chen
 *
 */
public enum CooldownType 
{
	Skill("Skill"),
	Equip("Equip");
	private String value;
	CooldownType(String value)
	{
		this.value = value;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
}
