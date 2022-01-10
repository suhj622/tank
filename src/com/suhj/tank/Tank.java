package com.suhj.tank;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

/**
 * 坦克类
 * @author Haojie
 *
 */
public class Tank extends GameObject {
	
	private int x,y;
	//save the pre coord before the current steep
	private int preX,preY;
	private Dir dir;
	private final int SPEED = 5;
	private boolean moving = false;
	private boolean living = true ;
	private Group group = Group.BAD;
	
	private Rectangle rect = new Rectangle();	
	
	public static int WIDTH = ResourceMgr.GoodTankD.getWidth();
	public static int HEIGHT = ResourceMgr.GoodTankD.getHeight();	
	private GameModel gm;
	
	private Random random = new Random(); 	
	
	public Group getGroup() {
		return group;
	}
	
	public boolean isMoving() {
		return moving;
	}

	public boolean isLiving() {
		return living;
	}

	public Tank(int x, int y, Dir dir, Group group, GameModel gm) {
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

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public Dir getDir() {
		return dir;
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

	public Rectangle getRect() {
		return rect;
	}

	@Override
	public void paint(Graphics g) {
		
//		Color c = g.getColor();
//		g.setColor(Color.YELLOW);
//		g.fillRect(getX(), getY(), 50, 50);
//		g.setColor(c);
		
		if(!living) {
			gm.deleteGameObject(this);
		}
		
		switch(dir) {
		
		case LEFT:
			g.drawImage(this.group == Group.GOOD ? ResourceMgr.GoodTankL : ResourceMgr.BadTankL, x, y, null);
			break;
		case UP:
			g.drawImage(this.group == Group.GOOD ? ResourceMgr.GoodTankU : ResourceMgr.BadTankU, x, y, null);
		    break;
		case RIGHT:
			g.drawImage(this.group == Group.GOOD ? ResourceMgr.GoodTankR : ResourceMgr.BadTankR, x, y, null);
		    break;
		case DOWN:
			g.drawImage(this.group == Group.GOOD ? ResourceMgr.GoodTankD : ResourceMgr.BadTankD, x, y, null);
		    break;		
		
		}
		
		//g.drawImage(ResourceMgr.tankL, x, y, null);
		
		move();		
		
	}

	private void move() {
		
		this.preX = this.x;
		this.preY = this.y;
		if(group == Group.GOOD && !moving) return;
		
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
		
		if(this.group == Group.BAD && random.nextInt(100) > 95 ) this.fire();
		
		//有 4% 的几率，敌方坦克会随机选择一个方向
		if (group == Group.BAD && random.nextInt(100) > 95) randomDir();
		
		boundsCheck();
		
		//update rect
		rect.x = this.x;
		rect.y = this.y;		
		
	}

	/**
	 * 边界检测
	 */
	private void boundsCheck() {
		
		if (this.x < 0) x = 0;
		
		if (this.y < 30) y =30;
		
		if (this.x > TankFrame.GAME_WIDTH - Tank.WIDTH) x = TankFrame.GAME_WIDTH - Tank.WIDTH;
		
		if (this.y > TankFrame.GAME_HEIGHT - Tank.HEIGHT) y = TankFrame.GAME_HEIGHT - Tank.HEIGHT;
		
	}

	private void randomDir() {
		this.dir = Dir.values()[random.nextInt(4)];		
	}

	public void fire() {
		int bX = this.x + Tank.WIDTH/2 - Bullet.WIDTH/2;
		int bY = this.y + Tank.HEIGHT/2 - Bullet.HEIGHT/2;
		gm.addGameObject(new Bullet(bX, bY, this.getDir(), this.getGroup(), this.gm)); 
	}

	public void die() {
		this.living = false;		
	}

	public void setDir(Dir dir) {
		this.dir = dir;
	}

	public void setMoving(boolean moving) {
		this.moving = moving;
	}

	public GameModel getGm() {
		return gm;
	}

	public void setX(int x) {
		this.x = x;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getPreX() {
		return preX;
	}

	public int getPreY() {
		return preY;
	}
	
	
	
}
