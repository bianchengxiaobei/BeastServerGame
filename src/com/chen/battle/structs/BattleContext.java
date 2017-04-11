package com.chen.battle.structs;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.DebugGraphics;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.omg.PortableServer.ID_ASSIGNMENT_POLICY_ID;

import com.chen.battle.message.req.ReqBeastCastSkillMessage;
import com.chen.battle.message.res.ResAddRoleToSceneMessage;
import com.chen.battle.message.res.ResBeastCastSkillMessage;
import com.chen.battle.message.res.ResBeastEndCastSkillMessage;
import com.chen.battle.message.res.ResBeastEnterStageMessage;
import com.chen.battle.message.res.ResBeastMoveMessage;
import com.chen.battle.message.res.ResEndPlayerRound;
import com.chen.battle.message.res.ResEnterSceneMessage;
import com.chen.battle.message.res.ResGamePrepareMessage;
import com.chen.battle.message.res.ResHpChangedMessage;
import com.chen.battle.message.res.ResSceneLoadedMessage;
import com.chen.battle.message.res.ResSelectBeastMessage;
import com.chen.battle.message.res.ResSelectBornPosMessage;
import com.chen.battle.message.res.ResStartBeastRoundMessage;
import com.chen.battle.message.res.ResStartGameMessage;
import com.chen.battle.message.res.ResTrySelectBeastMessage;
import com.chen.battle.structs.beast.EStageType;
import com.chen.battle.structs.beast.SSBeast;
import com.chen.battle.structs.map.EMapNodeType;
import com.chen.battle.structs.map.SSMap;
import com.chen.battle.structs.map.SSMapCfg;
import com.chen.player.manager.PlayerManager;
import com.chen.player.structs.EBattleServerState;
import com.chen.player.structs.EBattleType;
import com.chen.player.structs.Player;
import com.chen.room.bean.RoomMemberData;
import com.chen.room.structs.ECampType;
import com.chen.server.BattleServer;
import com.chen.server.config.BattleConfig;
import com.chen.structs.CVector3;
import com.chen.structs.Vector3;
import com.chen.utils.MessageUtil;

public class BattleContext extends BattleServer
{
	private Logger log = LogManager.getLogger(BattleContext.class);
	private EBattleType battleType;
	private EBattleServerState battleState = EBattleServerState.eSSBS_SelectHero;
	private long battleId;
	private long battleStateTime;
	private RoomMemberData[] m_battleUserInfo = new RoomMemberData[maxMemberCount];
	private List<SSBeast> ssBeastList;//服务器神兽列表
	private SSMap map;
	private HashMap<Long,SSBeast> ssBeastMap = new HashMap<Long,SSBeast>();
	private int m_battleBeast;//战斗中神兽的数量
	private List<Long> beastOrder = new ArrayList<Long>();//神兽战斗顺序
	private int orderIndex = 0;//战斗顺序索引
	public static final int maxMemberCount = 6; 
	public static final int timeLimit = 20;
	public static final int prepareTimeLimit = 5;
	public static final int loadTimeLimit = 10;
	public EBattleType getBattleType() {
		return battleType;
	}
	public void setBattleType(EBattleType battleType) {
		this.battleType = battleType;
	}
	public long getBattleId() {
		return battleId;
	}
	public void setBattleId(long battleId) {
		this.battleId = battleId;
	}
	public EBattleServerState getBattleState() {
		return battleState;
	}
	public void setBattleState(EBattleServerState battleState) {
		this.battleState = battleState;
	}
	public RoomMemberData[] getM_battleUserInfo() {
		return m_battleUserInfo;
	}
	public void setM_battleUserInfo(RoomMemberData[] m_battleUserInfo) {
		this.m_battleUserInfo = m_battleUserInfo;
	}
	public BattleContext(EBattleType type, long battleId,List<BattleConfig> configs)
	{
		super("战斗-"+battleId,configs);
		this.battleId = battleId;
		this.battleType = type;
	}
	
