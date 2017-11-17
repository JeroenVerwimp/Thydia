package be.goofydev.thydia.graphics;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * Created by Jeroen on 31/10/2015.
 */
public class SpriteSheet {

    private String path;
    public final int WIDTH;
    public final int HEIGHT;
    private int width, height;
    public int[] pixels;

    public static SpriteSheet tiles = new SpriteSheet("/textures/sheets/level/spritesheet.png", 256);
    public static SpriteSheet items = new SpriteSheet("/textures/sheets/items/items.png", 256, 256);
    public static SpriteSheet staticEntities = new SpriteSheet("/textures/sheets/entities/static.png", 48);

    public static SpriteSheet animals = new SpriteSheet("/textures/sheets/entities/animals/animals.png", 288, 288);

    public static SpriteSheet npc = new SpriteSheet("/textures/sheets/entities/npc/npc.png", 288);
    public static SpriteSheet hair = new SpriteSheet("/textures/sheets/entities/npc/hair.png", 288, 288);
    public static SpriteSheet hair2 = new SpriteSheet("/textures/sheets/entities/npc/hair2.png", 288, 288);
    public static SpriteSheet eyes = new SpriteSheet("/textures/sheets/entities/npc/eyes.png", 288, 32);

    public static SpriteSheet wizzard_projectiles = new SpriteSheet("/textures/sheets/entities/projectiles/wizzard.png", 48, 48);

    public static SpriteSheet gui_hud = new SpriteSheet("/textures/sheets/gui/hud.png", 256, 256);

    public SpriteSheet(SpriteSheet sheet, int x, int y, int width, int height, int spriteSize) {
        int xx = x * spriteSize;
        int yy = y * spriteSize;
        int w = width * spriteSize;
        int h = height * spriteSize;
        pixels = new int[x * h];

        this.WIDTH = w;
        this.HEIGHT = h;

        for(int y0 = 0; y0 < h; y0++) {
            int yp = yy + y0;
            for(int x0= 0; x0 < w; x0++) {
                int xp = xx + x0;
                pixels[x0 + y0 * w] = sheet.pixels[xp + yp * sheet.WIDTH];
            }
        }
    }

    public SpriteSheet(String path, int size) {
        this(path, size, size);
    }

    public SpriteSheet(String path, int width, int height) {
        this.path = path;
        this.WIDTH = width;
        this.HEIGHT = height;
        load();
    }

    public int[] getPixels() {
        return pixels;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    private void load() {
        try {
            BufferedImage image = ImageIO.read(SpriteSheet.class.getResource(path));
            width = image.getWidth();
            height = image.getHeight();
            pixels = new int[width * height];
            image.getRGB(0, 0, width, height, pixels, 0, width);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

