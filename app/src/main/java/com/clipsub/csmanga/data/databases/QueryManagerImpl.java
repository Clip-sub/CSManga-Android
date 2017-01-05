<<<<<<< HEAD
package com.clipsub.csmanga.data.databases;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.clipsub.csmanga.models.Chapter;
import com.clipsub.csmanga.models.DownloadChapter;
import com.clipsub.csmanga.models.DownloadManga;
import com.clipsub.csmanga.models.DownloadPage;
import com.clipsub.csmanga.models.FavoriteManga;
import com.clipsub.csmanga.models.Manga;
import com.clipsub.csmanga.models.RecentChapter;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import nl.qbusict.cupboard.Cupboard;
import nl.qbusict.cupboard.CupboardBuilder;
import rx.Observable;
import rx.Subscriber;
import rx.functions.Func1;

public class QueryManagerImpl implements QueryManager {
    public static final String TAG = QueryManagerImpl.class.getSimpleName();

    private Cupboard mCupboard;

    /**
     * Application database instance (download queue, etc).
     */
    private SQLiteDatabase mApplicationDatabase;
    private SQLiteDatabase mLibraryDatabase;

    private static final ReentrantReadWriteLock mChapterReadWriteLock = new ReentrantReadWriteLock();
    private static final ReentrantReadWriteLock mDownloadChapterReadWriteLock = new ReentrantReadWriteLock();
    private static final ReentrantReadWriteLock mDownloadMangaReadWriteLock = new ReentrantReadWriteLock();
    private static final ReentrantReadWriteLock mDownloadPageReadWriteLock = new ReentrantReadWriteLock();
    private static final ReentrantReadWriteLock mFavoriteMangaReadWriteLock = new ReentrantReadWriteLock();
    private static final ReentrantReadWriteLock mMangaReadWriteLock = new ReentrantReadWriteLock();
    private static final ReentrantReadWriteLock mRecentChapterReadWriteLock = new ReentrantReadWriteLock();

    public QueryManagerImpl(ApplicationSQLiteOpenHelper applicationSQLiteOpenHelper,
                            LibrarySQLiteOpenHelper librarySQLiteOpenHelper) {

        mApplicationDatabase = applicationSQLiteOpenHelper.getWritableDatabase();
        mApplicationDatabase.enableWriteAheadLogging();
        mLibraryDatabase = librarySQLiteOpenHelper.getWritableDatabase();
        mLibraryDatabase.enableWriteAheadLogging();

        initializeCupboard();
    }

    /**
     * Initialize the Cupboard instance.
     */
    private void initializeCupboard() {
        mCupboard = new CupboardBuilder().build();
        mCupboard.register(Chapter.class);
        mCupboard.register(DownloadManga.class);
        mCupboard.register(DownloadChapter.class);
        mCupboard.register(DownloadPage.class);
        mCupboard.register(FavoriteManga.class);
        mCupboard.register(Manga.class);
        mCupboard.register(RecentChapter.class);
    }

    // TODO: Complete all below.

    @Override
    public Observable<Cursor> rawApplicationQuery(final String sqlStatement, final String[] selectionArgs) {
        return Observable.create(new Observable.OnSubscribe<Cursor>() {
            @Override
            public void call(Subscriber<? super Cursor> subscriber) {
                try {
                    if (!subscriber.isUnsubscribed()) {
                        subscriber.onNext(mApplicationDatabase.rawQuery(sqlStatement, selectionArgs));
                    }

                    subscriber.onCompleted();
                } catch (Throwable e) {
                    subscriber.onError(e);
                }
            }
        });
    }

    @Override
    public Observable<Cursor> rawLibraryQuery(final String sqlStatement, final String[] selectionArgs) {
        return Observable.create(new Observable.OnSubscribe<Cursor>() {
            @Override
            public void call(Subscriber<? super Cursor> subscriber) {
                try {
                    if (!subscriber.isUnsubscribed()) {
                        subscriber.onNext(mLibraryDatabase.rawQuery(sqlStatement, selectionArgs));
                    }
                    subscriber.onCompleted();
                } catch (Throwable e) {
                    subscriber.onError(e);
                }
            }
        });
    }

    @Override
    public Observable<Long> createChapter(final Chapter chapter) {
        return Observable.create(new Observable.OnSubscribe<Long>() {
            @Override
            public void call(Subscriber<? super Long> subscriber) {
                try {
                    subscriber.onNext(mCupboard.withDatabase(mApplicationDatabase).put(chapter));
                    subscriber.onCompleted();
                } catch (Throwable e) {
                    subscriber.onError(e);
                }
            }
        });
    }

    @Override
    public Observable<Long> createDownloadChapter(final DownloadChapter downloadChapter) {
        return Observable.create(new Observable.OnSubscribe<Long>() {
            @Override
            public void call(Subscriber<? super Long> subscriber) {
                try {
                    subscriber.onNext(mCupboard.withDatabase(mApplicationDatabase).put(downloadChapter));
                    subscriber.onCompleted();
                } catch (Throwable e) {
                    subscriber.onError(e);
                }

            }
        });
    }

    @Override
    public Observable<Long> createDownloadManga(final DownloadManga downloadManga) {
        return Observable.create(new Observable.OnSubscribe<Long>() {
            @Override
            public void call(Subscriber<? super Long> subscriber) {
                try {
                    subscriber.onNext(mCupboard.withDatabase(mApplicationDatabase).put(downloadManga));
                    subscriber.onCompleted();
                } catch (Throwable e) {
                    subscriber.onError(e);
                }
            }
        });
    }

    @Override
    public Observable<Long> createDownloadPage(final DownloadPage downloadPage) {
        return Observable.create(new Observable.OnSubscribe<Long>() {
            @Override
            public void call(Subscriber<? super Long> subscriber) {
                try {
                    subscriber.onNext(mCupboard.withDatabase(mApplicationDatabase).put(downloadPage));
                    subscriber.onCompleted();
                } catch (Throwable e) {
                    subscriber.onError(e);
                }
            }
        });
    }

    @Override
    public Observable<Long> createFavoriteManga(final FavoriteManga favoriteManga) {
        return Observable.create(new Observable.OnSubscribe<Long>() {
            @Override
            public void call(Subscriber<? super Long> subscriber) {
                try {
                    subscriber.onNext(mCupboard.withDatabase(mApplicationDatabase).put(favoriteManga));
                    subscriber.onCompleted();
                } catch (Throwable e) {
                    subscriber.onError(e);
                }
            }
        });
    }

    @Override
    public Observable<Long> createManga(final Manga manga) {
        return Observable.create(new Observable.OnSubscribe<Long>() {
            @Override
            public void call(Subscriber<? super Long> subscriber) {
                try {
                    subscriber.onNext(mCupboard.withDatabase(mLibraryDatabase).put(manga));
                    subscriber.onCompleted();
                } catch (Throwable e) {
                    subscriber.onError(e);
                }
            }
        });
    }

    @Override
    public Observable<Long> createRecentChapter(final RecentChapter recentChapter) {
        return Observable.create(new Observable.OnSubscribe<Long>() {
            @Override
            public void call(Subscriber<? super Long> subscriber) {
                try {
                    subscriber.onNext(mCupboard.withDatabase(mApplicationDatabase).put(recentChapter));
                    subscriber.onCompleted();
                } catch (Throwable e) {
                    subscriber.onError(e);
                }
            }
        });
    }