	@Override
	protected void init() 
	{
		ssBeastList = new ArrayList<SSBeast>();
		 SSMapCfg.register("empire_base", EMapNodeType.MAP_NODE_EMPIRE_BASE,false);
         SSMapCfg.register("league_base", EMapNodeType.MAP_NODE_LEAGUE_BASE,false);
         SSMapCfg.register("rock", EMapNodeType.MAP_NODE_ROCK,false);
         SSMapCfg.register("shop", EMapNodeType.MAP_NODE_SHOP, false);
         SSMapCfg.register("magic_spring", EMapNodeType.MAP_NODE_MAGIC_SPRING, false);
         SSMapCfg.register("golden", EMapNodeType.MAP_NODE_GOLDEN,true);
         SSMapCfg.register("camp", EMapNodeType.MAP_NODE_CAMP, true);
         SSMapCfg.register("swamp", EMapNodeType.MAP_NODE_SWAMP,true);
         SSMapCfg.register("reborn_empire", EMapNodeType.MAP_NODE_REBORN_EMPIRE,true);
         SSMapCfg.register("reborn_league", EMapNodeType.MAP_NODE_REBORN_LEAGUE,true);
         SSMapCfg.register("desert", EMapNodeType.MAP_NODE_DESERT, true);
         SSMapCfg.register("water", EMapNodeType.MAP_NODE_WATER, true);
         SSMapCfg.register("forest", EMapNodeType.MAP_NODE_FOREST, true);
         SSMapCfg.register("money", EMapNodeType.MAP_NODE_MONEY, true);
         SSMapCfg.register("teleport", EMapNodeType.MAP_NODE_TELEPORT, true);
         SSMapCfg.register("magic_turret", EMapNodeType.MAP_NODE_MAGIC_TURRET, true);
         SSMapCfg.register("rune_array", EMapNodeType.MAP_NODE_RUNE_ARRAY,  true);
         SSMapCfg.register("golden_new", EMapNodeType.MAP_NODE_GOLDEN_NEW,  true);
         SSMapCfg.register("money_new", EMapNodeType.MAP_NODE_MONEY_NEW, true);
         SSMapCfg.register("camp_new", EMapNodeType.MAP_NODE_CAMP_NEW,  true);
         SSMapCfg.register("accelerate_array", EMapNodeType.MAP_NODE_ACCELERATE_ARRAY, true);
         SSMapCfg.register("devil_altar", EMapNodeType.MAP_NODE_DEVIL_ALTAR, true);
         SSMapCfg.register("healing_ward", EMapNodeType.MAP_NODE_HEALING_WARD, false);
         SSMapCfg.register("frost_column", EMapNodeType.MAP_NODE_ICE_WALL,  false);
         SSMapCfg.register("open_door", EMapNodeType.MAP_NODE_OPEN_DOOR,true);
         SSMapCfg.register("close_door", EMapNodeType.MAP_NODE_CLOSE_DOOR,false);
         SSMapCfg.register("door_switch", EMapNodeType.MAP_NODE_DOOR_SWITCH,true);
         SSMapCfg.register("golden_new_2", EMapNodeType.MAP_NODE_GOLDEN_NEW_2,true);
         SSMapCfg.register("stable", EMapNodeType.MAP_NODE_STABLE,true);
         SSMapCfg.register("MonkeyKing_image", EMapNodeType.MAP_NODE_MONKEY, false);
         SSMapCfg.register("totem", EMapNodeType.MAP_NODE_TOTEM, false);
         SSMapCfg.register("skill", EMapNodeType.MAP_NODE_SKILL, false);
         SSMapCfg.register("rock_prison", EMapNodeType.MAP_NODE_ROCK_PRISON,false);
         System.out.println("BattleContent:Init");
	}
	@Override
	public void run()
	{
		super.run();
		new Timer("Time out").schedule(new TimerTask() {
			@Override
			public void run() 
			{			
				BattleContext.this.checkLoadingTimeout();
				BattleContext.this.checkPrepareTimeout();
				BattleContext.this.checkSelectBeastTimeout();
			}
		},1000,1000);
	}
	public void EnterBattleState(Player player)
	{
		boolean isReconnect = player.isReconnect();
		if (isReconnect)
		{
			//通知重新连接信息
		}
		log.info("玩家"+player.getId()+"确认加入战斗房间，当前战斗状态:"+battleState.toString());
		//以后再扩展开选择符文等
	}
	/**
	 * 客户端试图选择该英雄
	 * @param player
	 * @param beastId
	 * @param suitId
	 */
	public void AskTrySelectBeast(Player player,long beastId,int beastTypeId)
	{
		boolean selected = isCanSelectBeast(player, beastId,beastTypeId);
		if (selected == false)
		{
			return ;
		}
		//选择神兽并发给其他玩家
		RoomMemberData dataInfo = getUserBattleInfo(player);
		dataInfo.setM_nSelectBeastId(beastId);
		dataInfo.setM_nSelectBeastTypeId(beastTypeId);
		ResTrySelectBeastMessage msg = new ResTrySelectBeastMessage();
		msg.setBeastId(beastId);
		msg.setBeastTypeId(beastTypeId);
		msg.setBeastLevel(dataInfo.getBeastLevel(beastTypeId));
		msg.setSkillList(dataInfo.getBeastSkillList(beastTypeId));
		MessageUtil.tell_battlePlayer_message(this, msg);
	}
	/**
	 * 玩家确认选择该神兽
	 * @param player
	 * @param beastId
	 * @param beastTypeId
	 */
	public void AskSelectBeast(Player player,long beastId)
	{
		RoomMemberData dataInfo = getUserBattleInfo(player);
		dataInfo.setM_nSelectBeastId(beastId);
		dataInfo.getSelectedBeastList().add(beastId);
		//dataInfo.setM_bHasBeastChoosed(true);
		ResSelectBeastMessage msg = new ResSelectBeastMessage();
		msg.setBeastId(beastId);
		this.beastOrder.add(beastId);
		//回馈给客户端确认选择该神兽的结果
		MessageUtil.tell_battlePlayer_message(this,msg);
		if (dataInfo.getTempBeastList().contains(beastId))
		{
			dataInfo.getTempBeastList().remove(beastId);
		}
		else
		{
			log.error("玩家不能选择该神兽");
			dataInfo.getSelectedBeastList().remove(dataInfo.getSelectedBeastList().size()-1);
		}
		if (dataInfo.getTempBeastList().size() == 0)
		{
			System.out.println("玩家"+player.getName()+"完成选择神兽");
			dataInfo.setM_bHasBeastChoosed(true);
		}
	}
	/**
	 * 通知其他玩家该神兽进入这个阶段
	 * @param beast
	 * @param stage
	 */
	public void beastEnterStage(SSBeast beast,int stage)
	{
		ResBeastEnterStageMessage msg = new ResBeastEnterStageMessage();
		msg.beastId = beast.objId;
		msg.stage = stage;
		MessageUtil.tell_battlePlayer_message(this, msg);
		beast.setStage(stage);
	}
	/**
	 * 玩家结束战斗阶段，如果玩家都已经移动并且释放技能完了，就发送给客户端玩家退出他的战斗阶段。
	 * 然后开始另外一个玩家开始阶段
	 * @param beast
	 * @param stage
	 */
	public void beastEndStage(SSBeast beast,int stage)
	{
		//记录下神兽完成了哪些阶段战斗
		if (stage == EStageType.ROLE_STAGE_ACTION.getValue())
		{
			beast.m_bHasAction = true;
		}
		if (stage == EStageType.ROLE_STAGE_MOVE.getValue())
		{
			beast.m_bHasMoved = true;
		}
		if (beast.m_bHasMoved == true && beast.m_bHasAction == true)
		{
			//发送结束该玩家回合
			ResEndPlayerRound msg = new ResEndPlayerRound();
			msg.playerId = beast.getPlayer().getId();
			MessageUtil.tell_battlePlayer_message(this, msg);			
			//发送下个玩家开始阶段
			startBeastRound();
		}
	}
	/**
	 * 神兽释放技能
	 * @param beast
	 * @param msg
	 */
	public void beastCastSkill(SSBeast beast,ReqBeastCastSkillMessage msg)
	{
		int skillId = msg.m_dwSkillId;
		long attacker = msg.m_dwRoleId;
		long beAttacker = msg.m_dwTargetRoleId;
		CVector3 targetPos = msg.m_oTargetPos;
		List<Long> hurtList = new ArrayList<Long>();
		if (skillId == 1)
		{
			hurtList.add(beAttacker);
		}
		ResBeastCastSkillMessage resmsg = new ResBeastCastSkillMessage();
		resmsg.m_dwRoleId = attacker;
		resmsg.m_dwTargetRoleId = beAttacker;
		resmsg.m_dwSkillId = skillId;
		resmsg.m_oTargetPos = targetPos;
		resmsg.m_oHurtList = hurtList;
		MessageUtil.tell_battlePlayer_message(this, resmsg);
		this.getSSBeast(beAttacker).ChangeHp(-1, (byte) 0);
		ResBeastEndCastSkillMessage resmsg1 = new ResBeastEndCastSkillMessage();
		MessageUtil.tell_battlePlayer_message(this, resmsg1);
	}
	/**
	 * 玩家发送加载完成消息
	 */
	public void EnsurePlayerLoaded(long playerId)
	{
		RoomMemberData data = this.getUserBattleInfo(playerId);
		data.setLoadCompleted(true);
		ResSceneLoadedMessage msg = new ResSceneLoadedMessage();
		msg.setM_playerId(playerId);
		MessageUtil.tell_battlePlayer_message(this, msg);
	}
	public void checkSelectBeastTimeout()
	{
		if (this.battleState != EBattleServerState.eSSBS_SelectHero)
		{
			return ;
		}
		boolean ifAllUserSelect = true;
		for (int i=0; i<maxMemberCount; i++)
		{
			if (this.m_battleUserInfo[i] != null)
			{
				if (this.m_battleUserInfo[i].isM_bHasBeastChoosed() == false)
				{
					ifAllUserSelect = false;
					break;
				}
			}
		}
		//等待时间结束
		if (ifAllUserSelect || (System.currentTimeMillis() - this.battleStateTime) * 0.001 >= timeLimit)
		{
			for (int i = 0; i < maxMemberCount; i++) {
				if (this.m_battleUserInfo[i] != null)
				{
					if (false == this.m_battleUserInfo[i].isM_bHasBeastChoosed()) {
						//如果还没有选择神兽，就随机选择一个
						if (this.m_battleUserInfo[i].getM_nSelectBeastId() == -1)
						{
							this.m_battleUserInfo[i].setM_nSelectBeastId(randomPickBeast(this.m_battleUserInfo[i].getBeastList(), this.m_battleUserInfo[i].getCampType()));
						}
						this.m_battleUserInfo[i].setM_bHasBeastChoosed(true);
						//然后将选择该神兽的消息广播给其他玩家
						ResSelectBeastMessage msg = new ResSelectBeastMessage();
						msg.setBeastId(this.m_battleUserInfo[i].getM_nSelectBeastId());
						MessageUtil.tell_battlePlayer_message(this, msg);
					}
				}
			}
			//选择神兽阶段结束，改变状态，进入准备状态
			setBattleState(EBattleServerState.eSSBS_Prepare,true);
		}
	}
	public void checkLoadingTimeout()
	{
		if (this.battleState != EBattleServerState.eSSBS_Loading)
		{
			return ;
		}
		boolean bIfAllPlayerConnect = true;
		//时间未到，则检查是否所有玩家已经连接
		if (System.currentTimeMillis() - this.battleStateTime < loadTimeLimit)
		{
			for (int i=0;i<this.m_battleUserInfo.length;i++)
			{
				if (this.m_battleUserInfo[i].getPlayerId() != 0 && !this.m_battleUserInfo[i].isLoadCompleted())
				{
					bIfAllPlayerConnect = false;
					break;
				}
			}
		}
		if (bIfAllPlayerConnect == false)
		{
			return;
		}
		//加载静态的配置文件
		this.LoadMapConfigNpc();
		//然后创建神兽
		for (int i=0;i<this.m_battleUserInfo.length;i++)
		{
			if (this.m_battleUserInfo[i] == null)
			{
				continue;
			}
			int count = this.m_battleUserInfo[i].getSelectedBeastList().size();
			if (count <= 0)
			{
				return;
			}
			for (int j=0;j<count;j++)
			{
				SSBeast beast = this.AddBeast(this.m_battleUserInfo[i], j);			
				this.m_battleBeast++;
				if (beast == null)
				{
					System.err.println("添加神兽到战场中失败");
					break;
				}
				//beast.ChangeMP(0, (byte)0);
				beast.ChangeHp(0, (byte)0);
				//beast.ChangeCP(100, false);
				this.ssBeastList.add(beast);				
			}		
		}
		//通知玩家游戏开始消息
		this.PostStartGameMsg();
		this.setBattleState(EBattleServerState.eSSBS_Playing);
	}
	public SSBeast AddBeast(RoomMemberData data,int index)
	{
		Player player = PlayerManager.getInstance().getPlayer(data.getPlayerId());
		long selectBeastId = data.getSelectedBeastList().get(index);
		int selectBeastTypeId = player.getBeastTypeId(selectBeastId);
		//加载Beast的配置信息
		SSBeast beast = new SSBeast(player,selectBeastId,data.getCampType(),this);
		beast.LoadBeastConfig(selectBeastTypeId);
		beast.beginActionWait(false);
		beast.LoadPassitiveSkill();
		return beast;
	}
	/**
	 * 神兽进入战场
	 * @param beast
	 * @param pos
	 */
	public void BeastEnterBattle(SSBeast beast,CVector3 pos)
	{	
		ResAddRoleToSceneMessage msg = new ResAddRoleToSceneMessage();
		msg.beastId = beast.objId;
		msg.pos = pos;
		MessageUtil.tell_battlePlayer_message(this, msg);
		beast.SetActionStateCPos(pos);
		this.ssBeastMap.put(beast.objId, beast);
		
		RoomMemberData data = getUserBattleInfo(beast.getPlayer());
		if (data != null)
		{
			int index = data.getSelectedBeastList().indexOf(beast.objId);
			if (data.getSelectedBeastList().size() == 3 && index < 2)
			{
				ResSelectBornPosMessage msg2 = new ResSelectBornPosMessage();
				msg2.setBeastId(data.getSelectedBeastList().get(index+1));
				MessageUtil.tell_player_message(beast.getPlayer(), msg2);
			}
		}
		//当全部进入到场景中，就发送战斗开始消息
		if (this.ssBeastMap.size() == maxMemberCount)
		{
			this.startBeastRound();
		}		
	}
	/**
	 * 神兽开始他的战斗阶段
	 */
	public void startBeastRound()
	{
		//按理来讲这里本来是双方玩家互换，而不是神兽顺序
		long beastId = this.beastOrder.get(this.orderIndex);
		ResStartBeastRoundMessage msg = new ResStartBeastRoundMessage();
		msg.beastId = beastId;
		MessageUtil.tell_battlePlayer_message(this, msg);
		this.orderIndex++;
	}
	/**
	 * 通知其他玩家神兽移动了
	 * @param beast
	 * @param pos
	 */
	public void beastMove(SSBeast beast,CVector3 pos)
	{
		ResBeastMoveMessage msg = new ResBeastMoveMessage();
		msg.beastId = beast.objId;
		if (this.map != null)
		{
			List<CVector3> path = this.map.FindPath(10000000, beast.actionState.cPos, pos, false);
			if (path == null)
			{
				System.err.println("Path == null");
				return;
			}
			for (CVector3 v : path)
			{
				System.out.println(v.toString());
			}
			msg.pos = path;
		}
		MessageUtil.tell_battlePlayer_message(this, msg);
		//玩家已经移动就设置移动状态为已经移动
		beast.m_bHasMoved = true;
		//如果玩家还没有进入到释放技能阶段，就发送给玩家进入到技能阶段
		if (beast.m_bHasAction == false)
		{
			ResBeastEnterStageMessage msg1 = new ResBeastEnterStageMessage();
			msg1.beastId = beast.objId;
			msg1.stage = EStageType.ROLE_STAGE_ACTION.getValue();
			MessageUtil.tell_battlePlayer_message(this, msg1);
		}
		beast.actionState.cPos = pos;
	}
	/**
	 * 根据id取得服务器神兽
	 * @param beastId
	 * @return
	 */
	public SSBeast getSSBeast(long beastId)
	{
		for (SSBeast beast : this.ssBeastList)
		{
			if (beast.objId == beastId)
			{
				return beast;
			}
		}
		return null;
	}
	public void checkPrepareTimeout()
	{
		if (this.battleState != EBattleServerState.eSSBS_Prepare)
		{
			return ;
		}
		if (System.currentTimeMillis() - this.battleStateTime > prepareTimeLimit * 1000)
		{
			this.setBattleState(EBattleServerState.eSSBS_Loading, true);
		}
	}
	/**
	 * 改变游戏状态
	 * @param state
	 * @param isSendToClient
	 */
	public void setBattleState(EBattleServerState state,boolean isSendToClient)
	{
		this.battleState = state;
		this.battleStateTime = System.currentTimeMillis();
		if (isSendToClient)
		{
			switch (state) {
			case eSSBS_Prepare:
				//通知客户端开始进入准备状态
				ResGamePrepareMessage pre_msg = new ResGamePrepareMessage();
				pre_msg.setTimeLimit(prepareTimeLimit);
				MessageUtil.tell_battlePlayer_message(this, pre_msg);
				break;
			case eSSBS_Loading:
				//通知客户端开始加载场景
				ResEnterSceneMessage scene_msg = new ResEnterSceneMessage();
				MessageUtil.tell_battlePlayer_message(this, scene_msg);
				break;			
			default:
				break;
			}
		}
	}
	/**
	 * 角色进入战斗
	 * @param unit
	 * @param pos
	 * @param dir
	 * @return
	 */
	public boolean enterBattle(SSGameUnit unit,Vector3 pos,Vector3 dir)
	{
		return true;
	}
	/**
	 * 加载地图配置
	 */
	public void LoadMapConfigNpc()
	{
		map = new SSMap();
		map.Init(0, "server-config/map-config.xml");
	}
	/**
	 * 是否玩家能选择该神兽
	 * @param player
	 * @param beastId
	 * @return
	 */
	private boolean isCanSelectBeast(Player player,long beastId,int beastTypeId)
	{
		if (player == null)
		{
			return false;
		}
		if (this.battleState != EBattleServerState.eSSBS_SelectHero)
		{
			return false;
		}
		//获取该玩家的信息
		RoomMemberData info =getUserBattleInfo(player);
		if (info.isM_bHasBeastChoosed() || info.getM_nSelectBeastTypeId() == beastTypeId)
		{
			return false;
		}
		boolean ifInBeastList = false;
		Iterator<Long> iter = info.getBeastList().iterator();
		while (iter.hasNext()) {
			Long beast = (Long) iter.next();
			if (beast == beastId)
			{
				ifInBeastList = true;
				break;
			}
		}
		if (false == ifInBeastList)
		{
			return false;
		}
		for (int i=0; i<6; i++)
		{
			if (this.m_battleUserInfo[i] != null){
				if (this.m_battleUserInfo[i].getPlayerId() == player.getId())
				{
					continue;
				}
				if (this.m_battleUserInfo[i].getM_nSelectBeastTypeId() == beastTypeId)
				{
					return false;
				}
			}
		}
		return true;
	}
	/**
	 * 取得随机神兽
	 * @param pickBeastList
	 * @param camType
	 * @return
	 */
	private long randomPickBeast(List<Long> pickBeastList,ECampType camType)
	{
		List<Long> canChooseList = new ArrayList<Long>();
		if (pickBeastList == null || pickBeastList.size() == 0)
		{
			for (int i=0; i<pickBeastList.size(); i++)
			{
				boolean isBeastUsed = false;
				for (int j=0; j<maxMemberCount; j++)
				{
					if (this.m_battleUserInfo[j].getM_nSelectBeastId() == pickBeastList.get(i).longValue() 
							&& this.m_battleUserInfo[j].getCampType() == camType)
					{
						isBeastUsed = true;
						break;
					}
				}
				if (false == isBeastUsed)
				{
					canChooseList.add(pickBeastList.get(i));
				}
			}			
		}
		return canChooseList.get((int) (Math.random()*canChooseList.size()));		
	}
	/**
	 * 根据玩家取得玩家数据
	 * @param player
	 * @return
	 */
	private RoomMemberData getUserBattleInfo(Player player)
	{
		if (player == null)
		{
			return null;
		}
		for (int i=0; i<this.m_battleUserInfo.length; i++)
		{
			if (this.m_battleUserInfo[i] == null)
			{
				continue;
			}
			if (this.m_battleUserInfo[i].getPlayerId() == player.getId())
			{
				return this.m_battleUserInfo[i];
			}
		}
		return null;
	}
	/**
	 * 根据玩家ID取得玩家数据
	 * @param player
	 * @return
	 */
	private RoomMemberData getUserBattleInfo(long playerId)
	{
		if (playerId == 0)
		{
			return null;
		}
		for (int i=0; i<this.m_battleUserInfo.length; i++)
		{
			if (this.m_battleUserInfo[i] == null)
			{
				continue;
			}
			if (this.m_battleUserInfo[i].getPlayerId() == playerId)
			{
				return this.m_battleUserInfo[i];
			}
		}
		return null;
	}
	private void PostStartGameMsg()
	{
		for (RoomMemberData data : this.m_battleUserInfo)
		{
			if (data == null)
			{
				continue;
			}
			ResStartGameMessage msg = new ResStartGameMessage();
			msg.beastId = data.getSelectedBeastList().get(0);
			msg.empireHp = 10;
			msg.leagueHp = 10;
			msg.playerOrder = beastOrder;
			msg.timeLimit = 10;
			ResSelectBornPosMessage msg2 = new ResSelectBornPosMessage();
			msg2.setBeastId(data.getSelectedBeastList().get(0));
			MessageUtil.tell_player_message(PlayerManager.getInstance().getPlayer(data.getPlayerId()), msg);
			MessageUtil.tell_player_message(PlayerManager.getInstance().getPlayer(data.getPlayerId()), msg2);
		}	
	}
	public int getBattleBeast() {
		return m_battleBeast;
	}
	public void setBattleBeast(int m_battleBeast) {
		this.m_battleBeast = m_battleBeast;
	}
}
