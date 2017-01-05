package com.clipsub.csmanga.data;

import com.clipsub.csmanga.models.Chapter;
import com.clipsub.csmanga.models.Manga;
import com.clipsub.csmanga.sources.UpdatePageMarker;

import java.util.List;

import rx.Observable;

/**
 * Base repository of ReactiveX implementation on this project.
 */
public interface CSMangaRepository {

    public Observable<String> getNameFromPreferenceSource();

    public Observable<String> getBaseUrlFromPreferenceSource();

    public Observable<String> getInitialUpdateUrlFromPreferenceSource();

    public Observable<List<String>> getGenresFromPreferenceSource();

    public Observable<UpdatePageMarker> pullLatestUpdatesFromNetwork(UpdatePageMarker updatePageMarker);

    public Observable<Manga> pullMangaFromNetwork(String source, String mangaUrl);

    public Observable<List<Chapter>> pullChaptersFromNetwork(String source, String mangaUrl, String mangaName);

    public Observable<String> pullImageUrlsFromNetwork(String source, String chapterUrl);
}
