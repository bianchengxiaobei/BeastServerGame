package com.chen.db.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.chen.db.DBServer;
import com.chen.db.bean.Role;

public class RoleDao 
{
	private Logger log = LogManager.getLogger(RoleDao.class);
	private SqlSessionFactory sqlMapper = DBServer.getInstance().getSqlMapper();
	/**
	 * 插入玩家角色数据
	 * @param role
	 * @return 玩家所在的列
	 */
	public int insert(Role role)
	{
		SqlSession session = sqlMapper.openSession();
		try {
			int rows = session.insert("game_role.insert",role);
			session.commit();
			return rows;
		}finally{
			session.close();
		}
	}
	public List<String> selectNames()
	{
		SqlSession session = sqlMapper.openSession();
		try {
			long start = System.currentTimeMillis();
			List<String> list = session.selectList("game_role.selectNames");
			long end = System.currentTimeMillis();
			log.debug("选择角色名字列表需要时间"+(end - start));
			return list;
		} catch (Exception e) {
			log.error(e,e);
			return null;
		}finally
		{
			session.close();
		}
	}
	public Role selectById(long id)
	{
		SqlSession session = sqlMapper.openSession();
		try {
			long start = System.currentTimeMillis();
			Role role = (Role)session.selectOne("game_role.selectById",id);
			long end = System.currentTimeMillis();
			log.debug("选择角色根据id消耗的时间："+(end - start));
			return role;
		} catch (Exception e) {
			log.error(e,e);
			return null;
		}finally
		{
			session.close();
		}
	}
	public int update(Role role)
	{
		SqlSession session = sqlMapper.openSession();
		try 
		{
			long start = System.currentTimeMillis();
			int rows = session.update("game_role.update", role);
			session.commit();
			long end = System.currentTimeMillis();
			log.debug("Update角色耗费："+(end-start));
			return rows;
		} catch (Exception e) {
			log.error(e,e);
			return -1;
		}finally
		{
			session.close();
		}
	}
}
