package com.chen.data.container;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import com.chen.data.bean.BeastBean;
import com.chen.data.dao.BeastDao;

public class BeastContainer 
{
	public HashMap<Integer, BeastBean> getMap() {
		return map;
	}

	public void setMap(HashMap<Integer, BeastBean> map) {
		this.map = map;
	}

	private List<BeastBean> list;
	private HashMap<Integer, BeastBean> map = new HashMap<Integer, BeastBean>();
	private BeastDao dao = new BeastDao();
	
	public void load()
	{
		list = dao.select();
		Iterator<BeastBean> iter = list.iterator();
		while (iter.hasNext()) {
			BeastBean beastBean = (BeastBean) iter.next();
			System.out.println(beastBean.getBeastId());
			map.put(beastBean.getBeastId(), beastBean);
		}
	}
}
