package com.chen.battle.structs.beast;

import com.chen.battle.message.res.ResHpChangedMessage;
import com.chen.battle.message.res.ResPlayerMPChangedMessage;
import com.chen.battle.message.res.ResPlayerMoneyChangedMessage;
import com.chen.battle.structs.BattleContext;
import com.chen.battle.structs.BattleServerData;
import com.chen.battle.structs.SSGameUnit;
import com.chen.player.structs.Player;
import com.chen.room.structs.ECampType;
import com.chen.utils.MessageUtil;
public class SSBeast extends SSGameUnit
{
	private Player m_player;
	private int hp;
	private int stage;
	public boolean m_bHasMoved = false;//是否已经结束移动阶段
	public boolean m_bHasAction = false;//是否已经结束释放技能阶段
	public SSBeast(Player player, long beastId,ECampType eCampType,BattleContext battle)
	{
		this.m_player = player;
		this.objId = beastId;
		this.eCampType = eCampType;
		this.battle = battle;
		this.hp = 10;
	}
	/**
	 * 加载神兽的基础配置
	 */
	public void LoadBeastConfig(int beastTypeId)
	{
		
	}
	/**
	 * 加载技能
	 */
	public void LoadPassitiveSkill()
	{
		
	}
	/**
	 * 改变金币
	 * @param value
	 * @param bIfIncomeByKill
	 * @return
	 */
	public boolean ChangeCP(int value,boolean bIfIncomeByKill)
	{
		BattleServerData data = this.m_player.getServerData();
		if (data == null)
		{
			this.m_player.setServerData(new BattleServerData());
			data = this.m_player.getServerData();
		}
		int cp = data.personCP + value;
		if (cp < 0)
		{
			System.out.println("金币不足");
			return false;
		}
		if (value > 0)
		{
			data.totalPersonCp += value;
		}
		data.personCP = cp;
		//发送消息给自身客户端
		this.SyncCpToClient();
		//如果是击杀获得的金币
		if (bIfIncomeByKill)
		{
			//发送改变值
		}
		return true;
	}
	public boolean ChangeMP(int value,byte reason)
	{
		BattleServerData data = this.m_player.getServerData();
		if (data == null)
		{
			this.m_player.setServerData(new BattleServerData());
			data = this.m_player.getServerData();
		}
		int mp = data.curMPValue + value;
		if (mp < 0)
		{
			System.out.println("魔法值小于0");
			return false;
		}
		data.curMPValue += value;
		this.SyncMpToClient();
		return true;
	}
	public void ChangeHp(int value,byte reason)
	{
		int hp = this.hp + value;
		if (hp < 0)
		{
			//死亡
		}
		this.hp += value;
		if (value > 0)
		{
			SyncHp(hp,(byte)0);
		}
		else
		{
			SyncHp(hp, (byte)1);
		}
	}
	/**
	 * 异步金币增加消息给自身客户端
	 */
	private void SyncCpToClient() 
	{
		ResPlayerMoneyChangedMessage msg = new ResPlayerMoneyChangedMessage();
		msg.setPlayerId(this.playerId);
		msg.setMoney(m_player.getServerData().personCP);
		MessageUtil.tell_player_message(this.m_player,msg);
	}
	private void SyncMpToClient()
	{
		ResPlayerMPChangedMessage msg = new ResPlayerMPChangedMessage();
		msg.setPlayerId(this.playerId);
		msg.setMp(m_player.getServerData().curMPValue);
		MessageUtil.tell_player_message(this.m_player,msg);
	}
	private void SyncHp(int value,byte reason)
	{
		ResHpChangedMessage msg = new ResHpChangedMessage();
		msg.setBeastId(this.objId);
		msg.setHp(value);
		msg.setReason(reason);
		MessageUtil.tell_battlePlayer_message(getCurBattle(), msg);
	}
	public void reset()
	{
		this.m_bHasAction = false;
		this.m_bHasMoved = false;
	}
	public Player getPlayer() {
		return m_player;
	}
	public void setPlayer(Player m_player) {
		this.m_player = m_player;
	}
	public ECampType geteCampType() {
		return eCampType;
	}
	public void seteCampType(ECampType eCampType) {
		this.eCampType = eCampType;
	}
	public int getHp() {
		return hp;
	}
	public void setHp(int hp) {
		this.hp = hp;
	}
	public int getStage() {
		return stage;
	}
	public void setStage(int stage) {
		if (this.stage != stage)
		{
			this.stage = stage;
		}
		else
		{
			System.out.println("神兽已经在该战斗阶段了");
		}
	}
}
