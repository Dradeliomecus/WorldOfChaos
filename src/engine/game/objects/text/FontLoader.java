package engine.game.objects.text;

import engine.util.Time;
import org.jetbrains.annotations.Contract;

import java.io.File;
import java.util.HashMap;

public class FontLoader {

    /**
     * Path to the font folder in the media folder.
     */
    private static String fontFolder = "/fonts/";

    /**
     * All the fonts that have been loaded.
     */
    final private static HashMap<String, Font> fonts = new HashMap<>();


    /**
     * Returns the path to the folder that contains the fonts.
     *
     * @return FontLoader::fontFolder
     */
    @Contract(pure = true)
    public static String getFontFolder() {
        return FontLoader.fontFolder;
    }

    /**
     * Returns a Font.
     *
     * @param name Font's name
     * @return FontLoader::fonts[name]
     */
    public static Font getFont(final String name) {
        return FontLoader.fonts.get(name);
    }

    /**
     * Sets the font folder.
     *
     * @param path Font folder's path
     */
    public static void setFontFolder(final String path) {
        FontLoader.fontFolder = path;
    }

    /**
     * Loads a Font.
     *
     * @param name Font's name
     */
    public static void loadFont(final String name) {
        FontLoader.fonts.put(name, new Font(FontLoader.getFontFolder() + name));
    }

    /**
     * Loads all the fonts that are in the font folder.
     */
    public static void loadAll() {
        final long startTime = Time.getNanoTime();

        final File folder = support.File.getFolder("/media/texture" + FontLoader.getFontFolder());

        if(folder == null || !folder.isDirectory()) {
            System.err.println("Error: Folder in FontLoader is incorrect: " + FontLoader.getFontFolder());
            new Exception().printStackTrace();
            System.exit(1);
        }

        final String[] fonts = folder.list();

        for(final String font : fonts) {
            FontLoader.loadFont(font);
        }

        System.out.println("It took " + (Time.getNanoTime() - startTime) * Time.NANO_TO_MILLI + "ms to load " + fonts.length + " fonts.");
    }

}