package com.chen.skill.structs;

import com.chen.object.GameObject;

public class Skill extends GameObject
{
	private static final long serialVersionUID = 7026462647976592167L;
	private int skillId;
	public int getSkillId() {
		return skillId;
	}
	public void setSkillId(int skillId) {
		this.skillId = skillId;
	}
}
