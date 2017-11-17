package be.goofydev.thydia.gui;

import be.goofydev.thydia.Game;
import be.goofydev.thydia.graphics.Screen;
import be.goofydev.thydia.gui.components.GuiButton;
import be.goofydev.thydia.gui.components.GuiButton.ClickHandler;

public class GuiIngameMenu extends Gui {

	private static int width = 150;
	private static int height = 200;
	private static int x = (Game.instance.getScreen().width / 2) - (width / 2);
	private static int y = (Game.instance.getScreen().height / 2) - (height / 2);
	
	public GuiIngameMenu() {
		super(x, y, width, height);
	}

	@Override
	public void onInit() {
		this.addComponent(new GuiButton(43, height - 35, 64, 17, "Exit", new ClickHandler() {
			@Override
			public void click(int x, int y, int button) {
				if(button == 1)
					Game.instance.stop();
			}
		}));
	}

	@Override
	public void onDraw(Screen screen) {
		screen.drawFullRect(x, y, width, height, 0xff432589, false);
	}

	@Override
	public void onUpdate() {
	}

}
