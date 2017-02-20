package com.chen.cooldown.manager;

import java.util.HashMap;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.chen.cooldown.structs.Cooldown;
import com.chen.cooldown.structs.CooldownType;
import com.chen.player.structs.Player;
import com.chen.pool.MemoryObject;
import com.chen.pool.MemoryPool;

/**
 * 冷却管理器
 * @author chen
 *
 */
public class CooldownManager 
{
	private static Logger log = LogManager.getLogger(CooldownManager.class);
	private static CooldownManager manager;
	private static Object obj = new Object();
	// 所有冷却对象池
	private MemoryPool<Cooldown> cooldownPool = new MemoryPool<Cooldown>();

	private CooldownManager() {

	}

	public static CooldownManager getInstance() {
		synchronized (obj) {
			if (manager == null) {
				manager = new CooldownManager();
			}
		}
		return manager;
	}

	/**
	 * 添加冷却类型
	 * 
	 * @param player
	 * @param cooldownType
	 * @param key
	 * @param delay
	 */
	public void addCooldown(Player player,CooldownType cooldownType, String key,int delay)
	{
		 String cooldownKey = null;
		 //当冷却key为空，就直接从枚举里面取得string类型，否则冷却类型+“_”+自己的key
		 if (null == key)
		 {
			 cooldownKey = cooldownType.getValue();
		 }
		 else
		 {
			 cooldownKey = cooldownType.getValue() + "_" + key;
		 }
		 if (player.getCooldowns().containsKey(cooldownKey))
		 {
			 Cooldown cooldown = player.getCooldowns().get(cooldownKey);
			 cooldown.setStart(0);//这里设置当前回合
			 cooldown.setDelay(delay);//设置该冷却持续
		 }
		 else
		 {
			 Cooldown cooldown = createCooldown();
			 cooldown.setType(cooldownType.getValue());
			 cooldown.setKey(cooldownKey);
			 cooldown.setStart(0);//当前回合
			 cooldown.setDelay(delay);
			 player.getCooldowns().put(cooldownKey, cooldown);
		 }
	}
	/**
	 * 取得技能冷却时间
	 * @param player
	 * @param type
	 * @param key
	 * @return
	 */
	public int getCooldownTime(Player player,CooldownType type, String key)
	{
		String cooldownKey = null;
		if (key == null)
		{
			cooldownKey = type.getValue();
		}
		else
		{
			cooldownKey = type.getValue() + "_" + key;
		}
		Cooldown cooldown = player.getCooldowns().get(cooldownKey);
		if (cooldown != null)
		{
			return cooldown.getRemainTime();
		}
		return 0;
	}
	public void removeCooldown(Player player,CooldownType type, String key)
	{
		String cooldownKey = null;
		if (key == null)
		{
			cooldownKey = type.getValue();
		}
		else
		{
			cooldownKey = type.getValue() + "_" + key;
		}
		if (player.getCooldowns().containsKey(cooldownKey))
		{
			Cooldown cooldown = player.getCooldowns().remove(cooldownKey);
			cooldownPool.put(cooldown);
		}
	}
	/**
	 * 是否在冷却
	 * @param player
	 * @param type
	 * @param key
	 * @return
	 */
	public boolean isCooldowning(Player player,CooldownType type,String key)
	{
		String cooldownKey = null;
		if (key == null)
		{
			cooldownKey = type.getValue();
		}
		else
		{
			cooldownKey = type.getValue() + "_" + key;
		}
		if (player.getCooldowns().containsKey(cooldownKey))
		{
			Cooldown cooldown = player.getCooldowns().get(cooldownKey);
			if (0 - (cooldown.getStart() + cooldown.getDelay()) >= 0)
			{
				return false;
			}
			else
			{
				return true;
			}
			
		}
		return false;
	}
	private Cooldown createCooldown() {
		try {
			return cooldownPool.get(Cooldown.class);
		} catch (Exception e) {
			log.error(e,e);
			return null;
		}
	}
}
