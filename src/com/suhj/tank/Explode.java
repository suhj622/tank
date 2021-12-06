package com.suhj.tank;

import java.awt.Graphics;

public class Explode {	
	public static int WIDTH = ResourceMgr.explodes[0].getWidth();
	public static int HEIGHT = ResourceMgr.explodes[0].getHeight();
	private TankFrame tf = null;
	
	private int x,y;
	
	private boolean living = true;
	private int step = 0;
	
	public Explode(int x, int y, TankFrame tf) {
		this.x = x;
		this.y = y;
		this.tf = tf;
		new Thread(() -> new Audio("audio/explode.wav").play()).start();
	}
	
	public void paint(Graphics g) {
		
		g.drawImage(ResourceMgr.explodes[step++], x, y, null);
		
		if(step >= ResourceMgr.explodes.length) {
			this.tf.explodes.remove(this);			
			step = 0;			
		}
	}		
}
