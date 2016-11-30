package com.clipsub.csmanga.utils;


import android.database.Cursor;

import com.clipsub.csmanga.models.Chapter;
import com.clipsub.csmanga.models.DownloadChapter;
import com.clipsub.csmanga.models.DownloadManga;
import com.clipsub.csmanga.models.DownloadPage;
import com.clipsub.csmanga.models.FavoriteManga;
import com.clipsub.csmanga.models.Manga;
import com.clipsub.csmanga.models.RecentChapter;

import java.util.List;

import nl.qbusict.cupboard.Cupboard;
import nl.qbusict.cupboard.CupboardBuilder;

public class DatabaseUtils {
    public static final String TAG = DatabaseUtils.class.getSimpleName();
    public static final int BUFFER_SIZE = 50;

    /**
     * Cupboard instance, can be replaced by static nl.qbusict.cupboard.CupboardFactory.cupboard.
     */
    private static Cupboard sInstance;

    private DatabaseUtils() {
        throw new AssertionError(TAG + ": Cannot be initialized");
    }

    public static Cupboard constructCupboard() {
        if (sInstance == null) {
            sInstance = new CupboardBuilder().build();
            sInstance.register(Chapter.class);
            sInstance.register(DownloadManga.class);
            sInstance.register(DownloadChapter.class);
            sInstance.register(DownloadPage.class);
            sInstance.register(FavoriteManga.class);
            sInstance.register(Manga.class);
            sInstance.register(RecentChapter.class);
        }

        return sInstance;
    }

    /**
     * Get the first entity from the database cursor.
     *
     * @param objectCursor The input cursor.
     * @param classType    Class type of the object.
     * @param <T>          Specified generic type.
     * @return The object or null if the cursor has no result.
     */
    public static <T> T toObject(Cursor objectCursor, Class<T> classType) {
        return constructCupboard().withCursor(objectCursor).get(classType);
    }

    /**
     * Get the result of the cursor as a list.
     *
     * @param listCursor The input cursor.
     * @param classType  Class type of the object.
     * @param <T>        Specified generic type.
     * @return The list of entities obtained from the cursor.
     */
    public static <T> List<T> toList(Cursor listCursor, Class<T> classType) {
        return constructCupboard().withCursor(listCursor).list(classType);
    }
}
