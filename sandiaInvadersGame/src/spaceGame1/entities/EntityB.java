package spaceGame1.entities;

import java.awt.Graphics;
import java.awt.Rectangle;

public interface EntityB {
//	Not Friendly Entity

	public void tick();

	public void render(Graphics g);

	public Rectangle getBounds();

	public double getX();

	public double getY();
}
