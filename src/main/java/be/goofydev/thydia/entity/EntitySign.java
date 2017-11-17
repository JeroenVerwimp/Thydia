package be.goofydev.thydia.entity;

import be.goofydev.thydia.Game;
import be.goofydev.thydia.entity.mob.EntityPlayer;
import be.goofydev.thydia.graphics.Screen;
import be.goofydev.thydia.graphics.Sprite;
import be.goofydev.thydia.graphics.SpriteSheet;
import be.goofydev.thydia.util.TileCoord;

public class EntitySign extends Entity {

	private String text;
	
	public EntitySign(TileCoord tileCoord, String text) {
		super(tileCoord.getX(), tileCoord.getY());
		this.setCollisionBox(0, 0, 16, 16);
		this.text = text;
	}

	@Override
	public void update() {
	}

	@Override
	public void onEntityCollision(Entity other) {
		if(other instanceof EntityPlayer && Game.instance.getKeyboard().space) {
			System.out.println("Sign: " + text);
		}
	}
	
	@Override
	public void render(Screen screen) {
		screen.renderSprite(x, y, new Sprite(16, 0, 0, SpriteSheet.staticEntities), true);
	}

}
