package be.goofydev.thydia.level;

import java.util.ArrayList;
import java.util.List;

import be.goofydev.thydia.entity.Entity;
import be.goofydev.thydia.graphics.Screen;
import be.goofydev.thydia.level.tile.Tile;
import be.goofydev.thydia.level.tile.Tiles;

/**
 * Created by Jeroen on 31/10/2015.
 */
public class Level {

	protected int width, height;
	protected int[] tiles;
	protected List<Entity> entities = new ArrayList<Entity>();
	protected List<Entity> items = new ArrayList<Entity>();

	public Level(int width, int height, int[] tiles) {
		this.width = width;
		this.height = height;
		this.tiles = tiles;
		entities = new ArrayList<Entity>();
		items = new ArrayList<Entity>();
	}

	public void update() {
		List<Entity> all = new ArrayList<>();
		all.addAll(entities);
		all.addAll(items);

		for (int i = 0; i < all.size(); i++) {
			Entity checking = all.get(i);
			checking.update();

			for (int j = 0; j < all.size(); j++) {
				Entity other = all.get(j);
				if (!checking.equals(other) && checking.getCollisionBox() != null && other.getCollisionBox() !=null) {
					if(checking.getCollisionBoxWithLocation().intersects(other.getCollisionBoxWithLocation())) {
						checking.onEntityCollision(other);
					}
				}
			}
		}
	}

	public void render(int xScroll, int yScroll, Screen screen) {
		screen.setOffset(xScroll, yScroll);
		int x0 = xScroll >> 4; // --- / 16 ===> tile size
		int x1 = (xScroll + screen.width + 16) >> 4;
		int y0 = yScroll >> 4; // --- / 16 ===> tile size
		int y1 = (yScroll + screen.height + 16) >> 4;

		for (int y = y0; y < y1; y++) {
			for (int x = x0; x < x1; x++) {
				getTile(x, y).render(x, y, screen);
			}
		}

		renderEntities(screen);
	}

	public Tile getTile(int x, int y) {
		if (x < 0 || y < 0 || x >= width || y >= height)
			return Tiles.voidTile;
		
		return Tiles.getTileById(tiles[x + y * width]);
	}

	public void renderEntities(Screen screen) {
		for (Entity e : items) {
			e.render(screen);
			e.renderCollisionBox(screen);
		}

		for (Entity e : entities) {
			e.render(screen);
			e.renderCollisionBox(screen);
		}
	}

	public void addEntity(Entity ent) {
		ent.init(this);
		entities.add(ent);
	}

	public List<Entity> getEntities() {
		return entities;
	}

	public void addItem(Entity item) {
		item.init(this);
		items.add(item);
	}

	public List<Entity> getItems() {
		return items;
	}

	public void removeEntity(Entity ent) {
		if (entities.contains(ent))
			entities.remove(ent);
		if (items.contains(ent))
			items.remove(ent);
	}
}
