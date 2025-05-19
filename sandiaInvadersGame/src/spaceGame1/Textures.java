package spaceGame1;

import java.awt.image.BufferedImage;

public class Textures {
	public BufferedImage player, bullet, enemy, berry;
	private SpriteSheet ss;

	public Textures(Game game) {
		ss = new SpriteSheet(game.getSpriteSheet());
		getTextures();
	}

	private Textures getTextures() {
		player = ss.grabImage(1, 1, 128, 128);
		bullet = ss.grabImage(5, 1, 128, 128);
		enemy = ss.grabImage(9, 1, 128, 128); // 9 instead of 8 because this way colision looks clearer
		berry = ss.grabImage(13, 1, 128, 128);
		return null;
	}

	public BufferedImage getPlayer() {
		return player;
	}

	public void setPlayer(BufferedImage player) {
		this.player = player;
	}

	public BufferedImage getBullet() {
		return bullet;
	}

	public void setBullet(BufferedImage bullet) {
		this.bullet = bullet;
	}

	public BufferedImage getEnemy() {
		return enemy;
	}

	public void setEnemy(BufferedImage enemy) {
		this.enemy = enemy;
	}

	public BufferedImage getBerry() {
		return berry;
	}

	public void setBerry(BufferedImage berry) {
		this.berry = berry;
	}

	public SpriteSheet getSs() {
		return ss;
	}

	public void setSs(SpriteSheet ss) {
		this.ss = ss;
	}

}
