package spaceGame1;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

import spaceGame1.entities.EntityB;

public class Berry extends GameObject implements EntityB {

	private Textures tex;
	Random r = new Random();
	private int speed = r.nextInt(3) + 1; // Random speed
	private Game game;
	private Controller c;

	public Berry(double x, double y, Textures tex, Controller c, Game game) {
		super(x, y);
		this.tex = tex;
		this.c = c;
		this.game = game;
	}

	@Override
	public void tick() {
//		This is so the object, enemy, or whatever moves.
//		y += 10;
		y += speed;
		if (y > (Game.HEIGHT * Game.SCALE)) {
			y = -40;
			speed = r.nextInt(3) + 1; // Speed changes at random when they reapear
			if (x < 10) {
				x = -r.nextInt(Game.WIDTH * Game.SCALE + 20);
//				Dont know if this is a good idea
			} else {
				x = r.nextInt(Game.WIDTH * Game.SCALE - 110);
			}

		}
//		for (int i = 0; i < game.eA.size(); i++) {
//			EntityA tempEnt = game.eA.get(i);
//			if (Physics.collision(this, tempEnt)) {
//				c.removeEntity(this); // Removing the enemy that was hit
//				game.setEnemy_killed(game.getEnemy_killed() + 1);
//				c.removeEntity(tempEnt);
////			c.removeEntity(game.eA.element()); This doesnt work properly
//			}
//		}

	}

	@Override
	public void render(Graphics g) {
		g.drawImage(tex.berry, (int) x, (int) y, null);

	}

	@Override
	public Rectangle getBounds() {
		return new Rectangle((int) x, (int) y, 50, 50);

	}

	@Override
	public double getX() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double getY() {
		// TODO Auto-generated method stub
		return 0;
	}

}
