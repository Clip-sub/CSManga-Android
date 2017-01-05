package com.clipsub.csmanga.data.databases;

import android.content.ContentValues;
import android.database.Cursor;

import com.clipsub.csmanga.models.Chapter;
import com.clipsub.csmanga.models.DownloadChapter;
import com.clipsub.csmanga.models.DownloadManga;
import com.clipsub.csmanga.models.DownloadPage;
import com.clipsub.csmanga.models.FavoriteManga;
import com.clipsub.csmanga.models.Manga;
import com.clipsub.csmanga.models.RecentChapter;

import rx.Observable;

/**
 * Interface for database query management.
 */
public interface QueryManager {

    // Raw Query:
    public Observable<Cursor> rawApplicationQuery(String sqlStatement, String[] selectionArgs);
    public Observable<Cursor> rawLibraryQuery(String sqlStatement, String[] selectionArgs);

    // Create Models:
    public Observable<Long> createChapter(Chapter chapter);
    public Observable<Long> createDownloadChapter(DownloadChapter downloadChapter);
    public Observable<Long> createDownloadManga(DownloadManga downloadManga);
    public Observable<Long> createDownloadPage(DownloadPage downloadPage);
    public Observable<Long> createFavoriteManga(FavoriteManga favoriteManga);
    public Observable<Long> createManga(Manga manga);
    public Observable<Long> createRecentChapter(RecentChapter recentChapter);

    // Retrieve Models:
    public Observable<Chapter> retrieveChapter(String source, String url);
    public Observable<DownloadChapter> retrieveDownloadChapter(String source, String url);
    public Observable<DownloadManga> retrieveDownloadManga(String source, String name);
    public Observable<DownloadPage> retrieveDownloadPage(String url);
    public Observable<FavoriteManga> retrieveFavoriteManga(String source, String name);
    public Observable<Manga> retrieveManga(String source, String name);
    public Observable<RecentChapter> retrieveRecentChapter(String source, String url, boolean isOnline);

    // Retrieve Models as Cursors:
    public Observable<Cursor> retrieveChapterAsCursor(String[] columns, String selection, String[] selectionArgs, String groupBy, String having, String orderBy, String limit);
    public Observable<Cursor> retrieveDownloadChapterAsCursor(String[] columns, String selection, String[] selectionArgs, String groupBy, String having, String orderBy, String limit);
    public Observable<Cursor> retrieveDownloadMangaAsCursor(String[] columns, String selection, String[] selectionArgs, String groupBy, String having, String orderBy, String limit);
    public Observable<Cursor> retrieveDownloadPageAsCursor(String[] columns, String selection, String[] selectionArgs, String groupBy, String having, String orderBy, String limit);
    public Observable<Cursor> retrieveFavoriteMangaAsCursor(String[] columns, String selection, String[] selectionArgs, String groupBy, String having, String orderBy, String limit);
    public Observable<Cursor> retrieveMangaAsCursor(String[] columns, String selection, String[] selectionArgs, String groupBy, String having, String orderBy, String limit);
    public Observable<Cursor> retrieveRecentChapterAsCursor(String[] columns, String selection, String[] selectionArgs, String groupBy, String having, String orderBy, String limit);

    // Retrieve All Models as Stream:
    public Observable<Chapter> retrieveAllChapterAsStream(String[] columns, String selection, String[] selectionArgs, String groupBy, String having, String orderBy, String limit);
    public Observable<DownloadChapter> retrieveAllDownloadChapterAsStream(String[] columns, String selection, String[] selectionArgs, String groupBy, String having, String orderBy, String limit);
    public Observable<DownloadManga> retrieveAllDownloadMangaAsStream(String[] columns, String selection, String[] selectionArgs, String groupBy, String having, String orderBy, String limit);
    public Observable<DownloadPage> retrieveAllDownloadPageAsStream(String[] columns, String selection, String[] selectionArgs, String groupBy, String having, String orderBy, String limit);
    public Observable<FavoriteManga> retrieveAllFavoriteMangaAsStream(String[] columns, String selection, String[] selectionArgs, String groupBy, String having, String orderBy, String limit);
    public Observable<Manga> retrieveAllMangaAsStream(String[] columns, String selection, String[] selectionArgs, String groupBy, String having, String orderBy, String limit);
    public Observable<RecentChapter> retrieveAllRecentChapterAsStream(String[] columns, String selection, String[] selectionArgs, String groupBy, String having, String orderBy, String limit);

    // Update Models:
    public Observable<Integer> updateChapter(ContentValues updateValues, String selection, String[] selectionArgs);
    public Observable<Integer> updateDownloadChapter(ContentValues updateValues, String selection, String[] selectionArgs);
    public Observable<Integer> updateDownloadManga(ContentValues updateValues, String selection, String[] selectionArgs);
    public Observable<Integer> updateDownloadPage(ContentValues updateValues, String selection, String[] selectionArgs);
    public Observable<Integer> updateFavoriteManga(ContentValues updateValues, String selection, String[] selectionArgs);
    public Observable<Integer> updateManga(ContentValues updateValues, String selection, String[] selectionArgs);
    public Observable<Integer> updateRecentChapter(ContentValues updateValues, String selection, String[] selectionArgs);

    // Update All Models:
    public Observable<Integer> updateAllChapter(ContentValues updateValues);
    public Observable<Integer> updateAllDownloadChapter(ContentValues updateValues);
    public Observable<Integer> updateAllDownloadManga(ContentValues updateValues);
    public Observable<Integer> updateAllDownloadPage(ContentValues updateValues);
    public Observable<Integer> updateAllFavoriteManga(ContentValues updateValues);
    public Observable<Integer> updateAllManga(ContentValues updateValues);
    public Observable<Integer> updateAllRecentChapter(ContentValues updateValues);

    // Delete Models:
    public Observable<Boolean> deleteChapter(Chapter chapter);
    public Observable<Boolean> deleteDownloadChapter(DownloadChapter downloadChapter);
    public Observable<Boolean> deleteDownloadManga(DownloadManga downloadManga);
    public Observable<Boolean> deleteDownloadPage(DownloadPage downloadPage);
    public Observable<Boolean> deleteFavoriteManga(FavoriteManga favoriteManga);
    public Observable<Boolean> deleteManga(Manga manga);
    public Observable<Boolean> deleteRecentChapter(RecentChapter recentChapter);

    // Delete all models:
    public Observable<Integer> deleteAllChapter(String selection, String[] selectionArgs);
    public Observable<Integer> deleteAllDownloadChapter(String selection, String[] selectionArgs);
    public Observable<Integer> deleteAllDownloadManga(String selection, String[] selectionArgs);
    public Observable<Integer> deleteAllDownloadPage(String selection, String[] selectionArgs);
    public Observable<Integer> deleteAllFavoriteManga(String selection, String[] selectionArgs);
    public Observable<Integer> deleteAllManga(String selection, String[] selectionArgs);
    public Observable<Integer> deleteAllRecentChapter(String selection, String[] selectionArgs);

    // Transaction method contracts:
    public void beginApplicationTransaction();
    public void endApplicationTransaction();
    public void setApplicationTransactionSuccessful();
    public void beginLibraryTransaction();
    public void endLibraryTransaction();
    public void setLibraryTransactionSuccessful();
}
