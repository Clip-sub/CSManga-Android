package com.clipsub.csmanga.model.cache;

import com.clipsub.csmanga.CSMangaApplication;
import com.clipsub.csmanga.util.DiskUtils;
import com.jakewharton.disklrucache.DiskLruCache;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

/**
 * Cache provider and helper using Jake's LRUCache.
 */
public class CacheProvider {

    /**
     * Class name for logging.
     */
    private static final String TAG = CacheProvider.class.getSimpleName();

    private static final String PARAMETER_CACHE_DIRECTORY = "chapter_disk_cache";
    private static final int PARAMETER_APP_VERSION = 1;
    private static final int PARAMETER_VALUE_COUNT = 1;
    private static final long PARAMETER_CACHE_SIZE = 10 * 1024 * 1024; // In bytes. This is 10 KB.

    private static CacheProvider sInstance;

    /**
     * Instance of DiskLRUCache;
     */
    private DiskLruCache diskCache;

    /**
     * Private constructor.
     */
    private CacheProvider() {
        try {
            diskCache = DiskLruCache.open(new File(CSMangaApplication.getInstance().getCacheDir(), PARAMETER_CACHE_DIRECTORY),
                    PARAMETER_APP_VERSION,
                    PARAMETER_VALUE_COUNT,
                    PARAMETER_CACHE_SIZE);
        } catch (IOException e) {
            // Log.e(TAG, "IOException occurred");
        }
    }

    /**
     * GetInstance method, return an instance of CacheProvider itself.
     *
     * @return An instance of CacheProvider.
     */
    public static CacheProvider getsInstance() {
        if (sInstance == null) {
            sInstance = new CacheProvider();
        }

        return sInstance;
    }

    /**
     * Get the image urls from disk cache.
     *
     * @param chapterUrl Chapter url to be compared.
     * @return Array of String represents image urls of a chapter.
     * @throws IOException Thrown when there're errors.
     */
    public synchronized String[] getImageUrlsFromDiskCache(String chapterUrl) throws IOException {
        // Initialize snapshot of DiskCache.
        DiskLruCache.Snapshot snapshot = null;

        try {
            String key = DiskUtils.hashKeyForDisk(chapterUrl);
            snapshot = diskCache.get(key);
            String joinedImageUrls = snapshot.getString(0);

            return joinedImageUrls.split(",");
        } finally {
            if (snapshot != null) {
                snapshot.close();
            }
        }
    }

    public synchronized void putImageUrlsToDiskCache(String chapterUrl, List<String> imageUrls) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int index = 0; index < imageUrls.size(); index++) {
            if (index == 0) {
                stringBuilder.append(imageUrls.get(index));
            } else {
                stringBuilder.append(",");
                stringBuilder.append(imageUrls.get(index));
            }
        }

        String cacheValue = stringBuilder.toString();
        DiskLruCache.Editor editor = null;
        OutputStream outputStream = null;

        try {
            String key = DiskUtils.hashKeyForDisk(chapterUrl);
            editor = diskCache.edit(key);
            if (editor == null) {
                return;
            }

            outputStream = new BufferedOutputStream(editor.newOutputStream(0));
            outputStream.write(cacheValue.getBytes());
            outputStream.flush();

            diskCache.flush();
            editor.commit();

        } catch (IOException e) {
            if (editor != null) {
                try {
                    editor.abort();
                } catch (IOException ex) {
                    // Do nothing.
                }
            }
        } finally {
            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (IOException ex) {
                    // Do nothing.
                }
            }
        }
    }

    /**
     * Get the cache directory.
     *
     * @return Cache directory, represent as a File object.
     */
    public synchronized File getCacheDir() {
        return diskCache.getDirectory();
    }
}
