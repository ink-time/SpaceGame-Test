package spaceGame1.entities;

import java.awt.Graphics;
import java.awt.Rectangle;

public interface EntityA {
//	Friendly Entity

	public void tick();

	public void render(Graphics g);

	public Rectangle getBounds();

	public double getX();

	public double getY();
}
