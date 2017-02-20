package com.chen.data.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.chen.data.DataServer;
import com.chen.data.bean.BeastBean;
import com.chen.db.DBServer;

public class BeastDao 
{
	private SqlSessionFactory sqlMapper = DataServer.getInstance().getSqlMapper();
	public List<BeastBean> select()
	{
		SqlSession session = sqlMapper.openSession();
		try {
			List<BeastBean> list = session.selectList("beastInfo.select");
			return list;
		}finally
		{
			session.close();
		}
	}
}
