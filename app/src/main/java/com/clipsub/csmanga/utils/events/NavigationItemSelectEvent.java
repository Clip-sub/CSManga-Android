package com.clipsub.csmanga.utils.events;


public class NavigationItemSelectEvent {
    public static final String TAG = NavigationItemSelectEvent.class.getSimpleName();
    private final int mSelectedPosition;

    public NavigationItemSelectEvent(int mSelectedPosition) {
        this.mSelectedPosition = mSelectedPosition;
    }

    public int getSelectedPosition() {
        return mSelectedPosition;
    }
}
