package be.goofydev.thydia.graphics;

/**
 * Created by Jeroen on 9/11/2015.
 */
public class Font {

    private static SpriteSheet font = new SpriteSheet("/fonts/arial.png", 16);
    private static Sprite[] characters = Sprite.split(font);
    private static String chars =
            "ABCDEFGHIJKLMNOPQRSTUVWXYZ" +
            "abcdefghijklmnopqrstuvwxyz" +
            "0123456789.,\'\'\"\";:!@$%()-+";

    public static void render(Screen screen, int x, int y, String text) {
        for(int i = 0; i < text.length(); i++) {
            char j = text.charAt(i);
            int index = chars.indexOf(j);
            Sprite sprite = characters[index];
            screen.renderSprite(x + 16 * i, y, sprite, false);
        }
    }

}
