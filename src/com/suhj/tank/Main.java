package com.suhj.tank;


public class Main {

	public static void main(String[] args) throws InterruptedException {
		
		TankFrame tf = new TankFrame();
		
		//获取敌方坦克的数量
		int initTankCount =  Integer.parseInt((String)PropertyMgr.get("initTankCount"));
		
		//初始化敌方坦克
		for(int i = 0; i < initTankCount; i++) {
			tf.tanks.add(new Tank(50 + i*80, 200, Dir.DOWN, Group.BAD, tf));
		}
		
		//设置背景声音
		new Thread(() -> new Audio("audio/war1.wav").loop()).start();
		
		
		//在主线程每隔 50 毫秒，调用一次 repaint 方法，repaint 方法会调用 paint 方法
		while(true) {
			Thread.sleep(50); //每隔 50 毫米，窗口重画一次，调用一次 paint 方法
			tf.repaint();
		} 

	}

}
