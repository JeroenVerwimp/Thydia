package be.goofydev.thydia.entity.mob;

import be.goofydev.thydia.graphics.Screen;
import be.goofydev.thydia.graphics.Sprite;
import be.goofydev.thydia.graphics.SpriteSheet;
import be.goofydev.thydia.util.Direction;
import be.goofydev.thydia.util.TileCoord;

public class EntityPlayerMP extends EntityLiving {

	protected Sprite[][] sprites;
	protected int anim = 0;

	protected int health;
	protected int maxHealth;
	protected int step;
	
	public EntityPlayerMP(TileCoord tileCoord) {
		this(tileCoord.getX(), tileCoord.getY());
	}
	public EntityPlayerMP(int x, int y) {
		super(x, y);
		this.setCollisionBox(9, 20, 14, 12);
		this.sprites = new Sprite[3][3];
		this.step = 1;
		this.maxHealth = 10;
		this.health = 4;
		
		SpriteSheet sheet = SpriteSheet.npc;
		sprites[0][0] = new Sprite(32, 0, 0, sheet); // up
		sprites[0][1] = new Sprite(32, 1, 0, sheet); // up
		sprites[0][2] = new Sprite(32, 2, 0, sheet); // up
		sprites[1][0] = new Sprite(32, 3, 0, sheet); // right
		sprites[1][1] = new Sprite(32, 4, 0, sheet); // right
		sprites[1][2] = new Sprite(32, 5, 0, sheet); // right
		sprites[2][0] = new Sprite(32, 6, 0, sheet); // down
		sprites[2][1] = new Sprite(32, 7, 0, sheet); // down
		sprites[2][2] = new Sprite(32, 8, 0, sheet); // down
	}

	@Override
	public void update() {
		
	}

	@Override
	public void render(Screen screen) {
		int dirInt = direction.getDirTextureInt();
		int animInt = 0;

		if (moving) {
			if (anim % 40 > 20)
				animInt = 1;
			else
				animInt = 2;
		}

		sprite = sprites[dirInt][animInt];
		screen.renderEntity(x, y, sprite, (direction == Direction.LEFT ? 1 : 0));
	}

}
