package be.goofydev.thydia.level.tile;

import java.util.HashMap;
import java.util.Map;

import be.goofydev.thydia.graphics.Sprite;
import be.goofydev.thydia.graphics.SpriteSheet;

public class Tiles {

	private static int currentId = 0;
	private static Map<Integer, Tile> tiles = new HashMap<>();
	
	public static Tile voidTile = registerTile(new TileBasic(0, new Sprite(16, 0x1B87E0)));
	public static Tile dirt = registerTile(new TileBasic(0, new Sprite(16, 0, 0, SpriteSheet.tiles)));
	public static Tile grass = registerTile(new TileBasic(0, new Sprite(16, 1, 0, SpriteSheet.tiles)));
	public static Tile wall = registerTile(new TileBasicSolid(0, new Sprite(16, 2, 0, SpriteSheet.tiles)));
	public static Tile wall_front = registerTile(new TileBasicSolid(0, new Sprite(16, 3, 0, SpriteSheet.tiles)));

	private static Tile registerTile(Tile tile) {
		tile.id = currentId++;
		tiles.put(tile.getId(), tile);
		return tile;
	}
	
	public static Tile getTileById(int id) {
		return tiles.get(id);
	}
	
}
