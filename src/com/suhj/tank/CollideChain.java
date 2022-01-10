/**
 * 
 */
package com.suhj.tank;

import java.util.LinkedList;
import java.util.List;

/**
 * @author Haojie
 *
 */
public class CollideChain implements Collider {
	
	private TankToBulletCollider tankToBulletCollider = new TankToBulletCollider();
	private TankToTankCollider tankToTankCollider = new TankToTankCollider();
	private List<Collider> colliderList = new LinkedList<>();
	
	public CollideChain() {
		add(tankToBulletCollider);
		add(tankToTankCollider);		
	}
	
	private void add(Collider collider) {
		colliderList.add(collider);
	}
	

	@Override
	public boolean collide(GameObject o1, GameObject o2) {
		
		for(int i = 0; i < colliderList.size(); i++ ) {
			if(colliderList.get(i).collide(o1, o2)) {
				return true;
			};
		}
		return false;		
	}
}
