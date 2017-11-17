package be.goofydev.thydia.level.tile;

import be.goofydev.thydia.graphics.Screen;
import be.goofydev.thydia.graphics.Sprite;

/**
 * Created by Jeroen on 31/10/2015.
 */
public abstract class Tile {

	protected int id;
	public Sprite sprite;

	public Tile(int id, Sprite sprite) {
		this.id = id;
		this.sprite = sprite;
	}

	public void update() {
	}

	public abstract void render(int x, int y, Screen screen);

	public int getId() {
		return id;
	}
	
	public boolean solid() {
		return false;
	}

}
