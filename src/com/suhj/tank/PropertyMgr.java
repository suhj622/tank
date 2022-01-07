package com.suhj.tank;

import java.io.IOException;
import java.util.Properties;

/**
 * 
 * @author Haojie
 * 
 * 2021-12-07 初始化配置文件类
 * 获取文件中配置好的属性值
 * 2022-01-06 用单例模式实现资源管理器
 */
public class PropertyMgr {
	
	private static final PropertyMgr pMgr = new PropertyMgr();
	
	private PropertyMgr() {}
	
	static Properties props = new Properties();
	
	
	static {
		try {
			props.load(PropertyMgr.class.getClassLoader().getResourceAsStream("config"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//可以添加一个返回 int 类型返回值的方法	
	public static Object get(String key) {
		if (props == null) return null;
		return props.get(key);
	}
	
	/**
	 * 返回资源管理器
	 * @return
	 */
	public static PropertyMgr getInstance() {
		return pMgr;
	}
	
	public static void main(String[] args) {
		System.out.println(PropertyMgr.get("initTankCount"));
	}

}