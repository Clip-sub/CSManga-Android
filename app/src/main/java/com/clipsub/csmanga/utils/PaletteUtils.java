package com.clipsub.csmanga.utils;

import android.support.v7.graphics.Palette;

public class PaletteUtils {
    public static final String TAG = PaletteUtils.class.getSimpleName();

    /**
     * Private constructor for error tracking.
     */
    private PaletteUtils() {
        throw new AssertionError(TAG + ": Cannot be initialized.");
    }

    public static int getColorWithDefault(Palette palette, int defaultColor) {
        Palette.Swatch currentItem = null;

        if (currentItem == null) {
            currentItem = palette.getVibrantSwatch();
        }
        if (currentItem == null) {
            currentItem = palette.getDarkVibrantSwatch();
            ;
        }
        if (currentItem == null) {
            currentItem = palette.getLightVibrantSwatch();
        }
        if (currentItem == null) {
            currentItem = palette.getMutedSwatch();
        }
        if (currentItem == null) {
            currentItem = palette.getDarkMutedSwatch();
        }
        if (currentItem == null) {
            currentItem = palette.getLightMutedSwatch();
        }

        return currentItem != null ? currentItem.getRgb() : defaultColor;
    }
}