    @Override
    public Observable<Chapter> retrieveChapter(final String source, final String url) {
        return Observable.create(new Observable.OnSubscribe<Chapter>() {
            @Override
            public void call(Subscriber<? super Chapter> subscriber) {
                try {
                    if (!subscriber.isUnsubscribed()) {
                        mChapterReadWriteLock.readLock().lock();

                        StringBuilder selection = new StringBuilder();
                        List<String> selectionArgs = new ArrayList<String>();

                        selection.append(ApplicationContract.Chapter.COLUMN_SOURCE += " = ?");
                        selectionArgs.add(source);
                        selection.append(" AND ").append(ApplicationContract.Chapter.COLUMN_URL + " = ?");
                        selectionArgs.add(url);

                        subscriber.onNext
                                (mCupboard.withDatabase(mApplicationDatabase).query(Chapter.class)
                                        .withSelection(selection.toString(),
                                                selectionArgs.toArray(new String[selectionArgs.size()]))
                                        .limit(1)
                                        .get()
                                );

                        mChapterReadWriteLock.readLock().unlock();
                    }

                    subscriber.onCompleted();
                } catch (Throwable e) {
                    subscriber.onError(e);
                }
            }
        });
    }

    @Override
    public Observable<DownloadChapter> retrieveDownloadChapter(final String source, final String url) {
        return Observable.create(new Observable.OnSubscribe<DownloadChapter>() {
            @Override
            public void call(Subscriber<? super DownloadChapter> subscriber) {
                try {
                    if (!subscriber.isUnsubscribed()) {
                        mDownloadChapterReadWriteLock.readLock().lock();

                        StringBuilder selection = new StringBuilder();
                        List<String> selectionArgs = new ArrayList<String>();

                        selection.append(ApplicationContract.DownloadChapter.COLUMN_SOURCE + " = ?");
                        selectionArgs.add(source);
                        selection.append(" AND ").append(ApplicationContract.DownloadChapter.COLUMN_URL + " = ?");
                        selectionArgs.add(url);

                        subscriber.onNext
                                (mCupboard.withDatabase(mApplicationDatabase).query(DownloadChapter.class)
                                        .withSelection(selection.toString(), selectionArgs.toArray(new String[selectionArgs.size()]))
                                        .limit(1)
                                        .get()
                                );

                        mDownloadChapterReadWriteLock.readLock().unlock();
                    }
                    subscriber.onCompleted();
                } catch (Throwable e) {
                    subscriber.onError(e);
                }
            }
        });
    }

    @Override
    public Observable<DownloadManga> retrieveDownloadManga(final String source, final String name) {
        return Observable.create(new Observable.OnSubscribe<DownloadManga>() {
            @Override
            public void call(Subscriber<? super DownloadManga> subscriber) {
                try {
                    if (!subscriber.isUnsubscribed()) {
                        mDownloadMangaReadWriteLock.readLock().lock();

                        StringBuilder selection = new StringBuilder();
                        List<String> selectionArgs = new ArrayList<String>();

                        selection.append(ApplicationContract.DownloadManga.COLUMN_SOURCE + " = ?");
                        selectionArgs.add(source);
                        selection.append(" AND ").append(ApplicationContract.DownloadManga.COLUMN_NAME + " = ?");
                        selectionArgs.add(name);

                        subscriber.onNext
                                (mCupboard.withDatabase(mApplicationDatabase).query(DownloadManga.class)
                                        .withSelection(selection.toString(), selectionArgs.toArray(new String[selectionArgs.size()]))
                                        .limit(1)
                                        .get()
                                );

                        mDownloadMangaReadWriteLock.readLock().unlock();
                    }
                    subscriber.onCompleted();
                } catch (Throwable e) {
                    subscriber.onError(e);
                }
            }
        });
    }

    @Override
    public Observable<DownloadPage> retrieveDownloadPage(final String url) {
        return Observable.create(new Observable.OnSubscribe<DownloadPage>() {
            @Override
            public void call(Subscriber<? super DownloadPage> subscriber) {
                try {
                    if (!subscriber.isUnsubscribed()) {
                        mDownloadPageReadWriteLock.readLock().lock();

                        StringBuilder selection = new StringBuilder();
                        List<String> selectionArgs = new ArrayList<String>();

                        selection.append(ApplicationContract.DownloadPage.COLUMN_URL + " = ?");
                        selectionArgs.add(url);

                        subscriber.onNext(mCupboard.withDatabase(mApplicationDatabase).query(DownloadPage.class)
                                .withSelection(selection.toString(),
                                        selectionArgs.toArray(new String[selectionArgs.size()]))
                                .limit(1)
                                .get()
                        );

                        mDownloadPageReadWriteLock.readLock().unlock();
                    }

                    subscriber.onCompleted();
                } catch (Throwable e) {
                    subscriber.onError(e);
                }
            }
        });
    }

    @Override
    public Observable<FavoriteManga> retrieveFavoriteManga(final String source, final String name) {
        return Observable.create(new Observable.OnSubscribe<FavoriteManga>() {
            @Override
            public void call(Subscriber<? super FavoriteManga> subscriber) {
                try {
                    if (!subscriber.isUnsubscribed()) {
                        mFavoriteMangaReadWriteLock.readLock().lock();

                        StringBuilder selection = new StringBuilder();
                        List<String> selectionArgs = new ArrayList<String>();

                        selection.append(ApplicationContract.FavoriteManga.COLUMN_SOURCE + " = ?");
                        selectionArgs.add(source);
                        selection.append(" AND ").append(ApplicationContract.FavoriteManga.COLUMN_NAME + " = ?");
                        selectionArgs.add(name);

                        subscriber.onNext
                                (mCupboard.withDatabase(mApplicationDatabase).query(FavoriteManga.class)
                                        .withSelection(selection.toString(), selectionArgs.toArray(new String[selectionArgs.size()]))
                                        .limit(1)
                                        .get()
                                );

                        mFavoriteMangaReadWriteLock.readLock().unlock();
                    }
                    subscriber.onCompleted();
                } catch (Throwable e) {
                    subscriber.onError(e);
                }
            }
        });
    }

    @Override
    public Observable<Manga> retrieveManga(final String source, final String name) {
        return Observable.create(new Observable.OnSubscribe<Manga>() {
            @Override
            public void call(Subscriber<? super Manga> subscriber) {
                try {
                    if (!subscriber.isUnsubscribed()) {
                        mMangaReadWriteLock.readLock().lock();

                        StringBuilder selection = new StringBuilder();
                        List<String> selectionArgs = new ArrayList<String>();

                        selection.append(LibraryContract.Manga.COLUMN_SOURCE + " = ?");
                        selectionArgs.add(source);
                        selection.append(" AND ").append(LibraryContract.Manga.COLUMN_NAME + " = ?");
                        selectionArgs.add(name);

                        subscriber.onNext
                                (mCupboard.withDatabase(mLibraryDatabase).query(Manga.class)
                                        .withSelection(selection.toString(), selectionArgs.toArray(new String[selectionArgs.size()]))
                                        .limit(1)
                                        .get()
                                );

                        mMangaReadWriteLock.readLock().unlock();
                    }
                    subscriber.onCompleted();
                } catch (Throwable e) {
                    subscriber.onError(e);
                }
            }
        });
    }

