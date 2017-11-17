package be.goofydev.thydia.gui;

import be.goofydev.thydia.Game;
import be.goofydev.thydia.entity.EntityItem;
import be.goofydev.thydia.entity.EntitySign;
import be.goofydev.thydia.entity.mob.EntityPlayer;
import be.goofydev.thydia.entity.mob.animals.EntityChicken;
import be.goofydev.thydia.graphics.Screen;
import be.goofydev.thydia.gui.components.GuiButton;
import be.goofydev.thydia.gui.components.GuiButton.ClickHandler;
import be.goofydev.thydia.inventory.items.Items;
import be.goofydev.thydia.level.Level;
import be.goofydev.thydia.level.generators.LevelGeneratorImage;
import be.goofydev.thydia.sounds.Sound;
import be.goofydev.thydia.util.TileCoord;

public class GuiMainMenu extends Gui {

	public GuiMainMenu(int width, int height) {
		super(0, 0, width, height);
		addComponent(new GuiButton(Game.width / 2 - 31, 60, 63, 17, "Play", new ClickHandler() {
			@Override
			public void click(int x, int y, int button) {
				Level level = new LevelGeneratorImage("/levels/level03.png").generate();
				Game.instance.setLevel(level);
				
				level.addItem(new EntityItem(new TileCoord(10, 4), Items.potion_heal));
				level.addItem(new EntityItem(new TileCoord(12, 4), Items.potion_heal));
				level.addItem(new EntityItem(new TileCoord(14, 4), Items.potion_heal));
				level.addItem(new EntityItem(new TileCoord(16, 4), Items.potion_heal));
				level.addItem(new EntityItem(new TileCoord(18, 4), Items.potion_heal));
				level.addItem(new EntityItem(new TileCoord(20, 4), Items.potion_heal));
				level.addItem(new EntityItem(new TileCoord(24, 4), Items.sword));
				level.addItem(new EntityItem(new TileCoord(26, 4), Items.sword));
				level.addItem(new EntityItem(new TileCoord(28, 4), Items.sword));
				level.addItem(new EntityItem(new TileCoord(30, 4), Items.sword));
				level.addItem(new EntityItem(new TileCoord(32, 4), Items.sword));
				level.addItem(new EntityItem(new TileCoord(34, 4), Items.sword));
				
				level.addEntity(new EntityChicken(new TileCoord(4, 10)));				
				level.addEntity(new EntitySign(new TileCoord(10, 10), "Dit is een test sign"));
				
				EntityPlayer player = new EntityPlayer(new TileCoord(4, 4), Game.instance.getKeyboard());
				level.addEntity(player);
				Game.instance.setPlayer(player);
				
				Sound.music_bg.stop();
				Game.instance.setGui(null);
			}
		}));
		addComponent(new GuiButton(Game.width / 2 - 31, 85, 63, 17, "Exit", new ClickHandler() {
			@Override
			public void click(int x, int y, int button) {
				Game.instance.stop();
			}
		}));		
	}

	@Override
	public void onInit() {
		Sound.music_bg.play();
	}

	@Override
	public void onDraw(Screen screen) {
		screen.drawFullRect(0, 0, screen.width, screen.height, 0xff559955, false);
	}

	@Override
	public void onUpdate() {
	}

	@Override
	public boolean shouldPauseGame() {
		return true;
	}
	
}
