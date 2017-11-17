package be.goofydev.thydia.util;

import be.goofydev.thydia.graphics.Screen;

/**
 * Created by Jeroen on 9/11/2015.
 */
public class Debug {

    private Debug() {
    }

    public static void drawRect(Screen screen, int x, int y, int width, int height, boolean fixed) {
        drawRect(screen, x, y, width, height, 0xffFF0000, fixed);
    }

    public static void drawRect(Screen screen, int x, int y, int width, int height, int color, boolean fixed) {
        screen.drawRect(x, y, width, height, color, fixed);
    }
}
