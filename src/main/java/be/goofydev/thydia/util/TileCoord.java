package be.goofydev.thydia.util;

/**
 * Created by Jeroen on 2/11/2015.
 */
public class TileCoord {

    private int x, y;
    private final int TILE_SIZE = 4; // << 4 = * 16

    public TileCoord(int x, int y) {
        this.x = (x << TILE_SIZE) + TILE_SIZE * 2;
        this.y = (y << TILE_SIZE) + TILE_SIZE * 2;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x << TILE_SIZE;
    }

    public void setY(int y) {
        this.y = y << TILE_SIZE;
    }
}
