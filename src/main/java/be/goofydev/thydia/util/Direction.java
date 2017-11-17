package be.goofydev.thydia.util;

/**
 * Created by Jeroen on 31/10/2015.
 */
public enum Direction {

    UP(0), // 0
    RIGHT(1), // 1
    DOWN(2), // 2
    LEFT(3); // 3

    private int dir;

    Direction(int dir) {
        this.dir = dir;
    }

    public int getDirInt() {
        return dir;
    }

    public int getDirTextureInt() {
        if(dir == 3) return 1;
        return dir;
    }
}
