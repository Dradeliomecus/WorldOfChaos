package com.menu.options.tabs.video;

import com.menu.options.tabs.content.TabsContent;

import java.util.LinkedHashMap;

public class VideoTabsContent extends TabsContent {

    /**
     * VideoTabsContent's total height.
     */
    final public static float TOTAL_HEIGHT = TabsContent.HEIGHT + 0.0f;

    /**
     * Creates a new VideoTabsContent instance.
     */
    public VideoTabsContent() {
        super("Video", VideoTabsContent.TOTAL_HEIGHT);

        final LinkedHashMap<String, String[]> content = new LinkedHashMap<>();
        content.put("Display", new String[] {">resolution", "|luminosity", "|maxFPS"});
        content.put("Language", new String[] {"*generalSubtitles", "*cutsceneSubtitles", ">keyboard"});

        this.addContent(content);
    }

}