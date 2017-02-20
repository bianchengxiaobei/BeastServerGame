package com.chen.battle.structs.skill;

import com.chen.battle.structs.SSGameObject;
import com.chen.structs.Vector3;

public class SSSkill 
{
	public SSSkillCfg cfg;
	public SSGameObject master;
	public SSGameObject target;
	public Vector3 skillTargetPos;
	public Vector3 skillDir;
	public ESkillState eSkillState;
	public int cooldownTime;
	public long stateTime;//技能切换时间
	public long beginTime;//技能开始时间
}
