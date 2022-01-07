package com.suhj.tank;

import java.awt.Color;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
/**
 * 
 * @author Haojie
 *
 */

//为什么要 从Frame类 中继承？
//从 Frame 类中继承，并且画出一个黑方块
public class TankFrame extends Frame {
	
	//set Game Frame
	static final int GAME_WIDTH = 1080 , GAME_HEIGHT = 960;
	private GameModel gm;
		
	/**
	 * 构造方法:生成游戏窗口
	 */
	public TankFrame(GameModel gm) {
		
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
		 this.gm = gm;
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
		
		gm.paint(g);
 
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
		    	case KeyEvent.VK_CONTROL:gm.getMainTank().fire(); break;
		    	default:break;
		    }
		    
		    //设置坦克方向
		    setMainTankDir();
 
		}
		
		/**
		 * 设置坦克行动方向
		 */
		public void setMainTankDir() {
			
			if(!bL && !bU && !bR && !bD) gm.getMainTank().setMoving(false);
			else { 				
				gm.getMainTank().setMoving(true);
				if(bL) gm.getMainTank().setDir(Dir.LEFT);  
				if(bU) gm.getMainTank().setDir(Dir.UP); 
				if(bR) gm.getMainTank().setDir(Dir.RIGHT);   
				if(bD) gm.getMainTank().setDir(Dir.DOWN);
			}				
		}
		
	}



}
