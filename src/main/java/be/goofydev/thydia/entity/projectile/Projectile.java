package be.goofydev.thydia.entity.projectile;

import be.goofydev.thydia.entity.Entity;
import be.goofydev.thydia.graphics.Sprite;

/**
 * Created by Jeroen on 2/11/2015.
 */
public abstract class Projectile extends Entity {

    protected final int xOrigin, yOrigin;
    protected double angle;
    protected Sprite sprite;
    protected double nx, ny;
    protected double speed, range, damage;

    public Projectile(int x, int y, double dir) {
        super(x, y);
        this.xOrigin = x;
        this.yOrigin = y;
        this.angle = dir;
    }
    
    protected boolean collision(int xa, int ya) {
		for (int c = 0; c < 4; c++) {
			int xt = (x + c % 2 * getCollisionBox().width + getCollisionBox().x) >> 4;
			int yt = (y + c / 2 * getCollisionBox().height + getCollisionBox().y) >> 4;
			if (level.getTile(xt, yt).solid())
				return true;
		}
		
		return false;
	}
	
}