    /**
     * Retrieve recent chapter from the database.
     *
     * @param source   Source of the chapter, represented as a String.
     * @param url      URL to the chapter, represented as a String.
     * @param isOnline Indicates if the chapter is an online chapter or stored in device (offline).
     * @return Corresponding RecentChapter object.
     */
    @Override
    public Observable<RecentChapter> retrieveRecentChapter(final String source, final String url, final boolean isOnline) {
        return Observable.create(new Observable.OnSubscribe<RecentChapter>() {
            @Override
            public void call(Subscriber<? super RecentChapter> subscriber) {
                try {
                    if (!subscriber.isUnsubscribed()) {
                        mRecentChapterReadWriteLock.readLock().lock();

                        StringBuilder selection = new StringBuilder();
                        List<String> selectionArgs = new ArrayList<String>();

                        selection.append(ApplicationContract.RecentChapter.COLUMN_SOURCE + " = ?");
                        selectionArgs.add(source);
                        selection.append(" AND ").append(ApplicationContract.RecentChapter.COLUMN_URL + " = ?");
                        selectionArgs.add(url);

                        if (isOnline) {
                            selection.append(" AND ").append(ApplicationContract.RecentChapter.COLUMN_OFFLINE + " = ?");
                            selectionArgs.add(String.valueOf(0));
                        } else {
                            selection.append(" AND ").append(ApplicationContract.RecentChapter.COLUMN_OFFLINE + " = ?");
                            selectionArgs.add(String.valueOf(1));
                        }

                        subscriber.onNext
                                (mCupboard.withDatabase(mApplicationDatabase).query(RecentChapter.class)
                                        .withSelection(selection.toString(), selectionArgs.toArray(new String[selectionArgs.size()]))
                                        .limit(1)
                                        .get()
                                );

                        mRecentChapterReadWriteLock.readLock().unlock();
                    }
                    subscriber.onCompleted();
                } catch (Throwable e) {
                    subscriber.onError(e);
                }
            }
        });
    }

    @Override
    public Observable<Cursor> retrieveChapterAsCursor(final String[] columns, final String selection, final String[] selectionArgs, final String groupBy, final String having, final String orderBy, final String limit) {
        return Observable.create(new Observable.OnSubscribe<Cursor>() {
            @Override
            public void call(Subscriber<? super Cursor> subscriber) {
                try {
                    if (!subscriber.isUnsubscribed()) {
                        mChapterReadWriteLock.readLock().lock();

                        Cursor temporaryCursor = mApplicationDatabase.query(ApplicationContract.Chapter.TABLE_NAME,
                                columns,
                                selection,
                                selectionArgs,
                                groupBy,
                                having,
                                orderBy,
                                limit);

                        subscriber.onNext(temporaryCursor);
                    }
                } catch (Throwable e) {
                    subscriber.onError(e);
                }
            }
        });
    }

    @Override
    public Observable<Cursor> retrieveDownloadChapterAsCursor(final String[] columns, final String selection, final String[] selectionArgs, final String groupBy, final String having, final String orderBy, final String limit) {
        return Observable.create(new Observable.OnSubscribe<Cursor>() {
            @Override
            public void call(Subscriber<? super Cursor> subscriber) {
                try {
                    if (!subscriber.isUnsubscribed()) {
                        mDownloadChapterReadWriteLock.readLock().lock();

                        Cursor temporaryCursor = mApplicationDatabase.query(
                                ApplicationContract.DownloadChapter.TABLE_NAME,
                                columns,
                                selection,
                                selectionArgs,
                                groupBy,
                                having,
                                orderBy,
                                limit
                        );

                        subscriber.onNext(temporaryCursor);

                        mDownloadChapterReadWriteLock.readLock().unlock();
                    }
                    subscriber.onCompleted();
                } catch (Throwable e) {
                    subscriber.onError(e);
                }
            }
        });
    }

    @Override
    public Observable<Cursor> retrieveDownloadMangaAsCursor(final String[] columns, final String selection, final String[] selectionArgs, final String groupBy, final String having, final String orderBy, final String limit) {
        return Observable.create(new Observable.OnSubscribe<Cursor>() {
            @Override
            public void call(Subscriber<? super Cursor> subscriber) {
                try {
                    if (!subscriber.isUnsubscribed()) {
                        mDownloadMangaReadWriteLock.readLock().lock();

                        Cursor temporaryCursor = mApplicationDatabase.query(
                                ApplicationContract.DownloadManga.TABLE_NAME,
                                columns,
                                selection,
                                selectionArgs,
                                groupBy,
                                having,
                                orderBy,
                                limit
                        );

                        subscriber.onNext(temporaryCursor);

                        mDownloadMangaReadWriteLock.readLock().unlock();
                    }
                    subscriber.onCompleted();
                } catch (Throwable e) {
                    subscriber.onError(e);
                }
            }
        });
    }

    @Override
    public Observable<Cursor> retrieveDownloadPageAsCursor(final String[] columns, final String selection, final String[] selectionArgs, final String groupBy, final String having, final String orderBy, final String limit) {
        return Observable.create(new Observable.OnSubscribe<Cursor>() {
            @Override
            public void call(Subscriber<? super Cursor> subscriber) {
                try {
                    if (!subscriber.isUnsubscribed()) {
                        mDownloadPageReadWriteLock.readLock().lock();

                        Cursor temporaryCursor = mApplicationDatabase.query(
                                ApplicationContract.DownloadPage.TABLE_NAME,
                                columns,
                                selection,
                                selectionArgs,
                                groupBy,
                                having,
                                orderBy,
                                limit
                        );

                        subscriber.onNext(temporaryCursor);

                        mDownloadPageReadWriteLock.readLock().unlock();
                    }
                    subscriber.onCompleted();
                } catch (Throwable e) {
                    subscriber.onError(e);
                }
            }
        });
    }

    @Override
    public Observable<Cursor> retrieveFavoriteMangaAsCursor(final String[] columns, final String selection, final String[] selectionArgs, final String groupBy, final String having, final String orderBy, final String limit) {
        return Observable.create(new Observable.OnSubscribe<Cursor>() {
            @Override
            public void call(Subscriber<? super Cursor> subscriber) {
                try {
                    if (!subscriber.isUnsubscribed()) {
                        mFavoriteMangaReadWriteLock.readLock().lock();

                        Cursor temporaryCursor = mApplicationDatabase.query(
                                ApplicationContract.FavoriteManga.TABLE_NAME,
                                columns,
                                selection,
                                selectionArgs,
                                groupBy,
                                having,
                                orderBy,
                                limit
                        );

                        subscriber.onNext(temporaryCursor);

                        mFavoriteMangaReadWriteLock.readLock().unlock();
                    }
                    subscriber.onCompleted();
                } catch (Throwable e) {
                    subscriber.onError(e);
                }
            }
        });
    }

    @Override
    public Observable<Cursor> retrieveMangaAsCursor(final String[] columns, final String selection, final String[] selectionArgs, final String groupBy, final String having, final String orderBy, final String limit) {
        return Observable.create(new Observable.OnSubscribe<Cursor>() {
            @Override
            public void call(Subscriber<? super Cursor> subscriber) {
                try {
                    if (!subscriber.isUnsubscribed()) {
                        mMangaReadWriteLock.readLock().lock();

                        Cursor temporaryCursor = mLibraryDatabase.query(
                                LibraryContract.Manga.TABLE_NAME,
                                columns,
                                selection,
                                selectionArgs,
                                groupBy,
                                having,
                                orderBy,
                                limit
                        );

                        subscriber.onNext(temporaryCursor);

                        mMangaReadWriteLock.readLock().unlock();
                    }
                    subscriber.onCompleted();
                } catch (Throwable e) {
                    subscriber.onError(e);
                }
            }
        });
    }

