package com.clipsub.csmanga.utils;

/**
 * Utils class for app's preferences.
 */
public class PreferenceUtils {
    public static final String TAG = PreferenceUtils.class.getSimpleName();

    public static final String CATALOGUE_VIEW_TYPE_KEY = "CatalogueViewTypeKey";
    public static final String SORT_CHAPTER_ASCENDING_KEY = "SortChapterAscendingKey";

    private PreferenceUtils() {
        throw new AssertionError(TAG + ": Cannot be initialized.");
    }
}
