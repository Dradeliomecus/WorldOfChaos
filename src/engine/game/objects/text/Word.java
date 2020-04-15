package engine.game.objects.text;

import engine.CoreEngine;
import engine.game.objects.GameObject;
import engine.math.Vector2f;

final class Word extends GameObject {

    /**
     * Creates a new Word instance.
     *
     * @param characters Word's characters
     * @param coreEngine Pointer to the CoreEngine
     */
    Word(final Character[] characters, final CoreEngine coreEngine) {
        super("Word for text", Word.getWidthFromCharacters(characters), characters[0].getHeight());

        this.addToEngine(coreEngine);

        float xPos = 0.0f;
        for(final Character character : characters) {
            this.addChildInstantly(character);

            character.setPosition(new Vector2f(xPos, 0));

            xPos += character.getWidth();
        }
    }

    /**
     * Returns the sum of all the characters' width.
     *
     * @param characters Characters
     * @return new float
     */
    private static float getWidthFromCharacters(final Character[] characters) {
        float width = 0;

        for(final Character character : characters) {
            width += character.getPixelWidth();
        }

        return width;
    }

}