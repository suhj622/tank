/**
 * 
 */
package com.suhj.tank;

/**
 * @author Haojie
 * 碰撞器接口
 */
public interface Collider {
	public abstract boolean collide(GameObject o1, GameObject o2);
}
