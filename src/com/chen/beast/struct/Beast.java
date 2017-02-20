package com.chen.beast.struct;


import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.chen.config.Config;
import com.chen.data.bean.BeastBean;
import com.chen.data.manager.DataManager;
import com.chen.map.structs.IMapObject;
import com.chen.player.structs.Person;
import com.chen.player.structs.Player;
import com.chen.structs.CVector3;

public class Beast extends Person implements IMapObject
{
	private static final long serialVersionUID = -1748060887440608465L;
	private static final Logger log = LogManager.getLogger(Beast.class);
	private long beastId;
	private int beastTypeId;
	//拥有者的id
	private long ownerId;
	private int level;
	private int exp;
	private List<Integer> skills = new ArrayList<Integer>();//所有技能（包括被动技能）
	
	public Beast(Player player,long beastId,int beastTypeId)
	{
		this.beastId = beastId;
		this.id = Config.getId();
		this.ownerId = player.getId();
		this.level = 1;
		this.beastTypeId = beastTypeId;
		this.exp = 0;
		BeastBean bean = DataManager.getInstance().beastContainer.getMap().get(beastTypeId);
		if (bean != null)
		{
			String[] skills = bean.getSkills().split(",");
			for (int i=0; i<skills.length; i++)
			{
				this.skills.add(Integer.valueOf(skills[i]));
			}
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public int getExp() {
		return exp;
	}

	public void setExp(int exp) {
		this.exp = exp;
	}

	@Override
	public int getServerId() {
		return 0;
	}

	@Override
	public int getMapId() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getBattleLine() {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public CVector3 getCVector3() {
		// TODO Auto-generated method stub
		return null;
	}
	public long getBeastId() {
		return beastId;
	}

	public void setBeastId(int beastId) {
		this.beastId = beastId;
	}

	public List<Integer> getSkills() {
		return skills;
	}

	public void setSkills(List<Integer> skills) {
		this.skills = skills;
	}

	public long getOwnerId() {
		return ownerId;
	}

	public void setOwnerId(long ownerId) {
		this.ownerId = ownerId;
	}
















	public int getBeastTypeId() {
		return beastTypeId;
	}
















	public void setBeastTypeId(int beastTypeId) {
		this.beastTypeId = beastTypeId;
	}
}
