/**
 * 
 */
package com.suhj.tank;

/**
 * @author Haojie
 * 坦克对子弹碰撞
 */
public class TankToBulletCollider implements Collider {

	@Override
	public boolean collide(GameObject o1, GameObject o2) {
		if (o1 instanceof Tank && o2 instanceof Bullet) {
			Tank tank = (Tank)o1;
			Bullet bullet = (Bullet)o2;
			if(tank.getGroup() == bullet.getGroup()) return false;
			//set main is not die
			if(tank.getGroup() == Group.GOOD) return false;
			
			//TODO: 用一个 rect 来记录子弹的位置
			//每次做碰撞检测，都需要重新生成对象 rect1、rect2，无引用对象过多会引发垃圾回收器回收垃圾对象 2*m*n 个对象
			//Rectangle rect1 = new Rectangle(this.x, this.y, WIDTH, HEIGHT);
			//Rectangle rect2 = new Rectangle(tank.getX(), tank.getY(), Tank.WIDTH, Tank.HEIGHT);
			
			//如果碰撞则发生爆炸
			if(tank.getRect().intersects(bullet.getRect())) {
				tank.die();
				bullet.die();
				int eX = tank.getX() + Tank.WIDTH/2 - Explode.WIDTH/2;
				int eY = tank.getY() + Tank.HEIGHT/2 - Explode.HEIGHT/2;
				tank.getGm().addGameObject(new Explode(eX, eY, tank.getGm()));
				return true;
			}			
		}
		
		if (o1 instanceof Bullet && o2 instanceof Tank) {
			collide(o2, o1);
		}
		return false;
		
	}

}
