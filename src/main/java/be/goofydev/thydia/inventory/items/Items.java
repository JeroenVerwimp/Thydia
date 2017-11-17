package be.goofydev.thydia.inventory.items;

import java.util.HashMap;
import java.util.Map;

import be.goofydev.thydia.graphics.Sprite;
import be.goofydev.thydia.graphics.SpriteSheet;

public final class Items {

	private static int currentId = 0;
	private static Map<Integer, Item> itemIds = new HashMap<>();
	
	public static Item potion_heal = registerItem(new Item("potion_heal", new Sprite(16, 0, 0, SpriteSheet.items)));
	public static Item sword = registerItem(new Item("sword", new Sprite(16, 1, 0, SpriteSheet.items)).setMaxStack(1));
	
	private static Item registerItem(Item item) {
		item.id = currentId++;
		itemIds.put(item.getId(), item);
		return item;
	}
	
	
}
