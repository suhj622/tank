/**
 * 
 */
package com.suhj.tank;

/**
 * @author Haojie
 *
 */
public class ChainOfCollide implements Collider {
	
	private TankToBulletCollider tankToBulletCollider = new TankToBulletCollider();

	@Override
	public void collide(GameObject o1, GameObject o2) {
		if((o1 instanceof Tank && o2 instanceof Bullet) || (o1 instanceof Bullet && o2 instanceof Tank) ) {
			tankToBulletCollider.collide(o1, o2);
			return;
		}
		
	}

}
