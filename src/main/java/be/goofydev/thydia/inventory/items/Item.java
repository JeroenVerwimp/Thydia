package be.goofydev.thydia.inventory.items;

import be.goofydev.thydia.graphics.Sprite;

/**
 * Created by Jeroen on 1/11/2015.
 */
public class Item {

	protected int id;
    protected Sprite[] sprites;
    protected boolean animated;
    protected int maxStack;

    protected String name;

    public Item(String name, Sprite sprite) {
        this.name = name;
        this.sprites = new Sprite[] { sprite };
        this.animated = false;
        this.maxStack = 999;
    }

    public Sprite[] getSprites() {
        return sprites;
    }

    public boolean isAnimated() {
        return animated;
    }

    public String getName() {
        return name;
    }
    
    public int getId() {
		return id;
	}
    
    public Item setMaxStack(int maxStack) {
		this.maxStack = maxStack;
		return this;
	}
    
    public int getMaxStack() {
		return maxStack;
	}
}
