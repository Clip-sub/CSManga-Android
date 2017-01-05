package com.clipsub.csmanga.modules;

import android.app.Application;

import com.clipsub.csmanga.BuildConfig;
import com.facebook.stetho.okhttp.StethoInterceptor;
import com.squareup.okhttp.Cache;
import com.squareup.okhttp.OkHttpClient;

import java.io.File;


public class DataModule {

    public static final String TAG = DataModule.class.getSimpleName();

    public static final int DISK_CACHE_SIZE = 50 * 1024 * 1024;

    public OkHttpClient provideOkHttpClient(Application application) {
        return createOkHttpClient(application);
    }

    public static OkHttpClient createOkHttpClient(Application application) {
        final OkHttpClient temporaryClient = new OkHttpClient();

        File cacheDirectory = application.getCacheDir();
        Cache cache = new Cache(cacheDirectory, DISK_CACHE_SIZE);
        temporaryClient.setCache(cache);

        if (BuildConfig.DEBUG) {
            temporaryClient.networkInterceptors().add(new StethoInterceptor());
        }

        return temporaryClient;
    }
}