    @Override
    public Observable<Cursor> retrieveRecentChapterAsCursor(final String[] columns, final String selection, final String[] selectionArgs, final String groupBy, final String having, final String orderBy, final String limit) {
        return Observable.create(new Observable.OnSubscribe<Cursor>() {
            @Override
            public void call(Subscriber<? super Cursor> subscriber) {
                try {
                    if (!subscriber.isUnsubscribed()) {
                        mRecentChapterReadWriteLock.readLock().lock();

                        Cursor temporaryCursor = mApplicationDatabase.query(
                                ApplicationContract.RecentChapter.TABLE_NAME,
                                columns,
                                selection,
                                selectionArgs,
                                groupBy,
                                having,
                                orderBy,
                                limit
                        );

                        subscriber.onNext(temporaryCursor);

                        mRecentChapterReadWriteLock.readLock().unlock();
                    }
                    subscriber.onCompleted();
                } catch (Throwable e) {
                    subscriber.onError(e);
                }
            }
        });
    }

    @Override
    public Observable<Chapter> retrieveAllChapterAsStream(String[] columns, String selection, String[] selectionArgs, String groupBy, String having, String orderBy, String limit) {
        return retrieveChapterAsCursor(columns, selection, selectionArgs, groupBy, having, orderBy, limit)
                .flatMap(new Func1<Cursor, Observable<Chapter>>() {
                    @Override
                    public Observable<Chapter> call(final Cursor cursor) {
                        return Observable.create(new Observable.OnSubscribe<Chapter>() {
                            @Override
                            public void call(Subscriber<? super Chapter> subscriber) {
                                try {
                                    Iterable<Chapter> iterable = mCupboard.withCursor(cursor).iterate(Chapter.class);
                                    for (Chapter currentRow : iterable) {
                                        if (!subscriber.isUnsubscribed()) {
                                            subscriber.onNext(currentRow);
                                        }
                                    }
                                    subscriber.onCompleted();
                                } catch (Throwable e) {
                                    subscriber.onError(e);
                                }
                            }
                        });
                    }
                })
                .onBackpressureBuffer();
    }

    @Override
    public Observable<DownloadChapter> retrieveAllDownloadChapterAsStream(String[] columns, String selection, String[] selectionArgs, String groupBy, String having, String orderBy, String limit) {
        return null;
    }

    @Override
    public Observable<DownloadManga> retrieveAllDownloadMangaAsStream(String[] columns, String selection, String[] selectionArgs, String groupBy, String having, String orderBy, String limit) {
        return null;
    }

    @Override
    public Observable<DownloadPage> retrieveAllDownloadPageAsStream(String[] columns, String selection, String[] selectionArgs, String groupBy, String having, String orderBy, String limit) {
        return null;
    }

    @Override
    public Observable<FavoriteManga> retrieveAllFavoriteMangaAsStream(String[] columns, String selection, String[] selectionArgs, String groupBy, String having, String orderBy, String limit) {
        return null;
    }

    @Override
    public Observable<Manga> retrieveAllMangaAsStream(String[] columns, String selection, String[] selectionArgs, String groupBy, String having, String orderBy, String limit) {
        return null;
    }

    @Override
    public Observable<RecentChapter> retrieveAllRecentChapterAsStream(String[] columns, String selection, String[] selectionArgs, String groupBy, String having, String orderBy, String limit) {
        return null;
    }

    @Override
    public Observable<Integer> updateChapter(ContentValues updateValues, String selection, String[] selectionArgs) {
        return null;
    }

    @Override
    public Observable<Integer> updateDownloadChapter(ContentValues updateValues, String selection, String[] selectionArgs) {
        return null;
    }

    @Override
    public Observable<Integer> updateDownloadManga(ContentValues updateValues, String selection, String[] selectionArgs) {
        return null;
    }

    @Override
    public Observable<Integer> updateDownloadPage(ContentValues updateValues, String selection, String[] selectionArgs) {
        return null;
    }

    @Override
    public Observable<Integer> updateFavoriteManga(ContentValues updateValues, String selection, String[] selectionArgs) {
        return null;
    }

    @Override
    public Observable<Integer> updateManga(ContentValues updateValues, String selection, String[] selectionArgs) {
        return null;
    }

    @Override
    public Observable<Integer> updateRecentChapter(ContentValues updateValues, String selection, String[] selectionArgs) {
        return null;
    }

    @Override
    public Observable<Integer> updateAllChapter(ContentValues updateValues) {
        return null;
    }

    @Override
    public Observable<Integer> updateAllDownloadChapter(ContentValues updateValues) {
        return null;
    }

    @Override
    public Observable<Integer> updateAllDownloadManga(ContentValues updateValues) {
        return null;
    }

    @Override
    public Observable<Integer> updateAllDownloadPage(ContentValues updateValues) {
        return null;
    }

    @Override
    public Observable<Integer> updateAllFavoriteManga(ContentValues updateValues) {
        return null;
    }

    @Override
    public Observable<Integer> updateAllManga(ContentValues updateValues) {
        return null;
    }

    @Override
    public Observable<Integer> updateAllRecentChapter(ContentValues updateValues) {
        return null;
    }

    @Override
    public Observable<Boolean> deleteChapter(Chapter chapter) {
        return null;
    }

    @Override
    public Observable<Boolean> deleteDownloadChapter(DownloadChapter downloadChapter) {
        return null;
    }

    @Override
    public Observable<Boolean> deleteDownloadManga(DownloadManga downloadManga) {
        return null;
    }

    @Override
    public Observable<Boolean> deleteDownloadPage(DownloadPage downloadPage) {
        return null;
    }

    @Override
    public Observable<Boolean> deleteFavoriteManga(FavoriteManga favoriteManga) {
        return null;
    }

    @Override
    public Observable<Boolean> deleteManga(Manga manga) {
        return null;
    }

    @Override
    public Observable<Boolean> deleteRecentChapter(RecentChapter recentChapter) {
        return null;
    }

    @Override
    public Observable<Integer> deleteAllChapter(String selection, String[] selectionArgs) {
        return null;
    }

    @Override
    public Observable<Integer> deleteAllDownloadChapter(String selection, String[] selectionArgs) {
        return null;
    }

    @Override
    public Observable<Integer> deleteAllDownloadManga(String selection, String[] selectionArgs) {
        return null;
    }

    @Override
    public Observable<Integer> deleteAllDownloadPage(String selection, String[] selectionArgs) {
        return null;
    }

    @Override
    public Observable<Integer> deleteAllFavoriteManga(String selection, String[] selectionArgs) {
        return null;
    }

    @Override
    public Observable<Integer> deleteAllManga(String selection, String[] selectionArgs) {
        return null;
    }

    @Override
    public Observable<Integer> deleteAllRecentChapter(String selection, String[] selectionArgs) {
        return null;
    }

    @Override
    public void beginApplicationTransaction() {
        mApplicationDatabase.beginTransaction();
    }

    @Override
    public void endApplicationTransaction() {

    }

    @Override
    public void setApplicationTransactionSuccessful() {

    }

    @Override
    public void beginLibraryTransaction() {

    }

    @Override
    public void endLibraryTransaction() {

    }

