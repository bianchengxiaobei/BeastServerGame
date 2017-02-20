package com.chen.battle.structs;

public class SSGameUnit extends SSGameObject
{
	
	
	/**
	 * 设置角色进入Wait状态
	 * @param bIsNotify
	 */
	public void beginActionWait(boolean bIsNotify)
	{
		BattleContext battle = getCurBattle();
		if (this.actionState.eActionState != EActionState.eActionStaet_Wait)
		{
			SetActionState(EActionState.eActionStaet_Wait);
			if (battle != null && bIsNotify)
			{
				//这里是通知其他客户端的角色，该角色是进入wait状态
			}
		}
	}
}
