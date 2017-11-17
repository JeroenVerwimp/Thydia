package be.goofydev.thydia.entity.mob;

import java.util.ArrayList;
import java.util.List;

import be.goofydev.thydia.Game;
import be.goofydev.thydia.entity.Entity;
import be.goofydev.thydia.entity.EntityItem;
import be.goofydev.thydia.entity.projectile.Projectile;
import be.goofydev.thydia.entity.projectile.ProjectileOrb;
import be.goofydev.thydia.graphics.Screen;
import be.goofydev.thydia.graphics.Sprite;
import be.goofydev.thydia.input.Keyboard;
import be.goofydev.thydia.input.Mouse;
import be.goofydev.thydia.inventory.Inventory;
import be.goofydev.thydia.inventory.items.Item;
import be.goofydev.thydia.inventory.items.ItemStack;
import be.goofydev.thydia.inventory.items.Items;
import be.goofydev.thydia.sounds.Sound;
import be.goofydev.thydia.util.TileCoord;

/**
 * Created by Jeroen on 31/10/2015.
 */
public class EntityPlayer extends EntityPlayerMP {

	private Keyboard input;

	private int fireRate = 0;
	protected List<Projectile> projectiles = new ArrayList<Projectile>();
	
	private Inventory inventory;

	public EntityPlayer(Keyboard input) {
		this(0, 0, input);
	}

	public EntityPlayer(TileCoord coords, Keyboard input) {
		this(coords.getX(), coords.getY(), input);
	}

	public EntityPlayer(int x, int y, Keyboard input) {
		super(x, y);
		this.input = input;
		this.fireRate = ProjectileOrb.FIRE_RATE;
		this.inventory = new Inventory(6 * 3);
	}

	protected void shoot(int x, int y, double dir) {
		Projectile proj = new ProjectileOrb(x, y, dir);
		projectiles.add(proj);
		level.addEntity(proj);
		Sound.proj_pop.play();
	}

	@Override
	public void update() {
		if (fireRate > 0)
			fireRate--;

		int xa = 0, ya = 0;
		int stepTemp = input.ctrl ? 3 : 1;
		
		if(Game.instance.getGui() == null) {
			if (input.up)
				ya -= stepTemp;
			if (input.down)
				ya += stepTemp;
			if (input.left)
				xa -= stepTemp;
			if (input.right)
				xa += stepTemp;	
		}
		
		
		if(xa != 0 || ya != 0) {
			move(xa, ya);
			moving = true;
		} else {
			moving = false;
		}
		
		if (anim < 7500) {
			anim++;
		} else {
			anim = 0;
		}
		
		if (Mouse.getButton() == 1 && fireRate == 0 && Game.instance.getGui() == null) {
			double dx = Mouse.getX() - Game.instance.getFrameWidth() / 2;
			double dy = Mouse.getY() - Game.instance.getFrameHeight() / 2;
			double direction = Math.atan2(dy, dx);
			shoot(x, y, direction);
			fireRate = ProjectileOrb.FIRE_RATE;
		}

		clear();
	}

	private void clear() {
		for (int i = 0; i < projectiles.size(); i++) {
			if (projectiles.get(i).isRemoved())
				projectiles.remove(i);
		}
	}

	@Override
	public void onEntityCollision(Entity other) {
		if (other instanceof EntityItem) {
			Item item = ((EntityItem) other).getItem();
			if (item.equals(Items.potion_heal)) {
				System.out.println("picked up health potion. +1 health");
				this.health += 1;
				other.remove();
			} else if(item.equals(Items.sword)) {
				if(inventory.addItem(new ItemStack(item, 1)).isEmpty()) {
					other.remove();
				}
			}
		}
	}

	@Override
	public void render(Screen screen) {
		super.render(screen);

		// HUD
		for (int i = 0; i < maxHealth; i++) {
			screen.renderSprite(5 + (4 * i), 5, (i < health ? Sprite.health_full : Sprite.health_empty), false);
		}
	}
	
	public Inventory getInventory() {
		return inventory;
	}
}