    @Override
    public void setLibraryTransactionSuccessful() {

    }
}
=======
package com.clipsub.csmanga.data.databases;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.clipsub.csmanga.models.Chapter;
import com.clipsub.csmanga.models.DownloadChapter;
import com.clipsub.csmanga.models.DownloadManga;
import com.clipsub.csmanga.models.DownloadPage;
import com.clipsub.csmanga.models.FavoriteManga;
import com.clipsub.csmanga.models.Manga;
import com.clipsub.csmanga.models.RecentChapter;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import nl.qbusict.cupboard.Cupboard;
import nl.qbusict.cupboard.CupboardBuilder;
import rx.Observable;
import rx.Subscriber;
import rx.functions.Func1;

public class QueryManagerImpl implements QueryManager {
    public static final String TAG = QueryManagerImpl.class.getSimpleName();

    private Cupboard mCupboard;

    /**
     * Application database instance (download queue, etc).
     */
    private SQLiteDatabase mApplicationDatabase;
    private SQLiteDatabase mLibraryDatabase;

    private static final ReentrantReadWriteLock mChapterReadWriteLock = new ReentrantReadWriteLock();
    private static final ReentrantReadWriteLock mDownloadChapterReadWriteLock = new ReentrantReadWriteLock();
    private static final ReentrantReadWriteLock mDownloadMangaReadWriteLock = new ReentrantReadWriteLock();
    private static final ReentrantReadWriteLock mDownloadPageReadWriteLock = new ReentrantReadWriteLock();
    private static final ReentrantReadWriteLock mFavoriteMangaReadWriteLock = new ReentrantReadWriteLock();
    private static final ReentrantReadWriteLock mMangaReadWriteLock = new ReentrantReadWriteLock();
    private static final ReentrantReadWriteLock mRecentChapterReadWriteLock = new ReentrantReadWriteLock();

    public QueryManagerImpl(ApplicationSQLiteOpenHelper applicationSQLiteOpenHelper,
                            LibrarySQLiteOpenHelper librarySQLiteOpenHelper) {

        mApplicationDatabase = applicationSQLiteOpenHelper.getWritableDatabase();
        mApplicationDatabase.enableWriteAheadLogging();
        mLibraryDatabase = librarySQLiteOpenHelper.getWritableDatabase();
        mLibraryDatabase.enableWriteAheadLogging();

        initializeCupboard();
    }

    /**
     * Initialize the Cupboard instance.
     */
    private void initializeCupboard() {
        mCupboard = new CupboardBuilder().build();
        mCupboard.register(Chapter.class);
        mCupboard.register(DownloadManga.class);
        mCupboard.register(DownloadChapter.class);
        mCupboard.register(DownloadPage.class);
        mCupboard.register(FavoriteManga.class);
        mCupboard.register(Manga.class);
        mCupboard.register(RecentChapter.class);
    }

    // TODO: Complete all below.

    @Override
    public Observable<Cursor> rawApplicationQuery(final String sqlStatement, final String[] selectionArgs) {
        return Observable.create(new Observable.OnSubscribe<Cursor>() {
            @Override
            public void call(Subscriber<? super Cursor> subscriber) {
                try {
                    if (!subscriber.isUnsubscribed()) {
                        subscriber.onNext(mApplicationDatabase.rawQuery(sqlStatement, selectionArgs));
                    }

                    subscriber.onCompleted();
                } catch (Throwable e) {
                    subscriber.onError(e);
                }
            }
        });
    }

    @Override
    public Observable<Cursor> rawLibraryQuery(final String sqlStatement, final String[] selectionArgs) {
        return Observable.create(new Observable.OnSubscribe<Cursor>() {
            @Override
            public void call(Subscriber<? super Cursor> subscriber) {
                try {
                    if (!subscriber.isUnsubscribed()) {
                        subscriber.onNext(mLibraryDatabase.rawQuery(sqlStatement, selectionArgs));
                    }
                    subscriber.onCompleted();
                } catch (Throwable e) {
                    subscriber.onError(e);
                }
            }
        });
    }

    @Override
    public Observable<Long> createChapter(final Chapter chapter) {
        return Observable.create(new Observable.OnSubscribe<Long>() {
            @Override
            public void call(Subscriber<? super Long> subscriber) {
                try {
                    subscriber.onNext(mCupboard.withDatabase(mApplicationDatabase).put(chapter));
                    subscriber.onCompleted();
                } catch (Throwable e) {
                    subscriber.onError(e);
                }
            }
        });
    }

    @Override
    public Observable<Long> createDownloadChapter(final DownloadChapter downloadChapter) {
        return Observable.create(new Observable.OnSubscribe<Long>() {
            @Override
            public void call(Subscriber<? super Long> subscriber) {
                try {
                    subscriber.onNext(mCupboard.withDatabase(mApplicationDatabase).put(downloadChapter));
                    subscriber.onCompleted();
                } catch (Throwable e) {
                    subscriber.onError(e);
                }

            }
        });
    }

    @Override
    public Observable<Long> createDownloadManga(final DownloadManga downloadManga) {
        return Observable.create(new Observable.OnSubscribe<Long>() {
            @Override
            public void call(Subscriber<? super Long> subscriber) {
                try {
                    subscriber.onNext(mCupboard.withDatabase(mApplicationDatabase).put(downloadManga));
                    subscriber.onCompleted();
                } catch (Throwable e) {
                    subscriber.onError(e);
                }
            }
        });
    }

    @Override
    public Observable<Long> createDownloadPage(final DownloadPage downloadPage) {
        return Observable.create(new Observable.OnSubscribe<Long>() {
            @Override
            public void call(Subscriber<? super Long> subscriber) {
                try {
                    subscriber.onNext(mCupboard.withDatabase(mApplicationDatabase).put(downloadPage));
                    subscriber.onCompleted();
                } catch (Throwable e) {
                    subscriber.onError(e);
                }
            }
        });
    }

    @Override
    public Observable<Long> createFavoriteManga(final FavoriteManga favoriteManga) {
        return Observable.create(new Observable.OnSubscribe<Long>() {
            @Override
            public void call(Subscriber<? super Long> subscriber) {
                try {
                    subscriber.onNext(mCupboard.withDatabase(mApplicationDatabase).put(favoriteManga));
                    subscriber.onCompleted();
                } catch (Throwable e) {
                    subscriber.onError(e);
                }
            }
        });
    }

    @Override
    public Observable<Long> createManga(final Manga manga) {
        return Observable.create(new Observable.OnSubscribe<Long>() {
            @Override
            public void call(Subscriber<? super Long> subscriber) {
                try {
                    subscriber.onNext(mCupboard.withDatabase(mLibraryDatabase).put(manga));
                    subscriber.onCompleted();
                } catch (Throwable e) {
                    subscriber.onError(e);
                }
            }
        });
    }

    @Override
    public Observable<Long> createRecentChapter(final RecentChapter recentChapter) {
        return Observable.create(new Observable.OnSubscribe<Long>() {
            @Override
            public void call(Subscriber<? super Long> subscriber) {
                try {
                    subscriber.onNext(mCupboard.withDatabase(mApplicationDatabase).put(recentChapter));
                    subscriber.onCompleted();
                } catch (Throwable e) {
                    subscriber.onError(e);
                }
            }
        });
    }

