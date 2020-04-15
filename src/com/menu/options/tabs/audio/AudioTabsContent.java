package com.menu.options.tabs.audio;

import com.menu.options.tabs.content.TabsContent;

import java.util.LinkedHashMap;

public class AudioTabsContent extends TabsContent {

    /**
     * Creates a new AudioTabsContent instance.
     */
    public AudioTabsContent() {
        super("Audio", TabsContent.HEIGHT);

        final LinkedHashMap<String, String[]> content = new LinkedHashMap<>();
	    content.put("Audio", new String[] {"|generalVolume"}); // TODO: Fill this.

	    this.addContent(content);
    }

}