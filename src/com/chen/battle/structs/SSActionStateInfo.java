package com.chen.battle.structs;

import com.chen.structs.CVector3;
import com.chen.structs.Vector3;

public class SSActionStateInfo
{
	public EActionState eActionState;
	public long timeMilsec;
	public Vector3 vPos;
	public Vector3 vDir;
	public CVector3 cPos;
	public int skillId;
	public Vector3 skillTargetPos;
	public long skillTargetId;
	public float disMove;
	public SSActionStateInfo()
	{
		this.eActionState = EActionState.eActionStaet_Wait;
		this.skillId = 0;
		this.disMove = 0.0f;
	}
	
}