    @Override
    public Observable<Chapter> retrieveChapter(final String source, final String url) {
        return Observable.create(new Observable.OnSubscribe<Chapter>() {
            @Override
            public void call(Subscriber<? super Chapter> subscriber) {
                try {
                    if (!subscriber.isUnsubscribed()) {
                        mChapterReadWriteLock.readLock().lock();

                        StringBuilder selection = new StringBuilder();
                        List<String> selectionArgs = new ArrayList<String>();

                        selection.append(ApplicationContract.Chapter.COLUMN_SOURCE += " = ?");
                        selectionArgs.add(source);
                        selection.append(" AND ").append(ApplicationContract.Chapter.COLUMN_URL + " = ?");
                        selectionArgs.add(url);

                        subscriber.onNext
                                (mCupboard.withDatabase(mApplicationDatabase).query(Chapter.class)
                                        .withSelection(selection.toString(),
                                                selectionArgs.toArray(new String[selectionArgs.size()]))
                                        .limit(1)
                                        .get()
                                );

                        mChapterReadWriteLock.readLock().unlock();
                    }

                    subscriber.onCompleted();
                } catch (Throwable e) {
                    subscriber.onError(e);
                }
            }
        });
    }

    @Override
    public Observable<DownloadChapter> retrieveDownloadChapter(final String source, final String url) {
        return Observable.create(new Observable.OnSubscribe<DownloadChapter>() {
            @Override
            public void call(Subscriber<? super DownloadChapter> subscriber) {
                try {
                    if (!subscriber.isUnsubscribed()) {
                        mDownloadChapterReadWriteLock.readLock().lock();

                        StringBuilder selection = new StringBuilder();
                        List<String> selectionArgs = new ArrayList<String>();

                        selection.append(ApplicationContract.DownloadChapter.COLUMN_SOURCE + " = ?");
                        selectionArgs.add(source);
                        selection.append(" AND ").append(ApplicationContract.DownloadChapter.COLUMN_URL + " = ?");
                        selectionArgs.add(url);

                        subscriber.onNext
                                (mCupboard.withDatabase(mApplicationDatabase).query(DownloadChapter.class)
                                        .withSelection(selection.toString(), selectionArgs.toArray(new String[selectionArgs.size()]))
                                        .limit(1)
                                        .get()
                                );

                        mDownloadChapterReadWriteLock.readLock().unlock();
                    }
                    subscriber.onCompleted();
                } catch (Throwable e) {
                    subscriber.onError(e);
                }
            }
        });
    }

    @Override
    public Observable<DownloadManga> retrieveDownloadManga(final String source, final String name) {
        return Observable.create(new Observable.OnSubscribe<DownloadManga>() {
            @Override
            public void call(Subscriber<? super DownloadManga> subscriber) {
                try {
                    if (!subscriber.isUnsubscribed()) {
                        mDownloadMangaReadWriteLock.readLock().lock();

                        StringBuilder selection = new StringBuilder();
                        List<String> selectionArgs = new ArrayList<String>();

                        selection.append(ApplicationContract.DownloadManga.COLUMN_SOURCE + " = ?");
                        selectionArgs.add(source);
                        selection.append(" AND ").append(ApplicationContract.DownloadManga.COLUMN_NAME + " = ?");
                        selectionArgs.add(name);

                        subscriber.onNext
                                (mCupboard.withDatabase(mApplicationDatabase).query(DownloadManga.class)
                                        .withSelection(selection.toString(), selectionArgs.toArray(new String[selectionArgs.size()]))
                                        .limit(1)
                                        .get()
                                );

                        mDownloadMangaReadWriteLock.readLock().unlock();
                    }
                    subscriber.onCompleted();
                } catch (Throwable e) {
                    subscriber.onError(e);
                }
            }
        });
    }

    @Override
    public Observable<DownloadPage> retrieveDownloadPage(final String url) {
        return Observable.create(new Observable.OnSubscribe<DownloadPage>() {
            @Override
            public void call(Subscriber<? super DownloadPage> subscriber) {
                try {
                    if (!subscriber.isUnsubscribed()) {
                        mDownloadPageReadWriteLock.readLock().lock();

                        StringBuilder selection = new StringBuilder();
                        List<String> selectionArgs = new ArrayList<String>();

                        selection.append(ApplicationContract.DownloadPage.COLUMN_URL + " = ?");
                        selectionArgs.add(url);

                        subscriber.onNext(mCupboard.withDatabase(mApplicationDatabase).query(DownloadPage.class)
                                .withSelection(selection.toString(),
                                        selectionArgs.toArray(new String[selectionArgs.size()]))
                                .limit(1)
                                .get()
                        );

                        mDownloadPageReadWriteLock.readLock().unlock();
                    }

                    subscriber.onCompleted();
                } catch (Throwable e) {
                    subscriber.onError(e);
                }
            }
        });
    }

    @Override
    public Observable<FavoriteManga> retrieveFavoriteManga(final String source, final String name) {
        return Observable.create(new Observable.OnSubscribe<FavoriteManga>() {
            @Override
            public void call(Subscriber<? super FavoriteManga> subscriber) {
                try {
                    if (!subscriber.isUnsubscribed()) {
                        mFavoriteMangaReadWriteLock.readLock().lock();

                        StringBuilder selection = new StringBuilder();
                        List<String> selectionArgs = new ArrayList<String>();

                        selection.append(ApplicationContract.FavoriteManga.COLUMN_SOURCE + " = ?");
                        selectionArgs.add(source);
                        selection.append(" AND ").append(ApplicationContract.FavoriteManga.COLUMN_NAME + " = ?");
                        selectionArgs.add(name);

                        subscriber.onNext
                                (mCupboard.withDatabase(mApplicationDatabase).query(FavoriteManga.class)
                                        .withSelection(selection.toString(), selectionArgs.toArray(new String[selectionArgs.size()]))
                                        .limit(1)
                                        .get()
                                );

                        mFavoriteMangaReadWriteLock.readLock().unlock();
                    }
                    subscriber.onCompleted();
                } catch (Throwable e) {
                    subscriber.onError(e);
                }
            }
        });
    }

    @Override
    public Observable<Manga> retrieveManga(final String source, final String name) {
        return Observable.create(new Observable.OnSubscribe<Manga>() {
            @Override
            public void call(Subscriber<? super Manga> subscriber) {
                try {
                    if (!subscriber.isUnsubscribed()) {
                        mMangaReadWriteLock.readLock().lock();

                        StringBuilder selection = new StringBuilder();
                        List<String> selectionArgs = new ArrayList<String>();

                        selection.append(LibraryContract.Manga.COLUMN_SOURCE + " = ?");
                        selectionArgs.add(source);
                        selection.append(" AND ").append(LibraryContract.Manga.COLUMN_NAME + " = ?");
                        selectionArgs.add(name);

                        subscriber.onNext
                                (mCupboard.withDatabase(mLibraryDatabase).query(Manga.class)
                                        .withSelection(selection.toString(), selectionArgs.toArray(new String[selectionArgs.size()]))
                                        .limit(1)
                                        .get()
                                );

                        mMangaReadWriteLock.readLock().unlock();
                    }
                    subscriber.onCompleted();
                } catch (Throwable e) {
                    subscriber.onError(e);
                }
            }
        });
    }

