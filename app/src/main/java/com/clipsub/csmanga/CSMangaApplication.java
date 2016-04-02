package com.clipsub.csmanga;

import android.app.Application;

/**
 * Main Application entry point.
 * // TODO: Complete it.
 */
public class CSMangaApplication extends Application {
    static {
        // cupboard().register(Manga.class);


    }

    /**
     * An instance of the app.
     */
    private static CSMangaApplication appInstance;

    public CSMangaApplication() {
        appInstance = this;
    }

    /**
     * Create a static and thread-safe, synchronized instance of the app.
     *
     * @return The app itself.
     */
    public static synchronized CSMangaApplication getInstance() {
        return appInstance;
    }
}
