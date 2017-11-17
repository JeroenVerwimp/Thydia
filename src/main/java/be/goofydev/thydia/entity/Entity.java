package be.goofydev.thydia.entity;

import be.goofydev.thydia.Game;
import be.goofydev.thydia.graphics.Screen;
import be.goofydev.thydia.level.Level;

import java.awt.Rectangle;
import java.util.Random;

/**
 * Created by Jeroen on 31/10/2015.
 */
public abstract class Entity {

    public int x, y;
    private boolean removed = false;
    protected Level level;
    protected final Random random = new Random();
    protected Rectangle collisionBox = null;

    public Entity(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public final void init(Level level) {
        this.level = level;
    }

    public abstract void update();

    public abstract void render(Screen screen);

    public final void remove() {
        level.removeEntity(this);
        removed = true;
    }

    public final boolean isRemoved() {
        return removed;
    }
    
    public final void setCollisionBox(int x, int y, int xa, int ya) {
    	this.collisionBox = new Rectangle(x, y, xa, ya);
    }
    
    public final void setCollisionBox(Rectangle collision) {
		this.collisionBox = collision;
	}
    
    public final Rectangle getCollisionBox() {
		return collisionBox;
	}
    
    public final Rectangle getCollisionBoxWithLocation() {
    	if(collisionBox == null)
    		return null;
    	
    	return new Rectangle(x + collisionBox.x, y + collisionBox.y, collisionBox.width, collisionBox.height);
    }
    
    public final void renderCollisionBox(Screen screen) {
    	if(!Game.debug || getCollisionBox() == null)
    		return;
    	
    	screen.drawRect(x + collisionBox.x, y + collisionBox.y, collisionBox.width, collisionBox.height, 0xFFFF0000, true);
    }
    
    public void onEntityCollision(Entity other) { }
}
