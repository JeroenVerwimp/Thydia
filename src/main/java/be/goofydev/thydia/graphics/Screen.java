package be.goofydev.thydia.graphics;

/**
 * Created by Jeroen on 31/10/2015.
 */
public class Screen {

    public int width, height;
    public int[] pixels;

    public int xOffset, yOffset;

    public Screen(int width, int height) {
        this.width = width;
        this.height = height;
        this.pixels = new int[width * height];
    }

    public void clear() {
        for(int i = 0; i < pixels.length; i++) {
            pixels[i] = 0;
        }
    }

    public void renderSprite(int xp, int yp, Sprite sprite, boolean fixed) {
        if(fixed) {
            xp -= xOffset;
            yp -= yOffset;
        }
        for(int y = 0; y < sprite.getHeight(); y++) {
            int ya = y + yp;
            for(int x = 0; x < sprite.getWidth(); x++) {
                int xa = x + xp;
                if(xa < 0 ||xa >= width || ya < 0 || ya >= height) continue;

                int color = sprite.pixels[x + y * sprite.getWidth()];
                if(color != 0xffFF00FF)
                    pixels[xa + ya * width] = color;
            }
        }

    }

    public void renderTile(int xp, int yp, Sprite sprite) {
        xp -= xOffset;
        yp -= yOffset;
        for(int y = 0; y < sprite.SIZE; y++) {
            int ya = y + yp;
            for(int x = 0; x < sprite.SIZE; x++) {
                int xa = x + xp;
                if(xa < -sprite.SIZE ||xa >= width || ya < 0 || ya >= height) break;
                if(xa < 0) xa = 0;
                pixels[xa + ya * width] = sprite.pixels[x + y * sprite.SIZE];
            }
        }
    }

    public void renderProjectile(int xp, int yp, Sprite sprite) {
        xp -= xOffset;
        yp -= yOffset;
        for(int y = 0; y < sprite.SIZE; y++) {
            int ya = y + yp;
            for(int x = 0; x < sprite.SIZE; x++) {
                int xa = x + xp;
                if(xa < -sprite.SIZE ||xa >= width || ya < 0 || ya >= height) break;
                if(xa < 0) xa = 0;

                int color = sprite.pixels[x + y * sprite.SIZE];
                if(color != 0xffff00ff)
                    pixels[xa + ya * width] = color;
            }
        }
    }

    public void renderEntity(int xp, int yp, Sprite sprite, int flip) {
        xp -= xOffset;
        yp -= yOffset;
        for(int y = 0; y < sprite.getWidth(); y++) { // 32
            int ya = y + yp;
            int ys = (flip == 2 || flip == 3 ? 31 - y : y);
            for(int x = 0; x < sprite.getWidth(); x++) { // 32
                int xa = x + xp;
                int xs = (flip == 1 || flip == 3 ? sprite.getWidth() - 1 - x : x); // 32 - 1
                if(xa < 0 || xa >= width || ya < 0 || ya >= height) break;
                if(xa < 0) xa = 0;
                int color = sprite.pixels[xs + ys * sprite.getWidth()]; // 32
                if(color != 0xffff00ff)
                    pixels[xa + ya * width] = color;
            }
        }
    }

    public void setOffset(int xOffset, int yOffset) {
        this.xOffset = xOffset;
        this.yOffset = yOffset;
    }

    public void drawRect(int xp, int yp, int width, int height, int color, boolean fixed) {
        if(fixed) {
            xp -= xOffset;
            yp -= yOffset;
        }
        for(int x = xp; x < xp + width + 1; x++) {
            if(x < 0 || x >= this.width || yp >= this.height) continue;
            if(yp > 0) pixels[x + yp * this.width] = color;
            if(yp + height >= this.height) continue;
            if(yp + height> 0) pixels[x + (yp + height) * this.width] = color;
        }
        for(int y = yp; y < yp + height + 1; y++) {
            if(xp >= this.width || y < 0 || y >= this.height) continue;
            if(xp > 0) pixels[xp + y * this.width] = color;
            if(xp + width >= this.width) continue;
            if(xp + width> 0) pixels[(xp + width) + y * this.width] = color;
        }
    }

    public void drawFullRect(int xp, int yp, int width, int height, int color, boolean fixed) {
        if(fixed) {
            xp -= xOffset;
            yp -= yOffset;
        }
        for(int x = xp; x < xp + width + 1; x++) {
            for(int y = yp; y < yp + height + 1; y++) {
                if(x < 0 || x >= this.width || y >= this.height || y < 0) continue;
                pixels[x + y * this.width] = color;
            }
        }
    }

}
