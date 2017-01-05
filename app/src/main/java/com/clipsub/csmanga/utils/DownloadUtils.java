package com.clipsub.csmanga.utils;

/**
 * Values and utils for downloading operations.
 */
public class DownloadUtils {

    public static final String TAG = DownloadUtils.class.getSimpleName();

    public static final long TIMEOUT = 100;
    public static final int FLAG_FAILED = -200;
    public static final int FLAG_PAUSED = -100;
    public static final int FLAG_PENDING = 0;
    public static final int FLAG_RUNNING = 100;
    public static final int FLAG_COMPLETED = 200;
    public static final int FLAG_CANCELED = 1337;

    /**
     * Private constructor.
     */
    private DownloadUtils() {
        throw new AssertionError(TAG + ": Cannot be initialized.");
    }
}
