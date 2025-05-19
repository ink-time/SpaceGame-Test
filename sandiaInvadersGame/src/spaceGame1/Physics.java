package spaceGame1;

import spaceGame1.entities.EntityA;
import spaceGame1.entities.EntityB;

public class Physics {
	public static boolean collision(EntityA entA, EntityB entB) {

		if (entA.getBounds().intersects(entB.getBounds())) {
			return true;

		}
		return false;
	}

	public static boolean collision(EntityB entB, EntityA entA) {

		if (entB.getBounds().intersects(entA.getBounds())) {
			return true;

		}

		return false;
	}
}
