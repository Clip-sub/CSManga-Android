package com.clipsub.csmanga.utils.wrappers;

/**
 * Wrapper for navigation.
 */
public class NavigationWrapper {
    private int mIconResource;
    private int mTitleResource;

    public NavigationWrapper(int iconResource, int titleResource) {
        this.mIconResource = iconResource;
        this.mTitleResource = titleResource;
    }

    public int getIconResource() {
        return mIconResource;
    }

    public int getTitleResource() {
        return mTitleResource;
    }
}
