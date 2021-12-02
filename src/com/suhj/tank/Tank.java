package com.suhj.tank;

import java.awt.Graphics;

/**
 * 坦克类
 * @author Haojie
 *
 */
public class Tank {
	
	private int x,y;
	private Dir dir;
	private final int SPEED = 10;
	
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

	public void paint(Graphics g) {
		
		g.fillRect(getX(), getY(), 50, 50);
		
		switch(getDir()) {
		case LEFT:
			setX(getX() - getSpeed());
			break;
		case UP:
			setY(getY() - getSpeed());
		    break;
		case RIGHT:
			setX(getX() + getSpeed());
		    break;
		case DOWN:
			setY(getY() + getSpeed());
		    break;		    
		}
		
	}
	
}
