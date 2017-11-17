package be.goofydev.thydia.inventory.items;

public class ItemStack {

	private Item item;
	private int amount;
	
	public ItemStack(Item item) {
		this(item, 1);
	}
	
	public ItemStack(Item item, int amount) {
		this.item = item;
		this.amount = amount;
	}
	
	public Item getItem() {
		return item;
	}
	
	public void setItem(Item item) {
		this.item = item;
	}
	
	public int getAmount() {
		return amount;
	}
	
	public void setAmount(int amount) {
		this.amount = amount;
	}

	public ItemStack copy() {
		ItemStack stack = new ItemStack(item, amount);
		return stack;
	}

	public boolean isSimilar(ItemStack itemStack) {
		if(item.equals(itemStack.item))
			return true;
		
		return false;
	}
	
}
