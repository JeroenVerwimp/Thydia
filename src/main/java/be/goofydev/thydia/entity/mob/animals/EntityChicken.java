package be.goofydev.thydia.entity.mob.animals;

import be.goofydev.thydia.entity.mob.EntityLiving;
import be.goofydev.thydia.graphics.Screen;
import be.goofydev.thydia.graphics.Sprite;
import be.goofydev.thydia.graphics.SpriteSheet;
import be.goofydev.thydia.util.Direction;
import be.goofydev.thydia.util.TileCoord;

/**
 * Created by Jeroen on 1/11/2015.
 */
public class EntityChicken extends EntityLiving {

    private Sprite[][] sprites;
    private boolean walking = false;
    private int anim = 0;
    private int time = 0;

    int xa = 0;
    int ya = 0;

    public EntityChicken(TileCoord tileCoord) {
		this(tileCoord.getX(), tileCoord.getY());
	}

    public EntityChicken(int x, int y) {
        super(x, y);
        setCollisionBox(8, 12, 16, 16);
        this.speed = 1;
        sprites = new Sprite[3][3];

        SpriteSheet sheet = SpriteSheet.animals;
        // up
        sprites[0][0] = new Sprite(32, 0, 0, sheet);
        sprites[0][1] = new Sprite(32, 1, 0, sheet);
        sprites[0][2] = new Sprite(32, 2, 0, sheet);

        // right
        sprites[1][0] = new Sprite(32, 3, 0, sheet);
        sprites[1][1] = new Sprite(32, 4, 0, sheet);
        sprites[1][2] = new Sprite(32, 5, 0, sheet);

        //down
        sprites[2][0] = new Sprite(32, 6, 0, sheet);
        sprites[2][1] = new Sprite(32, 7, 0, sheet);
        sprites[2][2] = new Sprite(32, 8, 0, sheet);
    }

	@Override
    public void update() {
        time++;

        // AI!
        if(time % (random.nextInt(50) + 30) == 0) {
            xa = random.nextInt(3) - 1;
            ya = random.nextInt(3) - 1;
            if(random.nextInt(4) == 0) {
                xa = 0;
                ya = 0;
                if(direction == Direction.UP)
                    direction = Direction.DOWN;
            }
        }


        // MOVING
        if(xa != 0 ||ya != 0){
            walking = true;
            move(xa, ya);
        } else {
            walking = false;
        }

        if(anim < 60) anim++;
        else anim = 0;
    }

    @Override
    public void render(Screen screen) {
        int dirInt = direction.getDirTextureInt();
        int animInt = 0;

        if(walking) {
            if(anim % 20 > 10) animInt = 1;
            else animInt = 2;
        }

        sprite = sprites[dirInt][animInt];
        screen.renderEntity(x, y, sprite, (direction == Direction.LEFT ? 1 : 0));
    }
}
