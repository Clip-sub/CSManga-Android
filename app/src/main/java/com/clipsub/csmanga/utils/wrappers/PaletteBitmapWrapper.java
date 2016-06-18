package com.clipsub.csmanga.utils.wrappers;

import android.graphics.Bitmap;
import android.support.v7.graphics.Palette;

public class PaletteBitmapWrapper {
    public static final String TAG = PaletteBitmapWrapper.class.getSimpleName();

    private final Palette mPalette;
    private final Bitmap mBitmap;

    public PaletteBitmapWrapper(Bitmap bitmap, Palette palette) {
        this.mBitmap = bitmap;
        this.mPalette = palette;
    }

    public Bitmap getBitmap() {
        return mBitmap;
    }

    public Palette getPalette() {
        return mPalette;
    }
}
