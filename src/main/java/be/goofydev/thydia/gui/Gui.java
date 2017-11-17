package be.goofydev.thydia.gui;

import java.util.ArrayList;
import java.util.List;

import be.goofydev.thydia.graphics.Screen;
import be.goofydev.thydia.gui.components.GuiComponent;

public abstract class Gui {

	protected int x;
	protected int y;
	protected int width;
	protected int height;
	private List<GuiComponent> components;
	
	public Gui(int x, int y, int width, int height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.components = new ArrayList<>();
	}
	
	public abstract void onInit();
	
	public final void init() {
		this.onInit();
		components.forEach(gui -> gui.init());
	}
	
	protected final void addComponent(GuiComponent component) {
		component.x += this.x;
		component.y += this.y;
		this.components.add(component);
	}
	
	protected final void removeComponent(GuiComponent component) {
		this.components.remove(component);
	}
	
	public abstract void onDraw(Screen screen);
	
	public final void render(Screen screen) {
		this.onDraw(screen);
		components.forEach(gui -> gui.render(screen));
	}
	
	public abstract void onUpdate();
	
	public final void update() {
		this.onUpdate();
		components.forEach(gui -> gui.update());
	}
	
	public void onClick(int x, int y, int button) {}
	
	public final void click(int x, int y, int button) {
		this.onClick(x, y, button);
		components.forEach(gui -> gui.click(x, y, button));
	}
	
	public boolean shouldPauseGame() {
		return false;
	}
	
}
