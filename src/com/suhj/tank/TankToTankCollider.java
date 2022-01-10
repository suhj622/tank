/**
 * 
 */
package com.suhj.tank;

/**
 * @author Haojie
 *
 */
public class TankToTankCollider implements Collider {

	@Override
	public boolean collide(GameObject o1, GameObject o2) {
		
		if(o1 instanceof Tank && o2 instanceof Tank) {			
			Tank tank1 = (Tank)o1;
			Tank tank2 = (Tank)o2;			
			if(tank1.getRect().intersects(tank2.getRect())) {
				tank1.setX(tank1.getPreX());
				tank1.setY(tank1.getPreY());
				tank2.setX(tank2.getPreX());
				tank2.setY(tank2.getPreY());
				return false;
			}			
		}
		
		return true;
	}

}
