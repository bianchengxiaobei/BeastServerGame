package com.chen.battle.structs.skill;

public class SSSkillCfg 
{
	public int skillId;
	public ESkillType skillType;
	public int needMp;
	public int needHp;
	public int cooldown;
	public int releaseMilsec;//准备动作时间
	public int skillLastTime;//技能后摇时间
	public int attackDis;//攻击距离
	public ESkillTriggerWay triggerWay;//技能触发方式
	
}
