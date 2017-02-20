package com.chen.battle.structs;

import com.chen.battle.structs.move.ISSMoveObject;
import com.chen.battle.structs.skill.SSSkill;
import com.chen.room.structs.ECampType;
import com.chen.structs.CVector3;

public class SSGameObject extends ISSMoveObject
{
	protected long playerId;
	protected long objId;
	protected long attackId;
	protected long attackMilses;
	protected float colliderRadius;
	protected ECampType eCampType;
	protected BattleContext battle;
	protected SSActionStateInfo actionState;
	protected SSSkill skill;
	protected int objType;
	public SSGameObject()
	{
		this.actionState = new SSActionStateInfo();
	}
	public void SetActionState(EActionState state)
	{
		this.actionState.eActionState = state;
		this.actionState.timeMilsec = System.currentTimeMillis();
	}
	public void SetActionStateCPos(CVector3 pos)
	{
		this.actionState.cPos = pos;
	}
	/**
	 * 取得现在的战斗线程
	 * @return
	 */
	public BattleContext getCurBattle()
	{
		if (this.battle != null)
		{
			return this.battle;
		}
		return null;
	}
}
