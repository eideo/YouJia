package com.arc.util;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 用于数据库备份与还原时，获取数据库的userName,password,dbName等信息
 */

public class DBUtil {
	static org.apache.commons.dbcp.BasicDataSource dataSource = null;

	static {
		// 读取applicationContext.xml文件
		ApplicationContext context = new ClassPathXmlApplicationContext(
				"applicationContext.xml");
		dataSource = (org.apache.commons.dbcp.BasicDataSource) context
				.getBean("dataSource");
	}

	public String getDUserName() {
		return dataSource.getUsername();
	}

	public String getDbPassword() {
		return dataSource.getPassword();
	}

	// Url 的格式为： jdbc:mysql://192.168.0.3:3306/haili
	public String getDbName() {
		final int port_DbName = 3;
		final int dbNameFirstIndex = 5;
		String urlArray[] = dataSource.getUrl().split(":");
		return urlArray[port_DbName].substring(dbNameFirstIndex,
				urlArray[port_DbName].length());
	}

	// 获取远程主机的IP地址
	public String getDbHostIp() {
		final int ipPart = 2;
		final int ipFirstIndex = 2;
		String urlArray[] = dataSource.getUrl().split(":");
		return urlArray[ipPart].substring(ipFirstIndex,
				urlArray[ipPart].length());
	}
}
