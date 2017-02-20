package com.chen.db;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class DBServer 
{
	private Logger log = LogManager.getLogger(DBServer.class);

	private static Object obj = new Object();
	// sql工厂
	private SqlSessionFactory sqlMapper;

	private static DBServer server;

	private DBServer() {
		try {
			String resource = "server-config/db-config.xml";
			InputStream in = new FileInputStream(resource);
			sqlMapper = new SqlSessionFactoryBuilder().build(in);
			in.close();
		} catch (IOException e) {
			log.error(e, e);
		}
	}

	public static DBServer getInstance() {
		synchronized (obj) {
			if (server == null)
				server = new DBServer();
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
