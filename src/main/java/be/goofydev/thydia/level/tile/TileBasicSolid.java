package be.goofydev.thydia.level.tile;

import be.goofydev.thydia.graphics.Sprite;

/**
 * Created by Jeroen on 31/10/2015.
 */
public class TileBasicSolid extends TileBasic {

    public TileBasicSolid(int id, Sprite sprite) {
        super(id, sprite);
    }

    public boolean solid() {
        return true;
    }
}
