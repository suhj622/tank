package com.suhj.tank;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
/**
 * 
 * @author Haojie
 * 
 * 2021-12-07 重写资源管理类
 * 添加了主战坦克和敌方坦克的图片
 * 更新了子弹的图片
 */
public class ResourceMgr {
	
	//public static BufferedImage tankL, tankU, tankR, tankD;
	public static BufferedImage GoodTankL, GoodTankU, GoodTankR, GoodTankD;
	public static BufferedImage BadTankL, BadTankU, BadTankR, BadTankD;
	
	public static BufferedImage bulletL, bulletU, bulletR, bulletD;
	public static BufferedImage[] explodes = new BufferedImage[16];
	
	//加载图片资源
	static {
		
		try {
			
			//加载主战坦克的图片资源
			GoodTankU = ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("images/GoodTank1.png"));
			GoodTankL = ImageUtil.rotateImage(GoodTankU, -90);
			GoodTankR = ImageUtil.rotateImage(GoodTankU, 90);
			GoodTankD = ImageUtil.rotateImage(GoodTankU, 180);
			
			//加载敌方坦克的图片资源
			BadTankU = ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("images/BadTank1.png"));
			BadTankL = ImageUtil.rotateImage(BadTankU, -90);
			BadTankR = ImageUtil.rotateImage(BadTankU, 90);
			BadTankD = ImageUtil.rotateImage(BadTankU, 180);
			
			//加载子弹图片资源
			bulletU = ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("images/bulletU.png"));
			bulletL = ImageUtil.rotateImage(bulletU, -90);
			bulletR = ImageUtil.rotateImage(bulletU, 90);
			bulletD = ImageUtil.rotateImage(bulletU, 180);
			
			//加载爆炸图片
			for(int i = 0; i< 16; i++) explodes[i] = ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("images/e"+ (i+1) +".gif"));
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
