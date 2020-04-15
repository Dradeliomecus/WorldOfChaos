package engine.game.objects.button.selector;

import engine.game.objects.button.Button;
import engine.game.objects.button.ButtonStyle;
import org.jetbrains.annotations.NotNull;

public class RightSelector extends Button {

    /**
     * Parent selector.
     */
    final @NotNull Selector parent;

    /**
     * Creates a new RightSelector instance.
     *
     * @param name RightSelector's name
     * @param style RightSelector's style
     * @param selector Parent selector
     */
    public RightSelector(final @NotNull String name, final @NotNull ButtonStyle style, final @NotNull Selector selector) {
        super("Right selector " + name, style);

        this.parent = selector;
        this.setRenderOutsideParent(true);
    }

    @Override
    public void onClick() {
        super.onClick();
        this.getParent().setIndex(this.getParent().getIndex() + 1);
    }

    /**
     * Returns the parent selector.
     *
     * @return RightSelector.selector
     */
    protected @NotNull Selector getParent() {
        return this.parent;
    }

}