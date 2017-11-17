package be.goofydev.thydia.input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import be.goofydev.thydia.Game;
import be.goofydev.thydia.gui.GuiIngameMenu;
import be.goofydev.thydia.gui.GuiInventory;
import be.goofydev.thydia.gui.GuiMainMenu;
import be.goofydev.thydia.sounds.Sound;

/**
 * Created by Jeroen on 31/10/2015.
 */
public class Keyboard implements KeyListener {

    private boolean[] keys = new boolean[120]; //65536
    public boolean up, down, left, right, ctrl, space;
    
    public void update() {
        up = keys[KeyEvent.VK_Z];
        down = keys[KeyEvent.VK_S];
        left = keys[KeyEvent.VK_Q];
        right = keys[KeyEvent.VK_D];
        ctrl = keys[KeyEvent.VK_CONTROL];
        space = keys[KeyEvent.VK_SPACE];
    }

    public Keyboard(Game game) {
        game.addKeyListener(this);
    }

    public void keyPressed(KeyEvent e) {
        keys[e.getKeyCode()] = true;

        if(e.getKeyCode() == KeyEvent.VK_ADD) {
            Sound.music_bg.setVolume(Sound.music_bg.getVolume() + 1);
        }

        if(e.getKeyCode() == KeyEvent.VK_SUBTRACT) {
            Sound.music_bg.setVolume(Sound.music_bg.getVolume() - 1);
        }

        if(e.getKeyCode() == KeyEvent.VK_MULTIPLY) {
            Sound.music_bg.stop();
        }

        if(e.getKeyCode() == KeyEvent.VK_ESCAPE) {
            if(Game.instance.getGui() == null) {
                Game.instance.setGui(new GuiIngameMenu());
            } else {
            	if(!(Game.instance.getGui() instanceof GuiMainMenu)) {
                    Game.instance.setGui(null);	
            	}
            }
        }
        
        if(e.getKeyCode() == KeyEvent.VK_E) {
        	if(Game.instance.getGui() == null) {
        		Game.instance.setGui(new GuiInventory(Game.instance.getPlayer().getInventory()));
        	} else if (Game.instance.getGui() instanceof GuiInventory) {
        		Game.instance.setGui(null);
        	}
        }

    }

    public void keyReleased(KeyEvent e) {
        keys[e.getKeyCode()] = false;
    }

    public void keyTyped(KeyEvent e) {

    }
}
