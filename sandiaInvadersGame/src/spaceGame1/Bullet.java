package spaceGame1;

import java.awt.Graphics;
import java.awt.Rectangle;

import spaceGame1.entities.EntityA;

public class Bullet extends GameObject implements EntityA {

//	private BufferedImage image;
	private Textures tex;
	private Game game;

	public Bullet(double x, double y, Textures tex, Game game) {
		super(x, y);
		this.tex = tex;
		this.game = game;
//		SpriteSheet ss = new SpriteSheet(game.getSpriteSheet());
//		image = ss.grabImage(5, 1, 128, 128);
	}

	public void tick() {
		y -= 15;

//		Checking for collisions:
//		if (Physics.collision(this, game.eB)) {
//			System.out.println("Collision DETECTED");
////			game.eB.remove(game.eB);
//		}
	}

	public void render(Graphics g) {
		g.drawImage(tex.bullet, (int) x, (int) y, null);
	}

	public Rectangle getBounds() {
		return new Rectangle((int) x, (int) y, 35, 45);
	}

//	GETTERS & SETTERS
	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}

}
