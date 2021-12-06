package com.suhj.tank;

import java.awt.Color;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
//import java.util.Iterator;
import java.util.List;

/**
 * 
 * @author Haojie
 *
 */

//为什么要 从Frame类 中继承？
//从 Frame 类中继承，并且画出一个黑方块
public class TankFrame extends Frame {
	
//	private static int x = 200, y =200;
//	private static final int  SPEED = 10;	
//	private static Dir dir = Dir.DOWN;
	
	//用一个对象来代表坦克
	Tank myTank = new Tank(200, 400, Dir.DOWN, Group.GOOD, this);
	//装载一组子弹
	List<Bullet> bullets = new ArrayList<>();
	//一排地方坦克
	List<Tank> tanks = new ArrayList<>();
	//一系列爆炸
	List<Explode> explodes = new ArrayList<>();
	
	static final int GAME_WIDTH = 800 , GAME_HEIGHT = 600; ;
	
	
	
	/**
	 * 构造方法
	 */
	public TankFrame() {
		
		 setSize(GAME_WIDTH, GAME_HEIGHT);
		 setResizable(false);
		 setTitle("tank war");
		 setVisible(true);
		 
		 
		 addKeyListener(new MyKeyListener());
		 
		 addWindowListener(new WindowAdapter() {

			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
			 
			 
		 });		
		
	}

	/**
	 * 画笔，系统自动调用方法
	 */
//	@Override
//	public void paint(Graphics g) {
//		//填充一个矩形，前 2 个参数是坐标，后两个参数是 长宽
//		g.fillRect(200, 200, 50, 50);
// 	}
	
	Image offScreenImage = null;
	
	@Override
	public void update(Graphics g) {		
		if(offScreenImage == null) {
			offScreenImage = this.createImage(GAME_WIDTH,GAME_HEIGHT);
		}
		
		Graphics gOffScreen = offScreenImage.getGraphics();
		Color c = gOffScreen.getColor();
		gOffScreen.setColor(Color.BLACK);
		gOffScreen.fillRect(0, 0, GAME_WIDTH, GAME_HEIGHT);
		gOffScreen.setColor(c);
		paint(gOffScreen);
		g.drawImage(offScreenImage, 0, 0,null);
	}


	/**
	 * 如何让方块动起来？
	 * 坐标值需要是变量（动态变化）
	 */
	@Override
	public void paint(Graphics g) {
		
		Color c = g.getColor();
		g.setColor(Color.WHITE);
		g.drawString("子弹的数量：" + bullets.size(), 10, 60);
		g.drawString("敌人的数量：" + tanks.size(), 10, 80);
		g.setColor(c);
		
		myTank.paint(g);

		//这种写法会引发问题 Exception in thread "AWT-EventQueue-0" java.util.ConcurrentModificationException
//		for(Bullet b: bullets) {
//			b.paint(g);
//		}
		
		
		for(int i =0; i < bullets.size(); i++) {
			bullets.get(i).paint(g);
		}
		
//		for(Iterator<Bullet> it = bullets.iterator(); it.hasNext();) {
//			Bullet b = it.next();
//			if(!b.live) it.remove();
//		}
		
		for(int i = 0; i < tanks.size(); i++) {
			tanks.get(i).paint(g);
		}
		
		//碰撞检测，如果发生碰撞则发生爆炸
		for (int i = 0; i < bullets.size(); i++) {
			for(int j = 0; j < tanks.size(); j++) {
				bullets.get(i).collideWith(tanks.get(j));
				if (!tanks.get(j).isLiving()) explodes.add(new Explode(tanks.get(j).getX(), tanks.get(j).getY(), this));
			}
		}
		
		for(int i = 0; i < explodes.size(); i++) explodes.get(i).paint(g);
 
 	}
	
	/**
	 * 如何按键后，让矩形移动起来？
	 * 窗口事件（如键盘事件）的监听
	 * 用键盘事件控制矩形的坐标
	 * 根据上下左右箭头的按键的状态，判断坦克的方向
	 */
	
	class MyKeyListener extends KeyAdapter {
		
		boolean bL = false;
		boolean bU = false;
		boolean bR = false;
		boolean bD = false;

		/**
		 * 键按下时被调用
		 */
		@Override
		public void keyPressed(KeyEvent e) {
		    int key = e.getKeyCode();
		    
		    switch(key) {
		    	case KeyEvent.VK_LEFT: bL = true; break;
		    	case KeyEvent.VK_UP: bU = true;  break;		    
		    	case KeyEvent.VK_RIGHT: bR = true; break;
		    	case KeyEvent.VK_DOWN: bD = true; break;
		    	default:break;   
		    }
		    
		    
		    //设置坦克方向
		    setMainTankDir();
 		    
 		    
		    
		}

		/**
		 * 键抬起来时被调用
		 */
		@Override
		public void keyReleased(KeyEvent e) {
			
		    int key = e.getKeyCode();
		    switch(key) {
		    	case KeyEvent.VK_LEFT: bL = false; break;
		    	case KeyEvent.VK_UP: bU = false;  break;		    
		    	case KeyEvent.VK_RIGHT: bR = false; break;
		    	case KeyEvent.VK_DOWN: bD = false; break;
		    	case KeyEvent.VK_CONTROL:myTank.fire(); break;
		    	default:break;
		    }
		    
		    //设置坦克方向
		    setMainTankDir();
 
		}
		
		/**
		 * 设置坦克行动方向
		 */
		public void setMainTankDir() {
			
			if(!bL && !bU && !bR && !bD) myTank.setMoving(false);
			else { 				
				myTank.setMoving(true);
				if(bL) myTank.setDir(Dir.LEFT);  
				if(bU) myTank.setDir(Dir.UP); 
				if(bR) myTank.setDir(Dir.RIGHT);   
				if(bD) myTank.setDir(Dir.DOWN);
			}				
		}
		
	}



}
