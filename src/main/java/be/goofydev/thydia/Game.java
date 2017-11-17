package be.goofydev.thydia;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.WindowEvent;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

import be.goofydev.thydia.entity.mob.EntityPlayer;
import be.goofydev.thydia.graphics.Screen;
import be.goofydev.thydia.gui.Gui;
import be.goofydev.thydia.gui.GuiMainMenu;
import be.goofydev.thydia.input.Keyboard;
import be.goofydev.thydia.input.Mouse;
import be.goofydev.thydia.level.Level;
import be.goofydev.thydia.sounds.Sound;

/**
 * Created by Jeroen on 31/10/2015.
 */
public class Game extends Canvas implements Runnable {

	private static final long serialVersionUID = 1L;
	
	public static Game instance;
	public static int width = 600; // 720
	public static int height = width / 16 * 9;
	public static int scale = 2;
	public static boolean debug = true;
	
	private Thread thread;
	private JFrame frame;
	private Level level;
	private boolean running;
	private Keyboard key;
	private EntityPlayer player;
	private Screen screen;
	private Gui gui;

	private BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
	private int[] pixels = ((DataBufferInt) image.getRaster().getDataBuffer()).getData();

	public Game() {
		instance = this;

		Dimension size = new Dimension(width * scale, height * scale);
		setPreferredSize(size);

		screen = new Screen(width, height);
		frame = new JFrame();
		key = new Keyboard(this);
		new Mouse(this);

		setGui(new GuiMainMenu(screen.width, screen.height));
	}

	public synchronized void start() {
		running = true;
		thread = new Thread(this, "Thydia");
		thread.start();
	}

	public synchronized void stop() {
		running = false;
	}

	public void run() {
		long lastTime = System.nanoTime();
		long timer = System.currentTimeMillis();
		final double ns = 1000000000.0 / 60D;
		double delta = 0;

		int updates = 0;
		int frames = 0;

		// fix auto focus
		requestFocus();

		while (running) {
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;

			while (delta >= 1) {
				update();
				updates++;
				delta--;
			}

			render();
			frames++;

			if (System.currentTimeMillis() - timer > 1000) {
				timer += 1000;
				frame.setTitle("Thydia  |  UPS: " + updates + ", FPS: " + frames);
				updates = 0;
				frames = 0;
			}
		}

		frame.setTitle("Exiting...");
		
		Sound.music_bg.stop();
		Sound.proj_pop.stop();
		
		frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));;
		
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		System.exit(0);
	}

	public void update() {
		key.update();
		if(gui != null) gui.update();
		
		if(level != null) {
			
			if(!(gui != null && gui.shouldPauseGame())) {
				level.update();
			}
			
		}
	}

	public void render() {
		BufferStrategy bs = getBufferStrategy();

		if (bs == null) {
			// 3 ==> 3 buffers ===> speed improvement
			createBufferStrategy(3);
			return;
		}

		screen.clear();
		int xScroll = 0;
		int yScroll = 0;
		if(player != null) {
			xScroll = player.x - screen.width / 2;
			yScroll = player.y - screen.height / 2;
		}
		
		if(level != null)
			level.render(xScroll, yScroll, screen);

		if(gui != null)
			gui.render(screen);

		for (int i = 0; i < pixels.length; i++) {
			pixels[i] = screen.pixels[i];
		}

		Graphics g = bs.getDrawGraphics();
		g.drawImage(image, 0, 0, getWidth(), getHeight(), null);

		g.dispose(); // release system resources again.
		bs.show();
	}

	public static void main(String[] args) {
		Game game = new Game();
		game.frame.setResizable(true); // false
		game.frame.setTitle("Thydia");
		game.frame.add(game);
		game.frame.pack();
		game.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		game.frame.setLocationRelativeTo(null);
		game.frame.setVisible(true);
		try {
			game.frame.setIconImage(ImageIO.read(Game.class.getResource("/textures/icon.png")));
		} catch (IOException e) {
			e.printStackTrace();
		}

		game.start();
	}

	public static void log(LogLevel level, String message) {
		if (level == LogLevel.DEBUG && !debug)
			return;
		System.out.println(level.getPrefix() + message);
	}

	public enum LogLevel {
		DEBUG("DEBUG"), WARNING("WARNING"), INFO("INFO");

		private String prefix;

		LogLevel(String prefix) {
			this.prefix = prefix;
		}

		public String getPrefix() {
			return "[" + prefix + "] ";
		}
	}

	public EntityPlayer getPlayer() {
		return player;
	}
	
	public void setPlayer(EntityPlayer player) {
		this.player = player;
	}

	public Keyboard getKeyboard() {
		return key;
	}
	
	public Gui getGui() {
		return gui;
	}
	
	public void setGui(Gui gui) {
		if(gui !=null)
			gui.init();
		this.gui = gui;
	}
	
	public Level getLevel() {
		return level;
	}
	
	public void setLevel(Level level) {
		this.level = level;
	}
	
	public Screen getScreen() {
		return screen;
	}
	
	public int getFrameWidth() {
		return frame.getWidth();
	}
	
	public int getFrameHeight() {
		return frame.getHeight();
	}

}
