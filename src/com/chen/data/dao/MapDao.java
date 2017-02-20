package com.chen.data.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.chen.data.DataServer;
import com.chen.data.bean.MapBean;

public class MapDao 
{
	private SqlSessionFactory sqlMapper = DataServer.getInstance().getSqlMapper();
	public List<MapBean> select()
	{
		SqlSession session = sqlMapper.openSession();
		try {
			List<MapBean> list = session.selectList("map.select");
			return list;
		} finally
		{
			session.close();
		}
	}
}
