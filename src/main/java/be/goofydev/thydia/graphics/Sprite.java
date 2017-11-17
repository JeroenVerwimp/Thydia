package be.goofydev.thydia.graphics;

import java.awt.*;

/**
 * Created by Jeroen on 31/10/2015.
 */
public class Sprite {

	public final int SIZE;
	private int x, y;
	private int width, height;
	private SpriteSheet sheet;
	public int[] pixels;

	// Projectile
	public static Sprite proj_wizzard_orb = new Sprite(16, 0, 0, SpriteSheet.wizzard_projectiles);

	// hud
	public static Sprite health_empty = new Sprite(4, 12, 0, 0, SpriteSheet.gui_hud);
	public static Sprite health_full = new Sprite(4, 12, 1, 0, SpriteSheet.gui_hud);

	// Particle
	public static Sprite particle_normal = new Sprite(3, 0x555555);

	public Sprite(int size, int x, int y, SpriteSheet sheet) {
		this.SIZE = size;
		this.width = size;
		this.height = size;
		this.pixels = new int[SIZE * SIZE];
		this.x = x * size;
		this.y = y * size;
		this.sheet = sheet;
		load();
	}

	public Sprite(int width, int height, int x, int y, SpriteSheet sheet) {
		this.SIZE = -1;
		this.width = width;
		this.height = height;
		this.pixels = new int[width * height];
		this.x = x * width;
		this.y = y * height;
		this.sheet = sheet;
		load();
	}

	public Sprite(int width, int height, int color) {
		this.SIZE = -1;
		this.width = width;
		this.height = height;
		this.pixels = new int[width * height];
		setColor(color);
	}

	public Sprite(int size, int color) {
		this.SIZE = size;
		this.width = size;
		this.height = size;
		this.pixels = new int[SIZE * SIZE];
		setColor(color);
	}

	public Sprite(int[] pixels, int width, int height) {
		this.SIZE = (width == height) ? width : -1;
		this.width = width;
		this.height = height;
		this.pixels = new int[pixels.length];
		for (int i = 0; i < pixels.length; i++) {
			this.pixels[i] = pixels[i];
		}
	}

	private void setColor(int color) {
		for (int i = 0; i < width * height; i++) {
			pixels[i] = color;
		}
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	private void load() {
		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				pixels[x + y * width] = sheet.pixels[(x + this.x) + (y + this.y) * sheet.WIDTH];
			}
		}
	}

	public void replaceColor(int from, int to) {
		for (int i = 0; i < SIZE * SIZE; i++) {
			if (pixels[i] == from)
				pixels[i] = to;
		}
	}

	public void fromGrayScale(int grayScaleBase, int base) {
		int baseGray = (grayScaleBase & 0xFF0000) >> 16;

		for (int i = 0; i < SIZE * SIZE; i++) {
			int currColl = pixels[i];
			if (currColl != 0xffff00ff) {
				int currGray = (currColl & 0xFF0000) >> 16;
				int diffGray = baseGray - currGray;

				System.out.println(currGray + ", " + diffGray);

				Color baseColor = new Color(Integer.valueOf(Integer.toHexString(base).toString().substring(1, 3), 16), Integer.valueOf(Integer.toHexString(base).toString().substring(3, 5), 16), Integer.valueOf(Integer.toHexString(base).toString().substring(5, 7), 16));

				int redBase = baseColor.getRed();
				int greenBase = baseColor.getGreen();
				int blueBase = baseColor.getBlue();
				/*
				 * int redBase = (base & 0xFF0000) >> 16; int greenBase = (base & 0xFF00) >> 8;
				 * int blueBase = (base & 0xFF);
				 */
				int redNew = redBase - diffGray;
				int greenNew = greenBase - diffGray;
				int blueNew = blueBase - diffGray;

				System.out.println(redNew + ", " + greenNew + ", " + blueNew);

				if (redNew < 0)
					redNew = 0;
				if (greenNew < 0)
					greenNew = 0;
				if (blueNew < 0)
					blueNew = 0;

				if (redNew > 255)
					redNew = 255;
				if (greenNew > 255)
					greenNew = 255;
				if (blueNew > 255)
					blueNew = 255;

				pixels[i] = Integer.toHexString(new Color(redNew, greenNew, blueNew).getRGB()).hashCode();

				// int currDiff = grayScaleBase - currColl;

				// System.out.println("currDiff: " + currDiff);
				// pixels[i] = base + currDiff;

				// int currDiff = currColl - grayScaleBase;
				// pixels[i] = base + currDiff;
				// pixels[i] = base * (0x11000000 - currColl);
			}
		}

	}

	public static Sprite[] split(SpriteSheet sheet) {
		int amount = (sheet.getWidth() * sheet.getHeight()) / (sheet.WIDTH * sheet.HEIGHT);
		Sprite[] sprites = new Sprite[amount];
		int current = 0;
		int[] pixels = new int[sheet.WIDTH * sheet.HEIGHT];

		for (int yp = 0; yp < sheet.getHeight() / sheet.HEIGHT; yp++) {
			for (int xp = 0; xp < sheet.getWidth() / sheet.WIDTH; xp++) {

				for (int y = 0; y < sheet.HEIGHT; y++) {
					for (int x = 0; x < sheet.WIDTH; x++) {
						int xo = x + xp * sheet.WIDTH;
						int yo = y + yp * sheet.HEIGHT;
						pixels[x + y * sheet.WIDTH] = sheet.getPixels()[xo + yo * sheet.getWidth()];
					}
				}

				sprites[current++] = new Sprite(pixels, sheet.WIDTH, sheet.HEIGHT);
			}
		}

		return sprites;
	}
}
