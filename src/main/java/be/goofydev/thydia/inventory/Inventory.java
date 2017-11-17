package be.goofydev.thydia.inventory;

import java.util.HashMap;
import java.util.Map;

import be.goofydev.thydia.inventory.items.Item;
import be.goofydev.thydia.inventory.items.ItemStack;

public class Inventory implements IInventory {
	
	private int size;
	private ItemStack[] contents;
	
	public Inventory(int size) {
		this.size = size;
		contents = new ItemStack[size];
	}
	
	@Override
	public ItemStack[] getContents() {
		return contents;
	}

	@Override
	public void setContents(ItemStack[] items) {
		this.contents = items;
		this.size = items.length;
	}

	@Override
	public int getSize() {
		return size;
	}

	@Override
	public boolean contains(Item item) {
		for (ItemStack itemStack : contents) {
			if(itemStack!= null && itemStack.getItem().equals(item))
				return true;
		}
		return false;
	}

	@Override
	public boolean contains(ItemStack stack) {
		// TODO implement when implementing meta data
		return false;
	}

	@Override
	public boolean container(Item item, int amount) {
		if(amount <= 0)
			return true;
		
		for (ItemStack itemStack : contents) {
			if(itemStack != null && itemStack.getItem().equals(item)) {
				if((amount -= itemStack.getAmount()) <= 0) {
					return true;
				}
			}
		}
		
		return false;
	}

	@Override
	public int firstSlot(Item item) {
		for (int i = 0; i < contents.length; i++) {
			ItemStack itemStack = contents[i];
			if(itemStack != null && itemStack.getItem().equals(item)) {
				return i;
			}
		}
		
		return -1;
	}
	
	@Override
	public int firstSlot(ItemStack stack, boolean withAmount) {
		if(stack == null)
			return -1;
		
		for (int i = 0; i < contents.length; i++) {
			if(contents[i] == null)
				continue;
			
			if(withAmount ? stack.equals(contents[i]) : stack.isSimilar(contents[i])) {
				return i;
			}
		}
		
		return -1;
	}
	
	@Override
	public int firstEmpty() {
		for (int i = 0; i < contents.length; i++) {
			if(contents[i] == null) {
				return i;
			}
		}
		return -1;
	}

	@Override
	public int firstPartial(Item item) {
		for (int i = 0; i < contents.length; i++) {
			ItemStack itemStack = contents[i];
			if(itemStack != null && itemStack.getItem().equals(item) && itemStack.getAmount() < item.getMaxStack()) {
				return i;
			}
		}
		return -1;
	}

	@Override
	public void setItem(int slot, ItemStack stack) {
		contents[slot] = stack;
	}
	
	@Override
	public ItemStack getItem(int slot) {
		return contents[slot];
	}
	
	@Override
	public Map<Integer, ItemStack> addItem(ItemStack... stacks) {
        Map<Integer, ItemStack> leftover = new HashMap<Integer, ItemStack>();
        
        for (int i = 0; i < stacks.length; i++) {
			ItemStack stack = stacks[i];
			while(true) {
				int firstPartial = firstPartial(stack.getItem());
				
				if(firstPartial == -1) {
					int firstFree = firstEmpty();
					
					if(firstFree == -1) {
						leftover.put(i, stack);
						break;
					} else {
						if(stack.getAmount() > stack.getItem().getMaxStack()) {
							ItemStack newStack = stack.copy();
							newStack.setAmount(stack.getItem().getMaxStack());
							setItem(firstFree, newStack);
							stack.setAmount(stack.getAmount() - stack.getItem().getMaxStack());
						} else {
							setItem(firstFree, stack);
							break;
						}
					}
				} else {
					ItemStack partialStack = getItem(firstPartial);
					
					int amount = stack.getAmount();
					int partialAmount = partialStack.getAmount();
					int maxAmount = partialStack.getItem().getMaxStack();
					
					if(amount + partialAmount <= maxAmount) {
						partialStack.setAmount(amount + partialAmount);
						break;
					}
					
					partialStack.setAmount(maxAmount);
					stack.setAmount(amount + partialAmount - maxAmount);
				}
			}
		}
        
		return leftover;
	}

	@Override
	public Map<Integer, ItemStack> removeItem(ItemStack... stacks) {
        Map<Integer, ItemStack> leftover = new HashMap<Integer, ItemStack>();
        
        for (int i = 0; i < stacks.length; i++) {
			ItemStack stack = stacks[i];
			int toDelete = stack.getAmount();
			
			while(true) {
				int first = firstSlot(stack, false);
				
				if(first == -1) {
					stack.setAmount(toDelete);
					leftover.put(i, stack);
					break;
				} else {
					ItemStack itemStack = getItem(first);
					int amount = itemStack.getAmount();
					
					if(amount <= toDelete) {
						toDelete -= amount;
						clear(first);
					} else {
						itemStack.setAmount(amount - toDelete);
						setItem(first, itemStack);
						toDelete = 0;
					}
				}
				
				if(toDelete <= 0) {
					break;
				}
			}
		}
        
		return leftover;
	}

	@Override
	public void remove(Item item) {
		for (int i = 0; i < contents.length; i++) {
			ItemStack itemStack = contents[i];
			if(itemStack != null && itemStack.getItem().equals(item)) {
				clear(i);
			}
		}
		
	}

	@Override
	public void clear(int slot) {
		setItem(slot, null);
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("inventory");
		for (ItemStack itemStack : contents) {
			if(itemStack != null)
				builder.append("\n" + itemStack.getItem().getName() + " - " + itemStack.getAmount());
		}
		return builder.toString();
	}

}
