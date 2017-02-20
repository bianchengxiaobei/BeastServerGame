package com.chen.player.structs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.chen.battle.structs.BattleServerData;
import com.chen.beast.struct.Beast;
import com.chen.cooldown.structs.Cooldown;
import com.chen.login.bean.BeastData;
import com.chen.match.structs.MatchPlayer;

public class Player extends Person
{
	private static final long serialVersionUID = 4896324745542328884L;
	private Logger log = LogManager.getLogger(Player.class);
	//创建的服务器
	private int createServerId;
	//用户id
	private String userId;
	//用户名字
	private String userName;
	//服务器中文名
	private String serverName;
	//所在平台名字
	private String webName;
	//所在服务器区域id
	private int area;
	//登陆类型
	private int loginType;
	//玩家头像
	private String icon;
	//玩家昵称
	private String name;
	//玩家性别
	private byte sex;
	//玩家角色索引
	private int roleIndex;
	//玩家等级
	private int level;
	//游戏所在网关id编号
	private int gateId;
	//玩家登陆的时间
	private long loginTime;
	//玩家登陆的ip
	private String loginIp;
	//玩家在线时间长度
	private long onlineTime;
	//是否被封号
	private boolean forbid;
	//拥有的金币
	private int money;
	//拥有的点券
	private int ticket;
	//被加入黑名单次数
	private int addBlackCount;
	//经验值
	private long exp;
	//战斗力
	private int fight;
	//拥有的神兽列表
	private List<Beast> beastList;
	//玩家战斗信息
	private PlayerBattleInfo battleInfo;
	//是否是重新连接
	private boolean isReconnect;
	// 冷却map
	protected HashMap<String, Cooldown> cooldowns = new HashMap<String, Cooldown>();
	
	
	//-------------------------------zhandou---------------------
	//玩家匹配信息
	private MatchPlayer matchPlayer;	 
	//玩家服务器数据
	private BattleServerData serverData;
	public Player()
	{
		this.setBattleInfo(new PlayerBattleInfo());
		matchPlayer = new MatchPlayer(this);
		beastList = new ArrayList<Beast>();				
	}
	public HashMap<String, Cooldown> getCooldowns() {
		return cooldowns;
	}

	public void setCooldowns(HashMap<String, Cooldown> cooldowns) {
		this.cooldowns = cooldowns;
	}
	public boolean isForbid() {
		return forbid;
	}
	public void setForbid(boolean forbid) {
		this.forbid = forbid;
	}
	public int getMoney() {
		return money;
	}
	public void setMoney(int money) {
		this.money = money;
	}
	public int getTicket() {
		return ticket;
	}
	public void setTicket(int ticket) {
		this.ticket = ticket;
	}
	public int getAddBlackCount() {
		return addBlackCount;
	}
	public void setAddBlackCount(int addBlackCount) {
		this.addBlackCount = addBlackCount;
	}
	public long getExp() {
		return exp;
	}
	public void setExp(long exp) {
		this.exp = exp;
	}
	public Logger getLog() {
		return log;
	}
	public void setLog(Logger log) {
		this.log = log;
	}
	public int getCreateServerId() {
		return createServerId;
	}
	public void setCreateServerId(int createServerId) {
		this.createServerId = createServerId;
	}
	public int getArea() {
		return area;
	}
	public void setArea(int area) {
		this.area = area;
	}
	public int getLoginType() {
		return loginType;
	}
	public void setLoginType(int loginType) {
		this.loginType = loginType;
	}
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getServerName() {
		return serverName;
	}
	public void setServerName(String serverName) {
		this.serverName = serverName;
	}
	public String getWebName() {
		return webName;
	}
	public void setWebName(String webName) {
		this.webName = webName;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	public int getGateId() {
		return gateId;
	}
	public void setGateId(int gateId) {
		this.gateId = gateId;
	}
	public byte getSex() {
		return sex;
	}
	public void setSex(byte sex) {
		this.sex = sex;
	}
	public long getLoginTime() {
		return loginTime;
	}
	public void setLoginTime(long loginTime) {
		this.loginTime = loginTime;
	}
	public String getLoginIp() {
		return loginIp;
	}
	public void setLoginIp(String loginIp) {
		this.loginIp = loginIp;
	}
	public long getOnlineTime() {
		return onlineTime;
	}
	public void setOnlineTime(long onlineTime) {
		this.onlineTime = onlineTime;
	}
	public PlayerBattleInfo getBattleInfo() {
		return battleInfo;
	}
	public void setBattleInfo(PlayerBattleInfo battleInfo) {
		this.battleInfo = battleInfo;
	}
	public MatchPlayer getMatchPlayer() {
		return matchPlayer;
	}
	public void setMatchPlayer(MatchPlayer matchPlayer) {
		this.matchPlayer = matchPlayer;
	}
	public int getFight() {
		return fight;
	}
	public void setFight(int fight) {
		this.fight = fight;
	}
	public List<Beast> getBeastList() {
		return beastList;
	}
	public String getBeasListString()
	{
		StringBuilder sb = new StringBuilder();
		for (int i=0; i<this.beastList.size(); i++)
		{
			sb.append(this.beastList.get(i).getBeastTypeId());
			if (i < this.beastList.size()-1){
				sb.append(",");
			}
		}
		return sb.toString();
	}
	public void setBeastList(List<Beast> beastList) {
		this.beastList = beastList;
	}
	public List<Long> getBeastListLong()
	{
		/*List<Long> list = new ArrayList<Long>();
		Iterator<Beast> iter = this.beastList.iterator();
		while (iter.hasNext()) {
			Beast beast = (Beast) iter.next();
			list.add(beast.getBeastId());
		}
		return list;
		*/
		List<Long> list = new ArrayList<Long>();
		for (int i=0; i<3; i++)
		{
			Beast beast = this.beastList.get(i);
			list.add(beast.getBeastId());
		}
		return list;
	}
	public HashMap<Integer, BeastData> getBeastData()
	{
		HashMap<Integer, BeastData> beastMap = new HashMap<Integer, BeastData>();
		Iterator<Beast> iter = this.beastList.iterator();
		while (iter.hasNext()) {
			Beast beast = (Beast) iter.next();
			BeastData data = new BeastData();
			data.m_dwId = beast.getBeastTypeId();
			data.m_dwLevel = beast.getLevel();
			data.m_dwExp = beast.getExp();
			data.m_oSkillList = beast.getSkills();
			beastMap.put(beast.getBeastTypeId(), data);
		}
		return beastMap;
	}
	/**
	 * 根据神兽id找到神兽类型id
	 * @param beastId
	 * @return
	 */
	public int getBeastTypeId(long beastId)
	{
		for (Beast beast : this.beastList)
		{
			if (beast.getId() == beastId)
			{
				return beast.getBeastTypeId();
			}
		}
		System.out.println("找不到神兽");
		return -1;
	}
	/**
	 * 添加神兽
	 * @param beast
	 */
	public void addBeast(Beast beast)
	{
		this.beastList.add(beast);
	}
	public boolean isReconnect() {
		return isReconnect;
	}
	public void setReconnect(boolean isReconnect) {
		this.isReconnect = isReconnect;
	}
	public int getRoleIndex() {
		return roleIndex;
	}
	public void setRoleIndex(int roleIndex) {
		this.roleIndex = roleIndex;
	}
	public BattleServerData getServerData() {
		return serverData;
	}
	public void setServerData(BattleServerData serverData) {
		this.serverData = serverData;
	}
}
