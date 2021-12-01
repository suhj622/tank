package com.suhj.tank;


public class Main {

	public static void main(String[] args) throws InterruptedException {
		
		TankFrame tf = new TankFrame();
		
		
		//在主线程每隔 50 毫秒，调用一次 repaint 方法，repaint 方法会调用 paint 方法
		while(true) {
			Thread.sleep(50); //每隔 50 毫米，窗口重画一次，调用一次 paint 方法
			tf.repaint();
		} 

	}

}
