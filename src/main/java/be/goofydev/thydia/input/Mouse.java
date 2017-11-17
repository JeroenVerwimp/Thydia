package be.goofydev.thydia.input;

import be.goofydev.thydia.Game;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

/**
 * Created by Jeroen on 2/11/2015.
 */
public class Mouse implements MouseListener, MouseMotionListener {

    private static int mouseX = -1;
    private static int mouseY = -1;
    private static int mouseB = -1;

    public static int getX() {
        return mouseX;
    }

    public static int getY() {
        return mouseY;
    }

    public static int getButton() {
        return mouseB;
    }

    public Mouse(Game game) {
        game.addMouseListener(this);
        game.addMouseMotionListener(this);
    }

    public void mouseDragged(MouseEvent e) {
        mouseX = e.getX();
        mouseY = e.getY();
    }

    public void mouseMoved(MouseEvent e) {
        mouseX = e.getX();
        mouseY = e.getY();
    }

    public void mouseClicked(MouseEvent e) {
    }

    public void mousePressed(MouseEvent e) {
        mouseB = e.getButton();
    }

    public void mouseReleased(MouseEvent e) {
        mouseB = -1;

    	if(Game.instance.getGui() != null) {
    		Game.instance.getGui().click(e.getX(), e.getY(), e.getButton());
    	}
    }

    public void mouseEntered(MouseEvent e) {
    }

    public void mouseExited(MouseEvent e) {
    }
}
