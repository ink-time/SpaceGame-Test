package spaceGame1;

import java.awt.Graphics;
import java.util.LinkedList;
import java.util.Random;

import spaceGame1.entities.EntityA;
import spaceGame1.entities.EntityB;

public class Controller {
//	This holds a bunch of bullet objects, or a lot of the same object
	private LinkedList<EntityA> eA = new LinkedList<EntityA>();
	private LinkedList<EntityB> eB = new LinkedList<EntityB>();
//	private LinkedList<Enemy> e = new LinkedList<Enemy>();

	EntityA entA;
	EntityB entB;

	private Textures tex;

	Random r = new Random();
	private Game game;

	public Controller(Textures tex, Game game) {
		this.tex = tex;
		this.game = game;

//		addEntity(new Enemy(r.nextInt(Game.WIDTH * Game.SCALE), -70, tex));

	}

	public void createEnemy(int enemy_count) {
		for (int i = 0; i < enemy_count; i++) {
			addEntity(new Enemy(r.nextInt(Game.WIDTH * Game.SCALE - 128), -70, tex, this, game));
//			The entity is automatically created as a B class, 
//			because enemy extends Entity B, so its a B type
		}
	}

	public void createBerry(int enemy_count) {
		for (int i = 0; i < enemy_count; i++) {
			addEntity(new Berry(r.nextInt(Game.WIDTH * Game.SCALE - 128), -70, tex, this, game));
//			The entity is automatically created as a B class, 
//			because enemy extends Entity B, so its a B type
		}
	}

	public void tick() {
//		A CLASS:
		for (int i = 0; i < eA.size(); i++) {
			entA = eA.get(i);

			entA.tick();
		}
//		B CLASS:
		for (int i = 0; i < eB.size(); i++) {
			entB = eB.get(i);

			entB.tick();
		}
//		for (int i = 0; i < b.size(); i++) {
//			tempBullet = b.get(i);
//			if (tempBullet.getY() < 0) {
//				removeBullet(tempBullet);
//			}
//			tempBullet.tick();
//		}
//		for (int i = 0; i < e.size(); i++) {
//			tempEnemy = e.get(i);
////			if (tempEnemy.getY() > Game.HEIGHT * Game.SCALE) {
////				tempEnemy.setY(0);
////			}
//			tempEnemy.tick();
//		}

	}

	public void render(Graphics g) {
//		A CLASS:
		for (int i = 0; i < eA.size(); i++) {
			entA = eA.get(i);

			entA.render(g);
		}

//		B CLASS:
		for (int i = 0; i < eB.size(); i++) {
			entB = eB.get(i);

			entB.render(g);
		}

//		for (int i = 0; i < b.size(); i++) {
//			tempBullet = b.get(i);
//
//			tempBullet.render(g);
//		}
//		for (int i = 0; i < e.size(); i++) {
//			tempEnemy = e.get(i);
//
//			tempEnemy.render(g);
//		}
	}

//	A CLASS:
	public void addEntity(EntityA block) {
		eA.add(block);
	}

	public void removeEntity(EntityA block) {
		eA.remove(block);
	}

//	B CLASS:
	public void addEntity(EntityB block) {
		eB.add(block);
	}

	public void removeEntity(EntityB block) {
		eB.remove(block);
	}

	public LinkedList<EntityA> geteA() {
		return eA;
	}

	public void seteA(LinkedList<EntityA> eA) {
		this.eA = eA;
	}

	public LinkedList<EntityB> geteB() {
		return eB;
	}

	public void seteB(LinkedList<EntityB> eB) {
		this.eB = eB;
	}

}
