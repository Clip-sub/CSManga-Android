package com.clipsub.csmanga.sources;

import com.clipsub.csmanga.models.Chapter;
import com.clipsub.csmanga.models.Manga;

import java.util.List;

import rx.Observable;

/**
 * Public observable interface for manga sources.
 */
public interface Source {

    Observable<String> getName();

    Observable<String> getBaseUrl();

    Observable<String> getInitialUpdateUrl();

    Observable<List<String>> getGenres();

    Observable<UpdatePageMarker> pullLatestUpdatesFromNetwork(UpdatePageMarker newUpdate);

    Observable<Manga> pullMangaFromNetwork(String mangaUrl);

    Observable<List<Chapter>> pullChaptersFromNetwork(String mangaUrl, String mangaName);

    Observable<String> pullImageUrlsFromNetwork(String chapterUrl);

    // TODO: Inspect this.
    Observable<String> recursivelyConstructDatabase(String url);
}
