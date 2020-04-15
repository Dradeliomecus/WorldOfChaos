package com.menu.options.tabs.controls;

import com.menu.options.tabs.content.TabsContent;

import java.util.LinkedHashMap;

public class ControlsTabsContent extends TabsContent {

    /**
     * ControlsTabsContent's total height.
     */
    final public static float TOTAL_HEIGHT = TabsContent.HEIGHT + 1.1f;

    /**
     * Creates a new ControlsTabsContent instance.
     */
    public ControlsTabsContent() {
        super("Controls", ControlsTabsContent.TOTAL_HEIGHT);

        final LinkedHashMap<String, String[]> content = new LinkedHashMap<>();
        content.put("Movement", new String[] {"/moveNorth", "/moveSouth", "/moveWest", "/moveEast", "/sprint", "*doubleKeyToSprint"});
        content.put("Actions", new String[] {"/action1", "/action2", "/spell1", "/spell2", "/spell3", "/spell4", "/spell5", "/spell6", "/spell7", "/spell8", "/spell9", "/spell10", "/selectTarget"});
        content.put("Miscellaneous", new String[] {"/inventory", "/map", "/questBook", "/characteristics", "/openConsole", "/menu", "/professions", "/bestiary"});

        this.addContent(content);
    }

}