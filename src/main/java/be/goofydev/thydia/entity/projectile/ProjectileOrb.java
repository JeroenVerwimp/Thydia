package be.goofydev.thydia.entity.projectile;

import be.goofydev.thydia.entity.Entity;
import be.goofydev.thydia.entity.mob.animals.EntityChicken;
import be.goofydev.thydia.graphics.Screen;
import be.goofydev.thydia.graphics.Sprite;

/**
 * Created by Jeroen on 2/11/2015.
 */
public class ProjectileOrb extends Projectile {

    public static final int FIRE_RATE = 15;

    public ProjectileOrb(int x, int y, double direction) {
        super(x, y, direction);
        this.setCollisionBox(13, 12, 7, 7);
        this.range = 450;
        this.speed = 4;
        this.damage = 20;
        this.sprite = Sprite.proj_wizzard_orb;

        nx = speed * Math.cos(angle);
        ny = speed * Math.sin(angle);
    }

    @Override
    public void update() {
        move();
    }

    protected void move() {
    	if(!collision((int)(x + nx), (int)(y + ny))) {
            x += nx;
            y += ny;
    	} else {
    		remove();
    	}

        if(distance() > range) {
            remove();
        }
    }

    private double distance() {
        double dist = Math.sqrt(Math.abs((xOrigin - x) * (xOrigin - x) + (yOrigin - y) * (yOrigin - y)));
        return dist;
    }

    @Override
    public void render(Screen screen) {
        screen.renderProjectile((x + 8), (y + 8), sprite);
        // screen.renderProjectile(x, y, sprite);
    }
    
    @Override
    public void onEntityCollision(Entity other) {
    	if(other instanceof EntityChicken) {
    		other.remove();
    		this.remove();
    	}
    }
    
}
