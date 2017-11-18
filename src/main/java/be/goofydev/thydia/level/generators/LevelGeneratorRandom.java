package be.goofydev.thydia.level.generators;

import java.util.Random;

import be.goofydev.thydia.level.Level;
import be.goofydev.thydia.level.tile.Tiles;

public class LevelGeneratorRandom implements ILevelGenerator {
	
	private int width, height;
	
	public LevelGeneratorRandom(int width, int height) {
		this.width = width;
		this.height = height;
	}
	
	@Override
	public Level generate() {
		int[] tiles = new int[width * height];
		
		for (int i = 0; i < tiles.length; i++) {
			tiles[i] = Tiles.grass.getId();
		}
		
		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				if(x * y % 10 < 7 && new Random().nextInt(15) > 5) {
					tiles[x + y * width] = Tiles.dirt.getId();
				}
			}
		}
		
		return new Level(width, height, tiles);
	}

}
