package spaceGame1;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.util.LinkedList;
import java.util.Random;

import javax.swing.JFrame;

import spaceGame1.entities.EntityA;
import spaceGame1.entities.EntityB;

public class Game extends Canvas implements Runnable {

//	public Game() {
//		this. //All the different methods and stuff we can use from Canvas.
//	}
	private static final long serialVersionUID = 1L;
	public static final int WIDTH = 640; // Possible 640, though we will see.
	public static final int HEIGHT = WIDTH / 12 * 9;
	public static final int SCALE = 2;
	// 640 * 480 px
	public final String TITLE = "Sandia Invaders";

	public boolean running = false;
	private Thread thread;

	// For buffering:
	// Which is making an image charge before is shown on screen.
	// We use buffered images for our sprites.
	private BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
	private BufferedImage spriteSheet = null;
	private BufferedImage background = null;
	// temp
//	private BufferedImage player;

	private boolean isShooting = false;

	private int enemy_count = 1;
	private int enemy_killed = 0;

	private Player p;
	private Controller c;
	private Textures tex;
	private Menu menu;

	public LinkedList<EntityA> eA;
	public LinkedList<EntityB> eB;

	private int baseHealth = 100 * 2;
	private int planetHealth = 300;
	public int playerSpeed = 5;

	private Random randomNum = new Random();

//	States for the game, using ENUMS
	public enum STATE {
		MENU, GAME, HELP, DEATH
	};

	public STATE state = STATE.MENU;

	private void init() {
		requestFocus();
		BufferedImageLoader loader = new BufferedImageLoader();
		try {
			spriteSheet = loader.loadImage("/sprite_sheet.png");
			background = loader.loadImage("/background.png");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("A ver");
			// If the program happens to not be able to find the file of that name,
			// it will print an error report.
		}

//		Test for the spritesheet:
//		SpriteSheet ss = new SpriteSheet(spriteSheet);
//		player = ss.grabImage(1, 1, 128, 128);

//		Listener:
		this.addKeyListener(new KeyInput(this));
		this.addMouseListener(new MouseInput(this));

		tex = new Textures(this);

		c = new Controller(tex, this);
		p = new Player(WIDTH, 800, tex, this, c); // The this just takes this class as the Game game that is being used
													// in
													// the
		// constructor of player.

		menu = new Menu();

		c.createEnemy(2);
//		if (randomNum>50){ c.createBerry(1)} //This way they appear randomly?
		c.createBerry(1);

		eA = c.geteA();
		eB = c.geteB();

	}

	private synchronized void start() {
		if (running) {
			// So this is so if running is already true, why in tha hell would we wanna make
			// it true again? This if makes sure that if thats the case we exit the method
			// with return;
			return;
		}
		running = true;
		thread = new Thread(this);
		thread.start();

	}

