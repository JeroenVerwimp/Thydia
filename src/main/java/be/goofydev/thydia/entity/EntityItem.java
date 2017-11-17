package be.goofydev.thydia.entity;

import be.goofydev.thydia.graphics.Screen;
import be.goofydev.thydia.inventory.items.Item;
import be.goofydev.thydia.util.TileCoord;

/**
 * Created by Jeroen on 1/11/2015.
 */
public class EntityItem extends Entity {

    private Item item;
    private int anim;
    private int currAnim = 0;

    public EntityItem(int x, int y, Item item) {
        super(x, y);
        setCollisionBox(0, 0, 16, 16);
        this.item = item;
    }

    public EntityItem(TileCoord tileCoord, Item item) {
    	this(tileCoord.getX(), tileCoord.getY(), item);
	}

	@Override
    public void update() {
        if(item.isAnimated()) {
            if(anim % 120 == 0) {
                currAnim++;
                if(currAnim >= item.getSprites().length) currAnim = 0;
            }

            if(anim < 7500) anim++;
            else anim = 0;
        }
  
    }

    @Override
    public void render(Screen screen) {
        screen.renderEntity(x, y, item.getSprites()[currAnim], 0);
    }
    
    public Item getItem() {
		return item;
	}
}
