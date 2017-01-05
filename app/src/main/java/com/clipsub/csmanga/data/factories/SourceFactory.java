package com.clipsub.csmanga.data.factories;

import com.clipsub.csmanga.sources.Source;

public interface SourceFactory {

    public Source constructSourceFromPreferences();

    public Source constructSourceFromName(String sourceName);

    public Source constructSourceFromUrl(String url);
}
