package engine.game.objects.text;

import engine.rendering.texture.Texture;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

public class Font {

    /**
     * Starting character.
     */
    final public static int STARTING_CHARACTER = 32;

    /**
     * Font's maximum characters.
     */
    final public static int MAX_CHARACTERS = 256;

    /**
     * Font's characters' rendered component.
     */
    final private Texture[] characters;

    /**
     * Font's character's height (in pixels).
     */
    final private int pixelHeight;

    /**
     * Creates a new Font instance.
     *
     * @param path Font's path
     */
    public Font(final String path) {
        this.characters = new Texture[Font.MAX_CHARACTERS - Font.STARTING_CHARACTER];

        int pixelHeight = 0;

        for(int i = Font.STARTING_CHARACTER; i < Font.MAX_CHARACTERS; i++) {
            try {
                final Texture texture = new Texture(path + "/" + i);
                this.characters[i - Font.STARTING_CHARACTER] = texture;

                if(i == Font.STARTING_CHARACTER) {
                    pixelHeight = texture.getHeight();
                } else if(texture.getHeight() != pixelHeight) {
                    System.err.println("The character '" + (char) i + "'  #" + i + "'s height is not correct");
                    System.err.println("Normal height: " + pixelHeight + " ; Character's height: " + texture.getHeight());
                    new Exception().printStackTrace();
                    System.exit(1);
                }
            } catch(final NullPointerException e) {
                // TODO: Set unknown character
            }
        }

        this.pixelHeight = pixelHeight;
    }

    /**
     * Returns a Font's character's texture.
     *
     * @param character Character to return
     * @return Font.character[character - starting]
     */
    @Contract(pure = true)
    final public Texture getCharacterTexture(final char character) {
        return this.characters[character - Font.STARTING_CHARACTER];
    }

    /**
     * Returns the width of a text (in pixels).
     *
     * @param text Text
     * @return new int
     */
    final public int getTextPixelWidth(@NotNull final String text) {
        final char[] characters = text.toCharArray();
        int width = 0;

        for(final char character : characters) {
            width += this.characters[character - Font.STARTING_CHARACTER].getWidth();
        }

        return Math.max(0, width - 1);
    }

    /**
     * Returns the Font's height (in pixels).
     *
     * @return Font.pixelHeight
     */
    final public int getPixelHeight() {
        return this.pixelHeight;
    }

}