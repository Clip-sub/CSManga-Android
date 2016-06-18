package com.clipsub.csmanga.modules;


import android.app.Application;

import com.clipsub.csmanga.CSMangaApplication;

import javax.inject.Singleton;

import dagger.Provides;

public class CSMangaModule {
    public static final String TAG = CSMangaModule.class.getSimpleName();

    private final CSMangaApplication mApplication;

    public CSMangaModule(CSMangaApplication mApplication) {
        this.mApplication = mApplication;
    }

    @Provides
    @Singleton
    public Application provideApplication() {
        return mApplication;
    }
}
