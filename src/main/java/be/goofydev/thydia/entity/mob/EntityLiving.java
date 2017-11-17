package be.goofydev.thydia.entity.mob;

import be.goofydev.thydia.entity.Entity;
import be.goofydev.thydia.graphics.Sprite;
import be.goofydev.thydia.level.tile.Tile;
import be.goofydev.thydia.util.Direction;

/**
 * Created by Jeroen on 31/10/2015.
 */
public abstract class EntityLiving extends Entity {

	protected Sprite sprite;
	protected double speed = 1;
	protected Direction direction = Direction.UP;
	protected boolean moving = false;

	public EntityLiving(int x, int y) {
		super(x, y);
		this.direction = Direction.DOWN;
	}

	public void move(int xa, int ya) {
		if (xa != 0 && ya != 0) {
			move(xa, 0);
			move(0, ya);
			return;
		}
		if (xa > 0)
			direction = Direction.RIGHT;
		if (xa < 0)
			direction = Direction.LEFT;
		if (ya > 0)
			direction = Direction.DOWN;
		if (ya < 0)
			direction = Direction.UP;

		if (!collision(xa, ya)) {
			x += xa * speed;
			y += ya * speed;
		}
	}

	private boolean collision(int xa, int ya) {
		int xMin = getCollisionBox().x;
		int xMax = getCollisionBox().x + getCollisionBox().width;
		int yMin = getCollisionBox().y;
		int yMax = getCollisionBox().y + getCollisionBox().height;

		for (int x = xMin; x < xMax; x++) {
			if (isSolid(xa, ya, x, yMin))
				return true;
		}

		for (int x = xMin; x < xMax; x++) {
			if (isSolid(xa, ya, x, yMin))
				return true;
		}

		for (int y = yMin; y < yMax; y++) {
			if (isSolid(xa, ya, xMin, y))
				return true;
		}

		for (int y = yMin; y < yMax; y++) {
			if (isSolid(xa, ya, xMax, y))
				return true;
		}

		return false;
	}

	private boolean isSolid(int xa, int ya, int x, int y) {
		if (level == null)
			return false;

		Tile lastTile = level.getTile((this.x + x) >> 4, (this.y + y) >> 4);
		Tile newTile = level.getTile((this.x + x + xa) >> 4, (this.y + y + ya) >> 4);
		if (!lastTile.equals(newTile) && newTile.solid())
			return true;

		return false;
	}

}
