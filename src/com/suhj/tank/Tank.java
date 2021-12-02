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
	private boolean shooting = false;
	
	public boolean isMoving() {
		return moving;
	}

	public void setMoving(boolean moving) {
		this.moving = moving;
	}

	public Tank(int x, int y, Dir dir) {
		super();
		this.x = x;
		this.y = y;
		this.dir = dir; 
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
	

	public boolean isShooting() {
		return shooting;
	}

	public void setShooting(boolean shooting) {
		this.shooting = shooting;
	}

	public void paint(Graphics g) {
		Color c = g.getColor();
		g.setColor(Color.YELLOW);
		g.fillRect(getX(), getY(), 50, 50);
		g.setColor(c);
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
	
}
