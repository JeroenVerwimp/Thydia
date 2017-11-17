package be.goofydev.thydia.level.generators;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;

import be.goofydev.thydia.level.Level;
import be.goofydev.thydia.level.tile.Tiles;

public class LevelGeneratorImage implements ILevelGenerator {

	private static Map<Integer, Integer> colorToTileId;
	
	static {
		colorToTileId = new HashMap<>();
		colorToTileId.put(0xff7F7F00, Tiles.dirt.getId()); // dirt
		colorToTileId.put(0xff00FF00, Tiles.grass.getId()); // grass
		colorToTileId.put(0xff404040, Tiles.wall.getId()); // wall
		colorToTileId.put(0xff808080, Tiles.wall_front.getId()); // wall_front
	}
	
	private String path;
	private int width;
	private int height;
	private int[] tiles;
	
	public LevelGeneratorImage(String path) {
		this.path = path;
	}
	
	@Override
	public Level generate() {
		int[] tilesColors = null;
		
		try {
            BufferedImage image = ImageIO.read(LevelGeneratorImage.class.getResource(path));
            int w = image.getWidth();
            int h = image.getHeight();
            this.width = w;
            this.height = h;
            tilesColors = image.getRGB(0, 0, w, h, null, 0, w);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Exception: Could not load level file!");
        }
		
		this.tiles = new int[this.width * this.height];
		for (int i = 0; i < tilesColors.length; i++) {
			int color = tilesColors[i];
			if(colorToTileId.containsKey(color)) {
				tiles[i] = colorToTileId.get(color);
			} else {
				tiles[i] = 0;
			}
		}
		
		return new Level(width, height, tiles);
	}

}
