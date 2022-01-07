/**
 * 
 */
package com.suhj.tank;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Haojie 游戏模型，用来处理窗体与游戏物体之间的交互
 */
public class GameModel {

	private Tank mainTank ;
	private List<Bullet> bullets;
	private List<Tank> enemyTanks;
	private List<Explode> explodes;

	//initialize the GameModel
	public GameModel() {
		mainTank = new Tank(200, 400, Dir.DOWN, Group.GOOD, this);
		bullets = new ArrayList<>();
		enemyTanks = new ArrayList<>();
		explodes = new ArrayList<>();
		// 获取敌方坦克的数量
		int initTankCount = Integer.parseInt((String) PropertyMgr.get("initTankCount"));
		// 初始化敌方坦克
		for (int i = 0; i < initTankCount; i++) {
			enemyTanks.add(new Tank(50 + i * 80, 200, Dir.DOWN, Group.BAD, this));
		}		
	}

	//supply a series of methods to TankFrame for getting game objects
	public Tank getMainTank() {
		return mainTank;
	}

	public List<Bullet> getBullets() {
		return bullets;
	}

	public List<Tank> getEnemyTanks() {
		return enemyTanks;
	}

	public List<Explode> getExplodes() {
		return explodes;
	}

	/**
	 * deal with the contact within objects
	 * @param g
	 */
	public void paint(Graphics g) {
		
		Color c = g.getColor();
		g.setColor(Color.WHITE);
		g.drawString("子弹的数量：" + this.bullets.size(), 10, 60);
		g.drawString("敌人的数量：" + this.enemyTanks.size(), 10, 80);
		g.drawString("爆炸的数量：" + this.explodes.size(), 10, 100);
		
		g.setColor(c);
		
		this.mainTank.paint(g);

		//这种写法会引发问题 Exception in thread "AWT-EventQueue-0" java.util.ConcurrentModificationException
//		for(Bullet b: bullets) {
//			b.paint(g);
//		}
		
		
		for(int i =0; i < this.bullets.size(); i++) {
			this.bullets.get(i).paint(g);
		}
		
//		for(Iterator<Bullet> it = bullets.iterator(); it.hasNext();) {
//			Bullet b = it.next();
//			if(!b.live) it.remove();
//		}
		
		for(int i = 0; i < this.enemyTanks.size(); i++) {
			this.enemyTanks.get(i).paint(g);
		}
		
		//collision detect 碰撞检测
		for (int i = 0; i < this.bullets.size(); i++) {
			for(int j = 0; j < this.enemyTanks.size(); j++) {
				this.bullets.get(i).collideWith(this.enemyTanks.get(j));
//				if (!tanks.get(j).isLiving()) {
//					new Thread(() -> new Audio("audio/explode.wav").play()).start();
//					e xplodes.add(new Explode(tanks.get(j).getX(), tanks.get(j).getY(), this));					
//				}
			}
		}
		
		for(int i = 0; i < this.explodes.size(); i++) this.explodes.get(i).paint(g);		
	}
	
	
	

}
