package com.suhj.tank;

import java.awt.Frame;
import java.awt.Graphics;
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
	
	int x = 200, y =200;
	
	/**
	 * 构造方法
	 */
	public TankFrame() {
		
		 setSize(800, 600);
		 setResizable(false);
		 setTitle("tank war");
		 setVisible(true);
		 
		 
		 this.addKeyListener(new MyKeyListener());
		 
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
	
	/**
	 * 如何让方块动起来？
	 * 坐标值需要是变量（动态变化）
	 */
	@Override
	public void paint(Graphics g) {
		//System.out.println("paint"); //测试是 paint 方法是否有被调用
		g.fillRect(x, y, 50, 50);
		//x += 10;
		//y += 10;
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
			//repaint();
		    
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
		    default:break;
		    }			
 
		}
		
	}

}
