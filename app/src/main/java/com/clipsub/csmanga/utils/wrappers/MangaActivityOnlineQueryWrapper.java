package com.clipsub.csmanga.utils.wrappers;


import com.clipsub.csmanga.models.Chapter;
import com.clipsub.csmanga.models.Manga;

import java.util.List;

public class MangaActivityOnlineQueryWrapper {
    public static final String TAG = MangaActivityOnlineQueryWrapper.class.getSimpleName();

    private final Manga manga;
    private final List<Chapter> mChapters;
    private final List<String> mRecentChapterNames;
    private final List<String> mDownloadChapterNames;

    public MangaActivityOnlineQueryWrapper(Manga manga,
                                           List<Chapter> mChapters,
                                           List<String> mRecentChapterNames,
                                           List<String> mDownloadChapterNames) {
        this.manga = manga;
        this.mChapters = mChapters;
        this.mRecentChapterNames = mRecentChapterNames;
        this.mDownloadChapterNames = mDownloadChapterNames;
    }

    public Manga getManga() {
        return manga;
    }

    public List<Chapter> getChapters() {
        return mChapters;
    }

    public List<String> getDownloadChapterNames() {
        return mDownloadChapterNames;
    }

    public List<String> getRecentChapterNames() {
        return mRecentChapterNames;
    }
}
