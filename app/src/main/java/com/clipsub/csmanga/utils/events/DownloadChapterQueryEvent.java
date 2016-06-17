package com.clipsub.csmanga.utils.events;

public class DownloadChapterQueryEvent {
    public static final String TAG = DownloadChapterQueryEvent.class.getSimpleName();

    private final String mSource;
    private final String mUrl;
    private final String mParentUrl;
    private final String mParentName;
    private final String mName;

    public DownloadChapterQueryEvent(String mName, String mSource, String mUrl, String mParentUrl, String mParentName) {
        this.mName = mName;
        this.mSource = mSource;
        this.mUrl = mUrl;
        this.mParentUrl = mParentUrl;
        this.mParentName = mParentName;
    }

    public String getName() {
        return mName;
    }

    public String getParentName() {
        return mParentName;
    }

    public String getParentUrl() {
        return mParentUrl;
    }

    public String getSource() {
        return mSource;
    }

    public String getUrl() {
        return mUrl;
    }
}
