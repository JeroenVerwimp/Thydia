package be.goofydev.thydia.gui;

import be.goofydev.thydia.Game;
import be.goofydev.thydia.graphics.Screen;
import be.goofydev.thydia.inventory.Inventory;
import be.goofydev.thydia.inventory.items.ItemStack;

public class GuiInventory extends Gui{

	private static int width = 117;
	private static int height = 60;
	private static int x = (Game.instance.getScreen().width / 2) - (width / 2);
	private static int y = (Game.instance.getScreen().height / 2) - (height / 2);
	
	private Inventory inventory;
	
	public GuiInventory(Inventory inventory) {
		super(x, y, width, height);
		this.inventory = inventory;
	}

	@Override
	public void onInit() {
		
	}

	@Override
	public void onDraw(Screen screen) {
		screen.drawFullRect(x, y, width, height, 0xff432589, false);
		
		for(int xa = 0; xa < 6; xa++) {
			for(int ya = 0; ya < 3; ya++) {
				int xb = x + 3 + (xa * 19);
				int yb = y + 3 + (ya * 19);
				screen.drawFullRect(xb, yb, 16, 16, 0xff5e5e5e, false);
				
				ItemStack stack = inventory.getContents()[xa + (ya * 5)];
				if(stack != null)
					screen.renderSprite(xb, yb, stack.getItem().getSprites()[0], false);
			}
		}
	}

	@Override
	public void onUpdate() {
	}
	
	@Override
	public void onClick(int x, int y, int button) {
	}
	
	
	
}
