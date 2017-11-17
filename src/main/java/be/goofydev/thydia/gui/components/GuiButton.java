package be.goofydev.thydia.gui.components;

import java.awt.Rectangle;

import be.goofydev.thydia.Game;
import be.goofydev.thydia.graphics.Font;
import be.goofydev.thydia.graphics.Screen;

public class GuiButton extends GuiComponent {

	public interface ClickHandler {
		void click(int x, int y, int button);
	}
	
	private String text;
	private ClickHandler handler;
	
	public GuiButton(int x, int y, int width, int height, String text, ClickHandler clickHandler) {
		super(x, y, width, height);
		this.text = text;
		this.handler = clickHandler;
	}

	@Override
	public void onInit() {
		
	}
	
	@Override
	public void onDraw(Screen screen) {
		screen.drawFullRect(x, y, width, height, 0xff555555, false);
		Font.render(screen, x, y, text);
	}

	@Override
	public void onUpdate() {
	}
	
	public void setText(String text) {
		this.text = text;
	}
	
	public String getText() {
		return text;
	}

	@Override
	public void onClick(int x, int y, int button) {
		Rectangle rect = new Rectangle(this.x * Game.scale, this.y * Game.scale, this.width * Game.scale, this.height * Game.scale);
		if(rect.contains(x, y)) {
			this.handler.click(x, y, button);
		}
	}
}
