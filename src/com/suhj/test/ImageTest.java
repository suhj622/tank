package com.suhj.test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.junit.jupiter.api.Test;

class ImageTest {

	@Test
	void test() {
		
		//将图片从硬盘拿到内存里
		//局限性：图片路径是绝对路径，应该改为相对路径
		try {
			BufferedImage image = ImageIO.read(new File("D:\\eclipse-workspace\\mashibing\\tank\\src\\images\\bulletD.gif"));
			assertNotNull(image);
			
			//ImageTest.class --> 找到将ImageTest.class载入内存的 classLoader
			//--> classLoader 调用 getResourceAsStream 去 classpath 下找到对应的文件，将之当成 stream 读进来
			//--> 将 stream 交给 ImageIO 当成图片读进来
			//将需要的文件打包到项目里，包在一起，扔给哪台机器都能用
			BufferedImage image2 = ImageIO.read(ImageTest.class.getClassLoader().getResourceAsStream("images/bulletD.gif"));
			assertNotNull(image2);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//测试一个断言
		//fail("Not yet implemented");
		//assertNotNull(new Object());
	}

}
