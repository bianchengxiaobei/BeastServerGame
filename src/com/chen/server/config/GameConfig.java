package com.chen.server.config;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class GameConfig 
{
	private Logger log = LogManager.getLogger(GameConfig.class);
	private ConcurrentHashMap<Integer, Integer> servers = new ConcurrentHashMap<Integer, Integer>();
	private ConcurrentHashMap<Integer, Date> serverTimes = new ConcurrentHashMap<Integer, Date>();
	private static SimpleDateFormat format = new SimpleDateFormat("yyyy-mm-dd HH:mm:ss");
	public ConcurrentHashMap<Integer, Integer> getServers() {
		return servers;
	}
	public void setServers(ConcurrentHashMap<Integer, Integer> servers) {
		this.servers = servers;
	}
	public ConcurrentHashMap<Integer, Date> getServerTimes() {
		return serverTimes;
	}
	public void setServerTimes(ConcurrentHashMap<Integer, Date> serverTimes) {
		this.serverTimes = serverTimes;
	}
}