    /**
     * Retrieve recent chapter from the database.
     *
     * @param source   Source of the chapter, represented as a String.
     * @param url      URL to the chapter, represented as a String.
     * @param isOnline Indicates if the chapter is an online chapter or stored in device (offline).
     * @return Corresponding RecentChapter object.
     */
    @Override
    public Observable<RecentChapter> retrieveRecentChapter(final String source, final String url, final boolean isOnline) {
        return Observable.create(new Observable.OnSubscribe<RecentChapter>() {
            @Override
            public void call(Subscriber<? super RecentChapter> subscriber) {
                try {
                    if (!subscriber.isUnsubscribed()) {
                        mRecentChapterReadWriteLock.readLock().lock();

                        StringBuilder selection = new StringBuilder();
                        List<String> selectionArgs = new ArrayList<String>();

                        selection.append(ApplicationContract.RecentChapter.COLUMN_SOURCE + " = ?");
                        selectionArgs.add(source);
                        selection.append(" AND ").append(ApplicationContract.RecentChapter.COLUMN_URL + " = ?");
                        selectionArgs.add(url);

                        if (isOnline) {
                            selection.append(" AND ").append(ApplicationContract.RecentChapter.COLUMN_OFFLINE + " = ?");
                            selectionArgs.add(String.valueOf(0));
                        } else {
                            selection.append(" AND ").append(ApplicationContract.RecentChapter.COLUMN_OFFLINE + " = ?");
                            selectionArgs.add(String.valueOf(1));
                        }

                        subscriber.onNext
                                (mCupboard.withDatabase(mApplicationDatabase).query(RecentChapter.class)
                                        .withSelection(selection.toString(), selectionArgs.toArray(new String[selectionArgs.size()]))
                                        .limit(1)
                                        .get()
                                );

                        mRecentChapterReadWriteLock.readLock().unlock();
                    }
                    subscriber.onCompleted();
                } catch (Throwable e) {
                    subscriber.onError(e);
                }
            }
        });
    }

    @Override
    public Observable<Cursor> retrieveChapterAsCursor(final String[] columns, final String selection, final String[] selectionArgs, final String groupBy, final String having, final String orderBy, final String limit) {
        return Observable.create(new Observable.OnSubscribe<Cursor>() {
            @Override
            public void call(Subscriber<? super Cursor> subscriber) {
                try {
                    if (!subscriber.isUnsubscribed()) {
                        mChapterReadWriteLock.readLock().lock();

                        Cursor temporaryCursor = mApplicationDatabase.query(ApplicationContract.Chapter.TABLE_NAME,
                                columns,
                                selection,
                                selectionArgs,
                                groupBy,
                                having,
                                orderBy,
                                limit);

                        subscriber.onNext(temporaryCursor);
                    }
                } catch (Throwable e) {
                    subscriber.onError(e);
                }
            }
        });
    }

    @Override
    public Observable<Cursor> retrieveDownloadChapterAsCursor(final String[] columns, final String selection, final String[] selectionArgs, final String groupBy, final String having, final String orderBy, final String limit) {
        return Observable.create(new Observable.OnSubscribe<Cursor>() {
            @Override
            public void call(Subscriber<? super Cursor> subscriber) {
                try {
                    if (!subscriber.isUnsubscribed()) {
                        mDownloadChapterReadWriteLock.readLock().lock();

                        Cursor temporaryCursor = mApplicationDatabase.query(
                                ApplicationContract.DownloadChapter.TABLE_NAME,
                                columns,
                                selection,
                                selectionArgs,
                                groupBy,
                                having,
                                orderBy,
                                limit
                        );

                        subscriber.onNext(temporaryCursor);

                        mDownloadChapterReadWriteLock.readLock().unlock();
                    }
                    subscriber.onCompleted();
                } catch (Throwable e) {
                    subscriber.onError(e);
                }
            }
        });
    }

    @Override
    public Observable<Cursor> retrieveDownloadMangaAsCursor(final String[] columns, final String selection, final String[] selectionArgs, final String groupBy, final String having, final String orderBy, final String limit) {
        return Observable.create(new Observable.OnSubscribe<Cursor>() {
            @Override
            public void call(Subscriber<? super Cursor> subscriber) {
                try {
                    if (!subscriber.isUnsubscribed()) {
                        mDownloadMangaReadWriteLock.readLock().lock();

                        Cursor temporaryCursor = mApplicationDatabase.query(
                                ApplicationContract.DownloadManga.TABLE_NAME,
                                columns,
                                selection,
                                selectionArgs,
                                groupBy,
                                having,
                                orderBy,
                                limit
                        );

                        subscriber.onNext(temporaryCursor);

                        mDownloadMangaReadWriteLock.readLock().unlock();
                    }
                    subscriber.onCompleted();
                } catch (Throwable e) {
                    subscriber.onError(e);
                }
            }
        });
    }

    @Override
    public Observable<Cursor> retrieveDownloadPageAsCursor(final String[] columns, final String selection, final String[] selectionArgs, final String groupBy, final String having, final String orderBy, final String limit) {
        return Observable.create(new Observable.OnSubscribe<Cursor>() {
            @Override
            public void call(Subscriber<? super Cursor> subscriber) {
                try {
                    if (!subscriber.isUnsubscribed()) {
                        mDownloadPageReadWriteLock.readLock().lock();

                        Cursor temporaryCursor = mApplicationDatabase.query(
                                ApplicationContract.DownloadPage.TABLE_NAME,
                                columns,
                                selection,
                                selectionArgs,
                                groupBy,
                                having,
                                orderBy,
                                limit
                        );

                        subscriber.onNext(temporaryCursor);

                        mDownloadPageReadWriteLock.readLock().unlock();
                    }
                    subscriber.onCompleted();
                } catch (Throwable e) {
                    subscriber.onError(e);
                }
            }
        });
    }

    @Override
    public Observable<Cursor> retrieveFavoriteMangaAsCursor(final String[] columns, final String selection, final String[] selectionArgs, final String groupBy, final String having, final String orderBy, final String limit) {
        return Observable.create(new Observable.OnSubscribe<Cursor>() {
            @Override
            public void call(Subscriber<? super Cursor> subscriber) {
                try {
                    if (!subscriber.isUnsubscribed()) {
                        mFavoriteMangaReadWriteLock.readLock().lock();

                        Cursor temporaryCursor = mApplicationDatabase.query(
                                ApplicationContract.FavoriteManga.TABLE_NAME,
                                columns,
                                selection,
                                selectionArgs,
                                groupBy,
                                having,
                                orderBy,
                                limit
                        );

                        subscriber.onNext(temporaryCursor);

                        mFavoriteMangaReadWriteLock.readLock().unlock();
                    }
                    subscriber.onCompleted();
                } catch (Throwable e) {
                    subscriber.onError(e);
                }
            }
        });
    }

    @Override
    public Observable<Cursor> retrieveMangaAsCursor(final String[] columns, final String selection, final String[] selectionArgs, final String groupBy, final String having, final String orderBy, final String limit) {
        return Observable.create(new Observable.OnSubscribe<Cursor>() {
            @Override
            public void call(Subscriber<? super Cursor> subscriber) {
                try {
                    if (!subscriber.isUnsubscribed()) {
                        mMangaReadWriteLock.readLock().lock();

                        Cursor temporaryCursor = mLibraryDatabase.query(
                                LibraryContract.Manga.TABLE_NAME,
                                columns,
                                selection,
                                selectionArgs,
                                groupBy,
                                having,
                                orderBy,
                                limit
                        );

                        subscriber.onNext(temporaryCursor);

                        mMangaReadWriteLock.readLock().unlock();
                    }
                    subscriber.onCompleted();
                } catch (Throwable e) {
                    subscriber.onError(e);
                }
            }
        });
    }

