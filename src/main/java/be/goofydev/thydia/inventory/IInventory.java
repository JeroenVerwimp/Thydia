package be.goofydev.thydia.inventory;

import java.util.Map;

import be.goofydev.thydia.inventory.items.Item;
import be.goofydev.thydia.inventory.items.ItemStack;

public interface IInventory {
	
	ItemStack[] getContents();
	
	void setContents(ItemStack[] items);
	
	int getSize();
	
	boolean contains(Item item);
	
	boolean contains(ItemStack stack);
	
	boolean container(Item item, int amount);
	
	int firstSlot(Item item);
	
	int firstSlot(ItemStack stack, boolean withAmount);
	
	int firstEmpty();
	
	int firstPartial(Item item);
	
	void setItem(int slot, ItemStack stack);
	
	ItemStack getItem(int slort);
	
	Map<Integer, ItemStack> addItem(ItemStack... stacks);
	
	Map<Integer, ItemStack> removeItem(ItemStack... stacks);
	
	void remove(Item item);
	
	void clear(int slot);
	
	
	
}
