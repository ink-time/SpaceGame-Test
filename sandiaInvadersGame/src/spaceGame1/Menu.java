package spaceGame1;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class Menu {
	public Rectangle playButton = new Rectangle(Game.WIDTH - 80, 150, 100, 50);

	public Rectangle helpButton = new Rectangle(Game.WIDTH - 80, 250, 100, 50);

	public Rectangle quitButton = new Rectangle(Game.WIDTH - 80, 350, 100, 50);

	public void render(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;

		Font fnt0 = new Font("arial", Font.BOLD, 50);
		g.setFont(fnt0);
		g.setColor(Color.white);
		g.drawString("SPACING GAME", (Game.WIDTH) / 2 + (Game.WIDTH / 6), 100);

		Font fnt1 = new Font("arial", Font.BOLD, 30);
		g.setFont(fnt1);

		g2d.draw(playButton);
		g.drawString("Play", playButton.x + 20, playButton.y + 35);
		g2d.draw(helpButton);
		g.drawString("Help", helpButton.x + 20, helpButton.y + 35);
		g2d.draw(quitButton);
		g.drawString("Exit", quitButton.x + 20, quitButton.y + 35);
	}
}
