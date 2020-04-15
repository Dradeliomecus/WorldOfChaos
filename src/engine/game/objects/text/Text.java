package engine.game.objects.text;

import com.GameOptions;
import engine.game.components.RenderedComponent;
import engine.game.objects.GameObject;
import engine.math.Vector2f;
import engine.rendering.shader.RenderToTextureShader;
import engine.rendering.texture.Material;
import engine.rendering.texture.Texture;
import engine.util.Color;
import engine.util.Units;
import engine.util.Window;
import org.jetbrains.annotations.Contract;

import static org.lwjgl.opengl.GL30.GL_COLOR_ATTACHMENT0;

public class Text extends GameObject {

    /**
     * Number of spaces in one tab.
     */
    final public static int SPACES_IN_TAB = 4;

    /**
     * No text decoration.
     */
    final public static byte DECORATION_NONE = 0;

    /**
     * Text's underlined.
     */
    final public static byte DECORATION_UNDERLINE = 1;

    /**
     * Text's stroked.
     */
    final public static byte DECORATION_STROKE = 2;

    /**
     * Text's align left.
     */
    final public static byte ALIGN_LEFT = 0;

    /**
     * Text's align center.
     */
    final public static byte ALIGN_CENTER = 1;

    /**
     * Text's align right.
     */
    final public static byte ALIGN_RIGHT = 2;

    /**
     * Text's align justify.
     */
    final public static byte ALIGN_JUSTIFY = 3;

    /**
     * Text's font.
     */
    private Font font;

    /**
     * Text's line space (number of pixels between two lines).
     */
    private int lineSpace;

    /**
     * Text's decoration.
     */
    private byte decoration;

    /**
     * Text's alignment.
     */
    private byte alignment;

    /**
     * Text's size multiplicator.
     */
    private int size;

    /**
     * Text's color.
     */
    private Color color;

    /**
     * Text.
     */
    private String text;

    /**
     * Text's rendered component.
     */
    private RenderedComponent renderedComponent;

    /**
     * Creates a new Text instance.
     *
     * @param text Text to print
     * @param font Text's font
     * @param width Object's width
     * @param height Object's height
     */
    public Text(final String text, final Font font, final float width, final float height) {
        super("Text: " + text, width, height);

        this.setFont(font);
        this.setLineSpace(Math.round(font.getPixelHeight() / 5.0f));
        this.setTextDecoration(Text.DECORATION_NONE);
        this.setTextAlignment(Text.ALIGN_LEFT);
        this.setSize(1);
        this.setColor(Color.BLACK);
        this.setText(text);
    }

    /**
     * Prints the text on the rendered component and adds it.
     */
    final public void print() {
        final int WINDOW_MULTIPLICATOR = GameOptions.getSelect("resolution").getIndex() + 1;

        final Vector2f textureDimension = Units.openGLToPixels(new Vector2f(this.getWidth() / (this.getSize() * WINDOW_MULTIPLICATOR), this.getHeight() / (this.getSize() * WINDOW_MULTIPLICATOR)));
        final int textureWidth = Math.round(textureDimension.getX());
        final int textureHeight = Math.round(textureDimension.getY());
        final String[] words = this.getText().split(" ");

        final Texture texture = new Texture(textureWidth, textureHeight, GL_COLOR_ATTACHMENT0);
        texture.bindAsRenderTarget();
        GameObject.ignoreRenderOutOfBounds = true;

        RenderToTextureShader.setTextureScale(new Vector2f(2.0f / textureWidth, 2.0f / textureHeight));
        RenderToTextureShader.setTexturePositionScale(new Vector2f(2.0f  / textureWidth, 2.0f / textureHeight));

        int x = 0;
        int y = 0;

        for(final String wordString : words) {
            final Character[] characters = new Character[wordString.length()];

            if(characters.length == 0) {
                continue;
            }

            for(int i = 0; i < characters.length; i++) {
                characters[i] = new Character(this.getFont().getCharacterTexture(wordString.charAt(i)));
            }


            final Word word = new Word(characters, this.getCoreEngine());

            if(x + word.getWidth() - 1 > textureWidth) {
                y++;
                x = 0;
            }

            word.setPosition(new Vector2f(x, y * this.getFont().getPixelHeight()));
            word.render(RenderToTextureShader.getInstance(), null);

            x += word.getWidth() - 1 + this.getFont().getCharacterTexture(' ').getWidth();

            if(x >= textureWidth) {
                y++;
                x = 0;
            }
        }

        GameObject.ignoreRenderOutOfBounds = false;
        Window.bindAsRenderTarget();

        if(this.getRenderedComponent() == null) {
            final RenderedComponent renderedComponent = new RenderedComponent(new Material(texture, this.getColor()), this.getWidth(), this.getHeight());
            this.setRenderedComponent(renderedComponent);
        } else {
            this.getRenderedComponent().getMaterial().setImage(texture);
        }
    }

