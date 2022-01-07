package com.suhj.tank;


public class Main {

	public static void main(String[] args) throws InterruptedException {
		
		//set GameModel which holds all of game objects and deal with the contact between TankFrame and Game-Objects
		GameModel gm = new GameModel();		
		TankFrame tf = new TankFrame(gm);		
		
		//设置背景声音
		new Thread(() -> new Audio("audio/war1.wav").loop()).start();
				
		//在主线程每隔 50 毫秒，调用一次 repaint 方法，repaint 方法会调用 paint 方法
		while(true) {
			Thread.sleep(50); //每隔 50 毫米，窗口重画一次，调用一次 paint 方法
			tf.repaint();
		} 

	}

}
