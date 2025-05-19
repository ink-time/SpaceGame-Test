package spaceGame1;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MouseInput implements MouseListener {
	Game game;

	public MouseInput(Game game) {
		this.game = game;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {
		int mX = e.getX(); // X coordinates of the mouse
		int mY = e.getY(); // Y coordinates of the mouse
		/*
		 * public Rectangle playButton = new Rectangle(Game.WIDTH - 80, 150, 100, 50);
		 * public Rectangle helpButton = new Rectangle(Game.WIDTH - 80, 250, 100, 50);
		 * public Rectangle quitButton = new Rectangle(Game.WIDTH - 80, 350, 100, 50);
		 */
		// Play Button
		if (mX >= Game.WIDTH - 80 && mX <= Game.WIDTH + 20) {
			if (mY >= 150 && mY <= 200) {
				// Pressed PlayButton
				game.setState(Game.STATE.GAME);
			}
		}

		// Help Button
		if (mX >= Game.WIDTH - 80 && mX <= Game.WIDTH + 20) {
			if (mY >= 250 && mY <= 300) {
				// Pressed HelpButton
				game.setState(Game.STATE.HELP);
			}
		}

		// Exit Button
		if (mX >= Game.WIDTH - 80 && mX <= Game.WIDTH + 20) {
			if (mY >= 350 && mY <= 400) {
				// Pressed ExitButton
				System.exit(1);
			}
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

}
