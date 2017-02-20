package com.chen.battle.structs.move;

import java.util.List;

import com.chen.structs.CVector3;
import com.chen.structs.Vector3;

public class ISSMoveObject 
{
	private EMoveObjectStatus moveStatus;//移动状态
	private EMoveObjectMoveType moveType;//移动类型,寻路、强制
	private long startMoveTime;
	private float oldSpeed;
	private float forceSpeed;
	private boolean bIfSpeedChanged;
	private Vector3 askMoveDir;//请求移动方向
	private Vector3 moveDir;//实际移动方向
	private Vector3 lastMovePos;//上次移动的坐标
	private List<CVector3> path;//路径点
	private CVector3 curMoveStep;//当前路径点
}
