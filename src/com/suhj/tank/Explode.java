package com.suhj.tank;

import java.awt.Graphics;

public class Explode extends GameObject {	
	public static int WIDTH = ResourceMgr.explodes[0].getWidth();
	public static int HEIGHT = ResourceMgr.explodes[0].getHeight();
	private GameModel gm = null;
	
	private int x,y;
	
	private boolean living = true;
	private int step = 0;
	
	public Explode(int x, int y, GameModel gm) {
		this.x = x;
		this.y = y;
		this.gm = gm;
		new Thread(() -> new Audio("audio/explode.wav").play()).start();
	}
	
	@Override
	public void paint(Graphics g) {
		
		g.drawImage(ResourceMgr.explodes[step++], x, y, null);
		
		if(step >= ResourceMgr.explodes.length) {
			this.gm.deleteGameObject(this);
			step = 0;
		}
	}		
}
