package com.menu.options.tabs.content;

import com.menu.options.tabs.content.categoryHeader.TabsCategoryHeader;
import com.menu.options.tabs.content.scrollbar.TabsScroll;
import com.menu.options.tabs.content.scrollbar.TabsScrollbar;
import com.menu.options.tabs.content.slot.TabsContentSlot;
import com.menu.options.tabs.content.slot.checkBox.TabsContentSlotCheckBox;
import com.menu.options.tabs.content.slot.key.TabsContentSlotKey;
import com.menu.options.tabs.content.slot.selector.TabsContentSlotSelector;
import com.menu.options.tabs.content.slot.slideBar.TabsContentSlotSlideBar;
import engine.game.components.RenderedComponent;
import engine.game.objects.scrollbar.GameObjectScrollable;
import engine.math.Vector2f;
import engine.rendering.texture.Material;
import engine.rendering.texture.Texture;
import engine.util.Color;
import engine.util.Window;
import org.jetbrains.annotations.NotNull;

import java.util.LinkedHashMap;

abstract public class TabsContent extends GameObjectScrollable {

    /**
     * TabsContent's x position.
     */
    final public static float X_POS = 0.012931f * Window.getRatio();

    /**
     * TabsContent's y position.
     */
    final public static float Y_POS = 0.0234375f;

    /**
     * TabsContent's width.
     */
    final public static float WIDTH = 1.375f * Window.getRatio();

    /**
     * TabsContent's height.
     */
    final public static float HEIGHT = 0.921875f;

    /**
     * Creates a new TabsContent instance.
     *
     * @param name TabsContent's name
     * @param totalHeight TabsContent's total height
     */
    public TabsContent(final String name, final float totalHeight) {
        super(name + "'s Tabs Content", TabsContent.WIDTH, TabsContent.HEIGHT, 0, totalHeight);

        this.setPosition(new Vector2f(TabsContent.X_POS, TabsContent.Y_POS));
        this.setDepth(0.1f);

        this.setScrollbar(new TabsScrollbar(this, new TabsScroll(name, TabsContent.HEIGHT / totalHeight, totalHeight - TabsContent.HEIGHT)));
        this.addComponent(new RenderedComponent(new Material(new Texture("blank1x1"), new Color(29, 29, 29)), TabsContent.WIDTH, TabsContent.HEIGHT));
    }

    /**
     * Adds all the content at the correct position.
     *
     * @param content Content to add
     */
    final protected void addContent(final @NotNull LinkedHashMap<String, String[]> content) {
        final float xPos1 = 0.05172414f * Window.getRatio();
        final float xPos2 = 0.72844828f * Window.getRatio();
        float yPos = 0.828125f;

        final float deltaHeightBetweenCategoryAndSlot = 0.015625f;
        final float deltaHeightBetweenSlotAndCategory = 0.0234375f;
        final float deltaHeightBetweenTwoSlots = 0.0078125f;

        int i = 0;

        for(final String category : content.keySet()) {
            this.addChild(new TabsCategoryHeader(category, yPos));

            final float originalYPos = yPos - deltaHeightBetweenTwoSlots;
            yPos -= TabsContentSlot.HEIGHT + deltaHeightBetweenCategoryAndSlot;

            for(final String slot : content.get(category)) {
                final char token = slot.charAt(0);
                final String slotName = slot.substring(1);
                final TabsContentSlot contentSlot;
                final String slotLabel = TabsContent.indexToLabel(slotName);
                final Vector2f slotPos = new Vector2f(i % 2 == 0 ? xPos1 : xPos2, yPos);

                if(token == '/') {
                    contentSlot = new TabsContentSlotKey(slotLabel, slotName, slotPos, this);
                } else if(token == '*') {
                    contentSlot = new TabsContentSlotCheckBox(slotLabel, slotName, slotPos, this);
                } else if(token == '>') {
                    contentSlot = new TabsContentSlotSelector(slotLabel, slotName, slotPos, this);
                } else if(token == '|') {
                    contentSlot = new TabsContentSlotSlideBar(slotLabel, slotName, slotPos, this);
                } else {
                    contentSlot = null;
                    System.err.println("Error: the token '" + token + "' isn't recognized.");
                    new Exception().printStackTrace();
                    System.exit(1);
                }

                this.addChild(contentSlot);
                i++;

                if(i % 2 == 0) {
                    yPos -= TabsContentSlot.HEIGHT + deltaHeightBetweenTwoSlots;
                }
            }

            if(i % 2 == 1) {
                i++;
                yPos -= TabsCategoryHeader.HEIGHT + deltaHeightBetweenSlotAndCategory;
            } else if(i % 2 == 0) {
                yPos += TabsContentSlot.HEIGHT + deltaHeightBetweenTwoSlots - TabsCategoryHeader.HEIGHT - deltaHeightBetweenSlotAndCategory;
            }

            // Create the separators (multiple because otherwise it's seen under the option window).
            float separatorHeight = originalYPos - yPos - TabsCategoryHeader.HEIGHT;
            final float separatorYPos = originalYPos - separatorHeight;

            int j = 0;
            while(separatorHeight > 0) {
                if(separatorHeight > TabsSeparator.MAX_HEIGHT) {
                    this.addChild(new TabsSeparator(TabsSeparator.MAX_HEIGHT, separatorYPos + separatorHeight - TabsSeparator.MAX_HEIGHT, j == 0 ? TabsSeparator.STATUS_TOP : TabsSeparator.STATUS_NONE));
                    separatorHeight -= TabsSeparator.MAX_HEIGHT;
                    j++;
                } else {
                    this.addChild(new TabsSeparator(separatorHeight, separatorYPos, j == 0 ? TabsSeparator.STATUS_TOP_BOTTOM : TabsSeparator.STATUS_BOTTOM));
                    break;
                }
            }
        }
    }

    /**
     * Returns the label from a index (ex: "moveWest" ==> "Move west :").
     *
     * @param index Index to use
     * @return new String
     */
    private static @NotNull String indexToLabel(final @NotNull String index) {
        String label = ("" + index.charAt(0)).toUpperCase();

        for(int i = 1; i < index.length(); i++) {
            final String previousCharacter = "" + index.charAt(i - 1);
            final String character = "" + index.charAt(i);
            final boolean previousIsCaps = !previousCharacter.equals(previousCharacter.toLowerCase());
            final boolean isCaps = !character.equals(character.toLowerCase());
            final boolean previousIsNumber = index.charAt(i - 1) >= 48 && index.charAt(i - 1) <= 57;
            final boolean isNumber = index.charAt(i) >= 48 && index.charAt(i) <= 57;

            if((isCaps && !previousIsCaps) || (isNumber && !previousIsNumber)) {
                label += " " + character.toLowerCase();
            } else {
                label += character;
            }
        }

        return label + " :";
    }

}