	private synchronized void stop() {
		if (!running) {
			return;
		}
		running = false;
		try {
			thread.join();
			// This method is joining all the threads together and waits for them to die, or
			// stop.
			// The possibility of this failing to join the threads exists, however small, so
			// the program wants a try catch here.
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.exit(1);
	}

	public void run() {
		// Here is where the game loop is gonna be.

		// ALL THE VARIABLES:
		init();
		// Initializing the
		long lastTime = System.nanoTime(); // Nanoseconds
		final double amountOfTicks = 60.0; // The game is gonna update 60 times every time it goes through the loop,
											// this means our game would basicaally run on 60 fps
		double ns = 1000000000 / amountOfTicks;
		double delta = 0; // This will handle the case where our fps are runninng a little bit behind,
							// this number will make it so the fps catch up

		int updates = 0;
		int frames = 0;
		long timer = System.currentTimeMillis();
		while (running) {
			// This would be our game loop. Of cours, there have to be some more conditions
			// for it than the 'runnable' being true.

			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;

			if (delta >= 1) {
				tick();
				updates++;
				delta--;
			}
			render();
			frames++;

			if (System.currentTimeMillis() - timer > 1000) {
				timer += 1000; // This is so the loop only happens once. So, even if we see the println more
								// than once, that is only the game loop looping, and entering in this one, only
								// once each time. Thats why the different printlns have similar results.
				System.out.println(updates + " Ticks, Fps " + frames);
				updates = 0;
				frames = 0;
				// This is so the fps and the Ticks reset after an itineration of the loop,
				// which would print: 60, 120, 180, 240, 300, 360, ect. And the Fps would also
				// climb.
			}

//			System.out.println("Lavidaesunasandía");

		}
		stop(); // We make the game stop after the conditions for it running are no longer met.

	}

	private void tick() {
		if (state == STATE.GAME) {
			// Everything in the game that UPDATES
			p.tick();
			c.tick();
			if (getPlanetHealth() <= 0) {
				setState(Game.STATE.DEATH);
			}

		}
		if (enemy_killed >= enemy_count) {
			enemy_count += 1;
			enemy_killed = 0;
			c.createEnemy(enemy_count);
			if ((randomNum.nextInt(100) + 1) > 78) {
				c.createBerry(1);
			}

		}

	}

	private void render() {

		// Everyting in the game that RENDERS
		// A buffer strategy handles all the buffering behind the screen.
		BufferStrategy bs = this.getBufferStrategy();
		// Here, using 'this' we are accessing the Canvas SuperClass that we are
		// extending from.
		// This method we are using will ruturn a buffer strategy, however, if there is
		// no buffer Strategy created yet, it will return null

		// since we havent created the buffer strategy yet, we want to initialize it,
		// however, since we want that to only happen once:
		if (bs == null) {
			createBufferStrategy(3);
			// What does this parameter mean? It means we will have 3 buffers. This means
			// that we have one buffer that load the image for another buffer that would
			// load it for the screen. This will increase speed over time.
			return;
		}
		Graphics g = bs.getDrawGraphics();
		// This is what is gonna drtaw our images that will be buffered?

		// And so, this is the space where we can draw stuff:
		// So our spriteSheets, our player, all that.
		///////////////////////////////////////////////////////////////////

		// Test for the spritesheet:
		g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
//
//		g.drawImage(player, 100, 290, this);

		if (state == STATE.GAME) {
			g.drawImage(background, 0, 0, null);

			// This is the planet/ship's base of operations health
			g.setColor(Color.lightGray);
			g.fillRect(WIDTH * SCALE - 310, HEIGHT * SCALE - 60, 300, 50);

			g.setColor(Color.BLUE);
			g.fillRect(WIDTH * SCALE - 310, HEIGHT * SCALE - 60, planetHealth, 50);

			g.setColor(Color.white);
			g.drawRect(WIDTH * SCALE - 310, HEIGHT * SCALE - 60, 300, 50);
			p.render(g);

			c.render(g);
			// This is the players health
			g.setColor(Color.lightGray);
			g.fillRect(5, 5, 200, 50);

			g.setColor(Color.green);
			g.fillRect(5, 5, baseHealth, 50);

			g.setColor(Color.white);
			g.drawRect(5, 5, 200, 50);

		} else if (state == STATE.MENU) {
			menu.render(g);
		} else if (state == STATE.DEATH) {
			menu.render(g);
			p.setX(WIDTH);
			p.setY(800);
			resetHealths();

			// DEATH MENU
			Font fnt0 = new Font("arial", Font.BOLD, 50);
			g.setFont(fnt0);
			g.setColor(Color.white);
			g.drawString("GAME OVER", Game.WIDTH - 175, Game.HEIGHT + 50);
			// maybe I should do this in the Game class
		}

		//////////////////////////////////////////////////////////////////
		// We really have to dispose of the buffer strategy that is called at the
		// beggining of this loop, since if we dont do that, it will return to its value
		// of null each iteration of the loop.
		g.dispose();
		bs.show();

	}

	public void keyPressed(KeyEvent e) {
		if (state == STATE.GAME) {
//			Here we use Velocity istead of adding and substracting to the Axis because this way the movement is smoother.
			int key = e.getKeyCode();
			if (key == KeyEvent.VK_D) {
				p.setVelX(playerSpeed);
			} else if (key == KeyEvent.VK_A) {
				p.setVelX(-playerSpeed);

			} else if (key == KeyEvent.VK_S) {
				p.setVelY(playerSpeed);

			} else if (key == KeyEvent.VK_W) {
				p.setVelY(-playerSpeed);
			} else if (key == KeyEvent.VK_SPACE && !isShooting) {
				isShooting = true;
				c.addEntity(new Bullet(p.getX(), p.getY(), tex, this));
			} else if (key == KeyEvent.VK_B) {
				state = STATE.MENU;

			}
		}

	}

	public void keyReleased(KeyEvent e) {
//		This makes it so the sprite doesnt just keep going with the inertia of the move forever and goes offscreen.
		int key = e.getKeyCode();
		if (key == KeyEvent.VK_D) {
			p.setVelX(0);
		} else if (key == KeyEvent.VK_A) {
			p.setVelX(0);

		} else if (key == KeyEvent.VK_S) {
			p.setVelY(0);

		} else if (key == KeyEvent.VK_W) {
			p.setVelY(0);
		} else if (key == KeyEvent.VK_SPACE) {
//			This, plus the alteration of the condition in keyPressed
//			makes it so the player has to stop hitting space 
//			to shoot again.
			isShooting = false;
		}
	}

	public static void main(String[] args) {

		Game game = new Game();
		game.setPreferredSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
		game.setMaximumSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
		game.setMinimumSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));

		JFrame frame = new JFrame(game.TITLE);
		frame.add(game);
		frame.pack(); // It resizes the window to fit all its components, only if necessary. Also
						// resizes when the aaspect ratio is lower than the minimum?
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // FUN FACT: this returns an Integer. Anyways, it allows
																// us to exit the app clicking the x button.
		frame.setResizable(false); // We dont want the widow to be resizable in this case.
		frame.setLocationRelativeTo(null); // This basically sets this function to null, so the location is not gonna be
											// set
		frame.setVisible(true);
		game.start();

	}

	public BufferedImage getSpriteSheet() {
		return spriteSheet;
	}

	public void setSpriteSheet(BufferedImage spriteSheet) {
		this.spriteSheet = spriteSheet;
	}

	public int getEnemy_count() {
		return enemy_count;
	}

	public void setEnemy_count(int enemy_count) {
		this.enemy_count = enemy_count;
	}

	public int getEnemy_killed() {
		return enemy_killed;
	}

	public void setEnemy_killed(int enemy_killed) {
		this.enemy_killed = enemy_killed;
	}

	public STATE getState() {
		return state;
	}

	public void setState(STATE state) {
		this.state = state;
	}

	public int getBaseHealth() {
		return baseHealth;
	}

	public void setBaseHealth(int changeHealth) {
		this.baseHealth += changeHealth;
	}

	public int getPlayerSpeed() {
		return playerSpeed;
	}

	public void setPlayerSpeed(int bonusSpeed) {
		this.playerSpeed += bonusSpeed;
	}

	public void resetBaseHealth() {
		this.baseHealth = 200;
	}

	public void resetHealths() {
		this.baseHealth = 200;
		this.planetHealth = 300;
	}

	public int getPlanetHealth() {
		return planetHealth;
	}

	public void setPlanetHealth(int changeHealth) {
		this.planetHealth += changeHealth;

	}

}
