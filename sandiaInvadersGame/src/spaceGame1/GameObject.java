package spaceGame1;

import java.awt.Rectangle;

public class GameObject {
	public double x;
	public double y;
//	These are the x and y coordinates for everything

	public GameObject(double x, double y) {
		this.x = x;
		this.y = y;
	}

	public Rectangle getBounds(int width, int height) {
		return new Rectangle((int) x, (int) y, width, height);
	}
}
