package spaceGame1;

import java.awt.image.BufferedImage;

public class SpriteSheet {
	private BufferedImage image;

	public SpriteSheet(BufferedImage ss) {
		this.image = ss; // ss = spreadSheet But we will use image this time
	}

	public BufferedImage grabImage(int col, int row, int width, int height) {
		BufferedImage img = image.getSubimage((col * 32) - 32, (row * 32) - 32, width, height);
		// With the column we are telling the program which sprite of the spriteSheet we
		// are taking
		// This way we dont have to create a separate file for each of the sprites, but
		// we can use one that has all of them in it.
		// The calculations mark the starting point from which program starts counting.
		return img;
	}
}
