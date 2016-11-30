package com.clipsub.csmanga.data.factories;

import android.preference.PreferenceManager;

import com.clipsub.csmanga.data.networks.NetworkService;
import com.clipsub.csmanga.sources.Source;

// TODO: Complete.
public class SourceFactoryImpl implements SourceFactory {

    public static final String TAG = SourceFactoryImpl.class.getSimpleName();

    private PreferenceManager mPreferenceManager;
    private NetworkService mNetworkService;

    @Override
    public Source constructSourceFromName(String sourceName) {
        return null;
    }

    @Override
    public Source constructSourceFromPreferences() {
        return null;
    }

    @Override
    public Source constructSourceFromUrl(String url) {
        return null;
    }
}