    @Override
    public Observable<Cursor> retrieveRecentChapterAsCursor(final String[] columns, final String selection, final String[] selectionArgs, final String groupBy, final String having, final String orderBy, final String limit) {
        return Observable.create(new Observable.OnSubscribe<Cursor>() {
            @Override
            public void call(Subscriber<? super Cursor> subscriber) {
                try {
                    if (!subscriber.isUnsubscribed()) {
                        mRecentChapterReadWriteLock.readLock().lock();

                        Cursor temporaryCursor = mApplicationDatabase.query(
                                ApplicationContract.RecentChapter.TABLE_NAME,
                                columns,
                                selection,
                                selectionArgs,
                                groupBy,
                                having,
                                orderBy,
                                limit
                        );

                        subscriber.onNext(temporaryCursor);

                        mRecentChapterReadWriteLock.readLock().unlock();
                    }
                    subscriber.onCompleted();
                } catch (Throwable e) {
                    subscriber.onError(e);
                }
            }
        });
    }

    @Override
    public Observable<Chapter> retrieveAllChapterAsStream(String[] columns, String selection, String[] selectionArgs, String groupBy, String having, String orderBy, String limit) {
        return retrieveChapterAsCursor(columns, selection, selectionArgs, groupBy, having, orderBy, limit)
                .flatMap(new Func1<Cursor, Observable<Chapter>>() {
                    @Override
                    public Observable<Chapter> call(final Cursor cursor) {
                        return Observable.create(new Observable.OnSubscribe<Chapter>() {
                            @Override
                            public void call(Subscriber<? super Chapter> subscriber) {
                                try {
                                    Iterable<Chapter> iterable = mCupboard.withCursor(cursor).iterate(Chapter.class);
                                    for (Chapter currentRow : iterable) {
                                        if (!subscriber.isUnsubscribed()) {
                                            subscriber.onNext(currentRow);
                                        }
                                    }
                                    subscriber.onCompleted();
                                } catch (Throwable e) {
                                    subscriber.onError(e);
                                }
                            }
                        });
                    }
                })
                .onBackpressureBuffer();
    }

    @Override
    public Observable<DownloadChapter> retrieveAllDownloadChapterAsStream(String[] columns, String selection, String[] selectionArgs, String groupBy, String having, String orderBy, String limit) {
        return null;
    }

    @Override
    public Observable<DownloadManga> retrieveAllDownloadMangaAsStream(String[] columns, String selection, String[] selectionArgs, String groupBy, String having, String orderBy, String limit) {
        return null;
    }

    @Override
    public Observable<DownloadPage> retrieveAllDownloadPageAsStream(String[] columns, String selection, String[] selectionArgs, String groupBy, String having, String orderBy, String limit) {
        return null;
    }

    @Override
    public Observable<FavoriteManga> retrieveAllFavoriteMangaAsStream(String[] columns, String selection, String[] selectionArgs, String groupBy, String having, String orderBy, String limit) {
        return null;
    }

    @Override
    public Observable<Manga> retrieveAllMangaAsStream(String[] columns, String selection, String[] selectionArgs, String groupBy, String having, String orderBy, String limit) {
        return null;
    }

    @Override
    public Observable<RecentChapter> retrieveAllRecentChapterAsStream(String[] columns, String selection, String[] selectionArgs, String groupBy, String having, String orderBy, String limit) {
        return null;
    }

    @Override
    public Observable<Integer> updateChapter(ContentValues updateValues, String selection, String[] selectionArgs) {
        return null;
    }

    @Override
    public Observable<Integer> updateDownloadChapter(ContentValues updateValues, String selection, String[] selectionArgs) {
        return null;
    }

    @Override
    public Observable<Integer> updateDownloadManga(ContentValues updateValues, String selection, String[] selectionArgs) {
        return null;
    }

    @Override
    public Observable<Integer> updateDownloadPage(ContentValues updateValues, String selection, String[] selectionArgs) {
        return null;
    }

    @Override
    public Observable<Integer> updateFavoriteManga(ContentValues updateValues, String selection, String[] selectionArgs) {
        return null;
    }

    @Override
    public Observable<Integer> updateManga(ContentValues updateValues, String selection, String[] selectionArgs) {
        return null;
    }

    @Override
    public Observable<Integer> updateRecentChapter(ContentValues updateValues, String selection, String[] selectionArgs) {
        return null;
    }

    @Override
    public Observable<Integer> updateAllChapter(ContentValues updateValues) {
        return null;
    }

    @Override
    public Observable<Integer> updateAllDownloadChapter(ContentValues updateValues) {
        return null;
    }

    @Override
    public Observable<Integer> updateAllDownloadManga(ContentValues updateValues) {
        return null;
    }

    @Override
    public Observable<Integer> updateAllDownloadPage(ContentValues updateValues) {
        return null;
    }

    @Override
    public Observable<Integer> updateAllFavoriteManga(ContentValues updateValues) {
        return null;
    }

    @Override
    public Observable<Integer> updateAllManga(ContentValues updateValues) {
        return null;
    }

    @Override
    public Observable<Integer> updateAllRecentChapter(ContentValues updateValues) {
        return null;
    }

    @Override
    public Observable<Boolean> deleteChapter(Chapter chapter) {
        return null;
    }

    @Override
    public Observable<Boolean> deleteDownloadChapter(DownloadChapter downloadChapter) {
        return null;
    }

    @Override
    public Observable<Boolean> deleteDownloadManga(DownloadManga downloadManga) {
        return null;
    }

    @Override
    public Observable<Boolean> deleteDownloadPage(DownloadPage downloadPage) {
        return null;
    }

    @Override
    public Observable<Boolean> deleteFavoriteManga(FavoriteManga favoriteManga) {
        return null;
    }

    @Override
    public Observable<Boolean> deleteManga(Manga manga) {
        return null;
    }

    @Override
    public Observable<Boolean> deleteRecentChapter(RecentChapter recentChapter) {
        return null;
    }

    @Override
    public Observable<Integer> deleteAllChapter(String selection, String[] selectionArgs) {
        return null;
    }

    @Override
    public Observable<Integer> deleteAllDownloadChapter(String selection, String[] selectionArgs) {
        return null;
    }

    @Override
    public Observable<Integer> deleteAllDownloadManga(String selection, String[] selectionArgs) {
        return null;
    }

    @Override
    public Observable<Integer> deleteAllDownloadPage(String selection, String[] selectionArgs) {
        return null;
    }

    @Override
    public Observable<Integer> deleteAllFavoriteManga(String selection, String[] selectionArgs) {
        return null;
    }

    @Override
    public Observable<Integer> deleteAllManga(String selection, String[] selectionArgs) {
        return null;
    }

    @Override
    public Observable<Integer> deleteAllRecentChapter(String selection, String[] selectionArgs) {
        return null;
    }

    @Override
    public void beginApplicationTransaction() {
        mApplicationDatabase.beginTransaction();
    }

    @Override
    public void endApplicationTransaction() {

    }

    @Override
    public void setApplicationTransactionSuccessful() {

    }

    @Override
    public void beginLibraryTransaction() {

    }

    @Override
    public void endLibraryTransaction() {

    }

    @Override
    public void setLibraryTransactionSuccessful() {

    }
}
>>>>>>> 78ab0b8a13b06d156a61db6d7ae3c71d13993a7f
