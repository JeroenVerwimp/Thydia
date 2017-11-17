package be.goofydev.thydia.level.tile;

import be.goofydev.thydia.graphics.Screen;
import be.goofydev.thydia.graphics.Sprite;

/**
 * Created by Jeroen on 31/10/2015.
 */
public class TileBasic extends Tile {

    public TileBasic(int id, Sprite sprite) {
        super(id, sprite);
    }

    @Override
    public void update() {
    }

    @Override
    public void render(int x, int y, Screen screen) {
        screen.renderTile(x << 4, y << 4, this.sprite); // <<4 == * 16 ---- back to pixel percision (not tile persiction)
    }
}
