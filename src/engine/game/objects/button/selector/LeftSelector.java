package engine.game.objects.button.selector;

import engine.game.objects.button.Button;
import engine.game.objects.button.ButtonStyle;
import org.jetbrains.annotations.NotNull;

public class LeftSelector extends Button {

    /**
     * Parent selector.
     */
    final private @NotNull Selector parent;

    /**
     * Creates a new LeftSelector instance.
     *
     * @param name LeftSelector's name
     * @param style LeftSelector's style
     * @param selector Parent selector
     */
    public LeftSelector(final @NotNull String name, final @NotNull ButtonStyle style, final @NotNull Selector selector) {
        super("Left selector " + name, style);

        this.parent = selector;
        this.setRenderOutsideParent(true);
    }

    @Override
    public void onClick() {
        super.onClick();
        this.getParent().setIndex(this.getParent().getIndex() - 1);
    }

    /**
     * Returns the parent selector.
     *
     * @return LeftSelector.selector
     */
    protected @NotNull Selector getParent() {
        return this.parent;
    }

}