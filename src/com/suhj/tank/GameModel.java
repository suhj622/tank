/**
 * 
 */
package com.suhj.tank;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @author Haojie 游戏模型，用来处理窗体与游戏物体之间的交互
 */
public class GameModel {

	private Tank mainTank;
	
	private List<Tank> enemyTanks = new ArrayList<>();
	private List<Explode> explodes = new ArrayList<>();
	private List<Bullet> bullets = new ArrayList<>();
	//gameObjectList:hold all game objects
	private List<GameObject> gameObjectList = new LinkedList<>();
	private CollideChain chainOfCollide = new CollideChain();

	//initialize the GameModel
	public GameModel() {
		//main tank
		mainTank = new Tank(200, 400, Dir.DOWN, Group.GOOD, this);
		//TODO:need to distinguish between main tank and enemy tank; 
		this.addGameObject(mainTank);
 		// 获取敌方坦克的数量
		int initTankCount = Integer.parseInt((String) PropertyMgr.get("initTankCount"));
		// 初始化敌方坦克
		for (int i = 0; i < initTankCount; i++) {
			this.addGameObject(new Tank(50 + i * 80, 200, Dir.DOWN, Group.BAD, this));
		}
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
		g.drawString("物体总数量：" + this.gameObjectList.size(), 10, 120);
		
		g.setColor(c);
		
		
		for(int i = 0; i < this.gameObjectList.size(); i++) {
			this.gameObjectList.get(i).paint(g);
		}
		
		

		for(int i = 0; i < this.gameObjectList.size(); i++) {
			for(int j = i + 1; j < this.gameObjectList.size(); j ++) {
				this.chainOfCollide.collide(this.gameObjectList.get(i), this.gameObjectList.get(j));
			}
		}
		
	}

	
	/**
	 * method:get Main Tank
	 * @return
	 */
	public Tank getMainTank() {
		return mainTank;
	}

	/**
	 * method:get a list of game objects
	 * @return
	 */
	public List<GameObject> getGameObjectList() {
		return gameObjectList;
	}	
	
	public List<Tank> getEnemyTanks() {
		return enemyTanks;
	}

	public List<Explode> getExplodes() {
		return explodes;
	}

	public List<Bullet> getBullets() {
		return bullets;
	}

	/**
	 * method:add a game object into the list
	 * @param gameObject
	 */
	public void addGameObject(GameObject gameObject) {
		
		gameObjectList.add(gameObject);
		
		//classify object according to the type
		//TODO:need to distinguish between main tank and enemy tank
		if (gameObject instanceof Tank) {
			this.enemyTanks.add((Tank)gameObject);
			return;
		}
		
		if (gameObject instanceof Explode) {
			this.explodes.add((Explode)gameObject);
			return;
		}
		
		if (gameObject instanceof Bullet) {
			this.bullets.add((Bullet)gameObject);
			return;
		}
	}
	
	/**
	 * method:delete a game object form the list
	 * @param gameObject
	 */
	public void deleteGameObject(GameObject gameObject) {
		
		gameObjectList.remove(gameObject);
		
		//classify object according to the type
		//TODO:need to distinguish between main tank and enemy tank
		if (gameObject instanceof Tank) {
			this.enemyTanks.remove((Tank)gameObject);
			return;
		}
		
		if (gameObject instanceof Explode) {
			this.explodes.remove((Explode)gameObject);
			return;
		}
		
		if (gameObject instanceof Bullet) {
			this.bullets.remove((Bullet)gameObject);
			return;
		}		
	}	 
	
}
