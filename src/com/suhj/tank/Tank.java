package com.suhj.tank;

import java.awt.Color;
import java.awt.Graphics;

/**
 * 坦克类
 * @author Haojie
 *
 */
public class Tank {
	
	private int x,y;
	private Dir dir;
	private final int SPEED = 5;
	private boolean moving = false;
	private boolean living = true ;
	
	public static int WIDTH = ResourceMgr.tankD.getWidth();
	public static int HEIGHT = ResourceMgr.tankD.getHeight();	
 	
	private TankFrame tf;
	
	public boolean isMoving() {
		return moving;
	}

	public void setMoving(boolean moving) {
		this.moving = moving;
	}

	public Tank(int x, int y, Dir dir, TankFrame tf) {
		super();
		this.x = x;
		this.y = y;
		this.dir = dir;
		this.tf = tf;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public Dir getDir() {
		return dir;
	}

	public void setDir(Dir dir) {
		this.dir = dir;
	}

	public int getSpeed() {
		return SPEED;
	}
	
	public static int getWIDTH() {
		return WIDTH;
	}

	public static int getHEIGHT() {
		return HEIGHT;
	}

	public void paint(Graphics g) {
		
//		Color c = g.getColor();
//		g.setColor(Color.YELLOW);
//		g.fillRect(getX(), getY(), 50, 50);
//		g.setColor(c);
		
		if(!living) tf.tanks.remove(this);
		
		switch(dir) {
		
		case LEFT:
			g.drawImage(ResourceMgr.tankL, x, y, null);
			break;
		case UP:
			g.drawImage(ResourceMgr.tankU, x, y, null);
		    break;
		case RIGHT:
			g.drawImage(ResourceMgr.tankR, x, y, null);
		    break;
		case DOWN:
			g.drawImage(ResourceMgr.tankD, x, y, null);
		    break;		
		
		}
		
		//g.drawImage(ResourceMgr.tankL, x, y, null);
		
		move();		
		
	}

	private void move() {
		
		if(!moving) return;
		
		switch(dir) {
		case LEFT:
			x -= SPEED;
			break;
		case UP:
			y -= SPEED;
		    break;
		case RIGHT:
			x += SPEED;
		    break;
		case DOWN:
			y += SPEED;
		    break;		    
		}
		
	}

	public void fire() {
		int bX = this.getX() + Tank.WIDTH/2 - Bullet.WIDTH/2;
		int bY = this.getY() + Tank.HEIGHT/2 - Bullet.HEIGHT/2;
		tf.bullets.add(new Bullet(bX, bY, this.getDir(), this.tf)); 
	}

	public void die() {
		this.living = false;		
	}
	
}
