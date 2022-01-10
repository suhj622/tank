package com.suhj.tank;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Bullet extends GameObject {
	
	private static final int SPEED = 10;
	public static int WIDTH = ResourceMgr.bulletD.getWidth();
	public static int HEIGHT = ResourceMgr.bulletD.getHeight();
	private GameModel gm = null;
	
	int x,y;
	private Dir dir;
	
	private boolean living = true;
	private Group group = Group.BAD;
	
	private Rectangle rect = new Rectangle();
	
	public Bullet(int x, int y,Dir dir, Group group, GameModel gm) {
		this.x = x;
		this.y = y;
		this.dir = dir;
		this.group = group;
		this.gm = gm;
		
		rect.x = this.x;
		rect.y = this.y;
		rect.width = this.WIDTH;
		rect.height = this.HEIGHT;
		
	}
	
	public Group getGroup() {
		return group;
	}

	public void setGroup(Group group) {
		this.group = group;
	}

	@Override
	public void paint(Graphics g) {
		
		if(!living) {
			gm.deleteGameObject(this);
		}
		
//		Color c = g.getColor();
//		g.setColor(Color.RED);
//		g.fillOval(x, y, WIDTH, HEIGHT);
//		g.setColor(c);
		
		switch(dir) {
		
		case LEFT:
			g.drawImage(ResourceMgr.bulletL, x, y, null);
			break;
		case UP:
			g.drawImage(ResourceMgr.bulletU, x, y, null);
		    break;
		case RIGHT:
			g.drawImage(ResourceMgr.bulletR, x, y, null);
		    break;
		case DOWN:
			g.drawImage(ResourceMgr.bulletD, x, y, null);
		    break;		
		
		}		
		
		move();
				
	}

	private void move() {
		
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
		
		//update rect
		rect.x = this.x;
		rect.y = this.y;
		
		if(x<0 || y < 0 || x > TankFrame.GAME_WIDTH || y > TankFrame.GAME_HEIGHT) living = false;
		
	}

//	/**
//	 * 碰撞检测
//	 * @param tank
//	 */
//	public void collideWith(Tank tank) {
//		if(this.group == tank.getGroup()) return;
//		//set main is not die
//		if(tank.getGroup() == Group.GOOD) return;
//		
//		//TODO: 用一个 rect 来记录子弹的位置
//		//每次做碰撞检测，都需要重新生成对象 rect1、rect2，无引用对象过多会引发垃圾回收器回收垃圾对象 2*m*n 个对象
//		//Rectangle rect1 = new Rectangle(this.x, this.y, WIDTH, HEIGHT);
//		//Rectangle rect2 = new Rectangle(tank.getX(), tank.getY(), Tank.WIDTH, Tank.HEIGHT);
//		
//		//如果碰撞则发生爆炸
//		if(rect.intersects(tank.rect)) {
//			tank.die();
//			this.die();
//			int eX = tank.getX() + Tank.WIDTH/2 - Explode.WIDTH/2;
//			int eY = tank.getY() + Tank.HEIGHT/2 - Explode.HEIGHT/2;
//			gm.addGameObject(new Explode(eX, eY, gm));
//		}
//	}

	public void die() {
		this.living = false;		
	}

	public Rectangle getRect() {
		return rect;
	}
	
	

}
