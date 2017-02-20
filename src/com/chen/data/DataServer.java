package com.chen.data;

import java.io.FileInputStream;
import java.io.InputStream;

import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;



/**
 * 数据库服务器
 * @author Administrator
 *
 */
public class DataServer 
{
	private Logger log = LogManager.getLogger(DataServer.class);
	private static Object obj = new Object();
	private static DataServer server;
	private SqlSessionFactory sqlMapper;

	private DataServer() {
		try {
			String resource = "server-config/data-config.xml";
			InputStream in = new FileInputStream(resource);
			sqlMapper = new SqlSessionFactoryBuilder().build(in);
			in.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static DataServer getInstance() {
		synchronized (obj) {
			if (server == null)
				server = new DataServer();
		}
		return server;
	}

	public SqlSessionFactory getSqlMapper() {
		return sqlMapper;
	}

	public void setSqlMapper(SqlSessionFactory sqlMapper) {
		this.sqlMapper = sqlMapper;
	}
}
