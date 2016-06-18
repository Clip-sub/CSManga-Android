package com.clipsub.csmanga.utils.events;


public class LatestMangaPositionEvent {
    public static final String TAG = LatestMangaPositionEvent.class.getSimpleName();

    private final int mPosition;

    public LatestMangaPositionEvent(int mPosition) {
        this.mPosition = mPosition;
    }

    public int getPosition() {
        return mPosition;
    }
}
