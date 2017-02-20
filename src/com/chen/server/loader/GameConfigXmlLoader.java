package com.chen.server.loader;

import java.io.FileInputStream;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.chen.server.config.GameConfig;


public class GameConfigXmlLoader
{
	private static SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	private Logger log = LogManager.getLogger(GameConfigXmlLoader.class);
	
	public GameConfig load(String file)
	{
		try {
			DocumentBuilder builder = DocumentBuilderFactory.newInstance()
					.newDocumentBuilder();
			InputStream in = new FileInputStream(file);
			Document doc = builder.parse(in);
			NodeList list = doc.getElementsByTagName("servers");
			if (list.getLength() > 0) {
				GameConfig config = new GameConfig();
				Node node = list.item(0);
				NodeList childs = node.getChildNodes();

				for (int i = 0; i < childs.getLength(); i++) {
					if (("server").equals(childs.item(i).getNodeName())) {
						NodeList schilds = childs.item(i).getChildNodes();
						ServerConfig sconfig = new ServerConfig();
						for (int j = 0; j < schilds.getLength(); j++) {
							if (("area").equals(schilds.item(j)
									.getNodeName())) {
								sconfig.setCountry(Integer.parseInt(schilds
										.item(j).getTextContent().trim()));
							} else if (("server-id").equals(schilds.item(j)
									.getNodeName())) {
								sconfig.setServerId(Integer.parseInt(schilds
										.item(j).getTextContent().trim()));
							} else if (("create-time").equals(schilds.item(j)
									.getNodeName())) {
								sconfig.setCreateTime(schilds.item(j)
										.getTextContent().trim());
							}
						}
						config.getServers().put(sconfig.getServerId(),
								sconfig.getCountry());
						Date date = getConfigTimeToDate(sconfig.getCreateTime());
						config.getServerTimes()
								.put(sconfig.getServerId(), date);
					}
				}

				in.close();
				return config;
			}
		} catch (Exception e) {
			log.error(e, e);
		}
		return null;
	}
	public Date getConfigTimeToDate(String str){
		if(str!=null && !("").equals(str)){
			try{
				return format.parse(str);
			}catch (Exception e) {
				log.error("开区时间设置错误", e);
			}
		}
		return null;
	}
	private class ServerConfig 
	{
		private int area;

		private int serverId;

		private String createTime;

		public int getCountry() {
			return area;
		}

		public void setCountry(int country) {
			this.area = country;
		}

		public int getServerId() {
			return serverId;
		}

		public void setServerId(int serverId) {
			this.serverId = serverId;
		}

		public String getCreateTime() {
			return createTime;
		}

		public void setCreateTime(String createTime) {
			this.createTime = createTime;
		}

	}
}
