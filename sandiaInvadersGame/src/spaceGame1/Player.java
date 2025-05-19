package spaceGame1;

import java.awt.Graphics;
import java.awt.Rectangle;

import spaceGame1.entities.EntityA;
import spaceGame1.entities.EntityB;

public class Player extends GameObject implements EntityA {
//	In most programming the Y axis is reversed, 
//	it starts from top left and goes to the bottom 
//	as the numbers grow.
//	The X axis is still the same though

	private double velX = 0;
	private double velY = 0;
//	private BufferedImage player;

	private Textures tex;
	Game game;
	Controller c;

	public Player(double x, double y, Textures tex, Game game, Controller c)// , Game game (was a third parameter)
	{
		super(x, y);
		this.tex = tex;
		this.game = game;
		this.c = c;
//		SpriteSheet ss = new SpriteSheet(game.getSpriteSheet());
//		player = ss.grabImage(1, 1, 128, 128);
	}

	public void tick() {
		x += velX;
		y += velY;
//		Colision for the border of the screen: 
//		This will probably have to be changed if the sprite is different
		if (x <= 0 - 28) {
			x = 0 - 28;
		}
		if (x >= Game.WIDTH * Game.SCALE - 100) {
			x = Game.WIDTH * Game.SCALE - 100;
		}
		if (y <= 0 - 30) {
			y = 0 - 30;
		}
		if (y >= Game.HEIGHT * Game.SCALE - 100) {
			y = Game.HEIGHT * Game.SCALE - 100;
		}

		for (int i = 0; i < game.eB.size(); i++) {
			EntityB tempEnt = game.eB.get(i);
			if (Physics.collision(this, tempEnt)) {
				if (tempEnt.getClass().equals(new Enemy(5000, 5000, tex, c, game).getClass())) {
					// System.out.println("Youve been hit");
					c.removeEntity(tempEnt);
					game.setBaseHealth(-35);
					game.setEnemy_killed(game.getEnemy_killed() + 1);
					if (game.getBaseHealth() <= 0 || game.getPlanetHealth() <= 0) {
						game.setState(Game.STATE.DEATH);

					}
				} else if (tempEnt.getClass().equals(new Berry(5000, 5000, tex, c, game).getClass())) {
					c.removeEntity(tempEnt);
					if (game.getBaseHealth() + 30 > 200) {
						game.resetBaseHealth();
					} else {
						if (game.getPlayerSpeed() < 10) {
							game.setPlayerSpeed(2);
						}
						game.setBaseHealth(+30);
					}

				}

			}

		}
	}

	public void render(Graphics g) {
		g.drawImage(tex.player, (int) x, (int) y, null);
	}

	public Rectangle getBounds() {
		return new Rectangle((int) x, (int) y, 70, 70);
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

//	public BufferedImage getPlayer() {
//		return player;
//	}
//
//	public void setPlayer(BufferedImage player) {
//		this.player = player;
//	}

	public double getVelX() {
		return velX;
	}

	public void setVelX(double velX) {
		this.velX = velX;
	}

	public double getVelY() {
		return velY;
	}

	public void setVelY(double velY) {
		this.velY = velY;
	}

}
