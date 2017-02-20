package com.chen.login.bean;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.mina.core.buffer.IoBuffer;

import com.chen.message.Bean;

/**
 * 神兽基础信息
 * @author Administrator
 *
 */
public class BeastInfo extends Bean
{
	public HashMap<Integer,BeastData> m_oBeastMap = new HashMap<Integer, BeastData>();
	@Override
	public boolean write(IoBuffer buf)
	{
		writeInt(buf, this.m_oBeastMap.size());
		Iterator<Entry<Integer, BeastData>> iter = this.m_oBeastMap.entrySet().iterator();
		while (iter.hasNext())
		{
			Map.Entry<Integer, BeastData> entry = (Map.Entry<Integer, BeastData>)iter.next();
			Integer key = entry.getKey();
			BeastData value = entry.getValue();
			this.writeInt(buf, key);
			this.writeBean(buf, value);
		}
		return true;
	}
	@Override
	public boolean read(IoBuffer buf) {
		this.m_oBeastMap.clear();
		int num = 0;
		num = this.readInt(buf);
		for (int i=0; i<num; i++)
		{
			int key = 0;
			BeastData data = new BeastData();
			key = readInt(buf);
			data = (BeastData)readBean(buf, BeastData.class);
			this.m_oBeastMap.put(key, data);
		}
		return true;
	}
	
}
