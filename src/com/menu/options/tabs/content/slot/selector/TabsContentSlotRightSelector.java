package com.menu.options.tabs.content.slot.selector;

import com.GameOptions;
import engine.game.components.RenderedComponent;
import engine.game.objects.button.ButtonStyle;
import engine.game.objects.button.selector.RightSelector;
import engine.math.Vector2f;
import engine.rendering.Mesh;
import engine.rendering.Vertex;
import engine.util.Window;

class TabsContentSlotRightSelector extends RightSelector {

    /**
     * TabsContentSlotRightSelector's x position.
     */
    final public static float X_POS = 0.27586206896552f * Window.getRatio();

    /**
     * Creates a new TabsContentSlotRightSelector instance.
     *
     * @param selector Selector parent
     */
    TabsContentSlotRightSelector(final TabsContentSlotSelectorObject selector) {
        super("Menu Right Selector", TabsContentSlotLeftSelector.BUTTON_STYLE, selector);

        this.setPosition(new Vector2f(TabsContentSlotRightSelector.X_POS, 0.0f));
        this.setDepth(-0.01f);

        this.setOverrideGlobalActivation((byte) 10);

        final ButtonStyle buttonStyle = TabsContentSlotLeftSelector.BUTTON_STYLE;

        final Mesh mesh = new Mesh();
        final Vertex[] vertices = new Vertex[] {
            new Vertex(new Vector2f(0, 0), new Vector2f(1, 0)),
            new Vertex(new Vector2f(0, buttonStyle.getHeight()), new Vector2f(1, 1)),
            new Vertex(new Vector2f(buttonStyle.getWidth(), buttonStyle.getHeight()), new Vector2f(0, 1)),
            new Vertex(new Vector2f(buttonStyle.getWidth(), 0), new Vector2f(0, 0))
        };
        mesh.setVertices(vertices);

        final RenderedComponent renderedComponent = new RenderedComponent(mesh, buttonStyle.getBasicMaterial(), buttonStyle.getWidth(), buttonStyle.getHeight());
        this.setRenderedComponent(renderedComponent);
    }

    @Override
    protected TabsContentSlotSelectorObject getParent() {
        return (TabsContentSlotSelectorObject) super.getParent();
    }

    @Override
    public void onClick() {
        super.onClick();

        GameOptions.getSelect(this.getParent().getOptionsIndex()).setIndex(this.getParent().getIndex());
    }

}