    /**
     * Returns the Text's font.
     *
     * @return Text.fontFolder
     */
    @Contract(pure = true)
    final public Font getFont() {
        return this.font;
    }

    /**
     * Returns the text's line space.
     *
     * @return Text.lineSpace
     */
    @Contract(pure = true)
    final public int getLineSpace() {
        return this.lineSpace;
    }

    /**
     * Returns the Text's decoration.
     *
     * @return Text.decoration
     */
    @Contract(pure = true)
    final public byte getTextDecoration() {
        return this.decoration;
    }

    /**
     * Returns the Text's alignment.
     *
     * @return Text.alignment
     */
    @Contract(pure = true)
    final public byte getTextAlignment() {
        return this.alignment;
    }

    /**
     * Returns the Text's size multiplicator.
     *
     * @return Text.size
     */
    @Contract(pure = true)
    final public int getSize() {
        return this.size;
    }

    /**
     * Returns the Text's color.
     *
     * @return Text.color
     */
    @Contract(pure = true)
    final public Color getColor() {
        return this.color;
    }

    /**
     * Returns the text.
     *
     * @return Text.text
     */
    @Contract(pure = true)
    final public String getText() {
        return this.text;
    }

    /**
     * Returns the Text's rendered component.
     *
     * @return Text.renderedComponent
     */
    @Contract(pure = true)
    private RenderedComponent getRenderedComponent() {
        return this.renderedComponent;
    }

    /**
     * Returns the Text's material.
     *
     * @return Text.renderedComponent.material
     */
    final public Material getTextMaterial() {
        return this.getRenderedComponent().getMaterial();
    }

    /**
     * Sets the Text's depth.
     *
     * @param depth Depth to set
     */
    public void setTextDepth(final float depth) {
        this.setDepth(depth);
    }

    /**
     * Sets the Text's font.
     *
     * @param font Font to set
     */
    final public void setFont(final Font font) {
        this.font = font;
    }

    /**
     * Sets the Text's line space.
     *
     * @param lineSpace Line space to set (in pixels)
     */
    final public void setLineSpace(final int lineSpace) {
        this.lineSpace = lineSpace;
    }

    /**
     * Sets the Text's decoration.
     *
     * @param textDecoration Text decoration to set
     */
    final public void setTextDecoration(final byte textDecoration) {
        this.decoration = textDecoration;
    }

    /**
     * Sets the Text's alignment.
     *
     * @param textAlignment Text alignment to set
     */
    final public void setTextAlignment(final byte textAlignment) {
        this.alignment = textAlignment;
    }

    /**
     * Sets the Text's size multiplicator.
     *
     * @param size Size multiplicator to set
     */
    final public void setSize(final int size) {
        this.size = size;
    }

    /**
     * Sets the Text's color.
     *
     * @param color Color to set
     */
    final public void setColor(final Color color) {
        this.color = color;
    }

    /**
     * Sets the text.
     *
     * @param text Text to set
     */
    final public void setText(final String text) {
        this.text = text;
    }

    /**
     * Sets the Rendered component.
     *
     * @param renderedComponent Rendered component to set
     */
    private void setRenderedComponent(final RenderedComponent renderedComponent) {
        if(this.getRenderedComponent() != null) {
            this.removeComponent(this.getRenderedComponent());
        }

        this.renderedComponent = renderedComponent;
        this.addComponent(renderedComponent);
    }

}