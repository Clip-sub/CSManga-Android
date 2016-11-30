package com.clipsub.csmanga.data.factories;

import com.clipsub.csmanga.utils.DownloadUtils;
import com.clipsub.csmanga.utils.SearchUtils;

import java.util.ArrayList;

/**
 * Default factory class for data.
 */
public class DefaultFactory {

    /**
     * Class name for logging.
     */
    public static final String TAG = DefaultFactory.class.getSimpleName();

    private DefaultFactory() {
        throw new AssertionError(TAG + ": Cannot be initialized.");
    }

    /**
     * Default Chapter creation factory.
     */
    public static final class Chapter {
        public static final String DEFAULT_SOURCE = "No Source";
        public static final String DEFAULT_URL = "No Url";
        public static final String DEFAULT_PARENT_URL = "No Parent Url";
        public static final String DEFAULT_PARENT_NAME = "No Parent Name";
        public static final String DEFAULT_NAME = "No Name";
        public static final boolean DEFAULT_NEW = false;
        public static final long DEFAULT_DATE = 0;
        public static final int DEFAULT_NUMBER = 0;

        private Chapter() {
            throw new AssertionError();
        }

        public static com.clipsub.csmanga.models.Chapter constructDefault() {
            com.clipsub.csmanga.models.Chapter newInstance = new com.clipsub.csmanga.models.Chapter();
            newInstance.setSource(DEFAULT_SOURCE);
            newInstance.setUrl(DEFAULT_URL);
            newInstance.setParentUrl(DEFAULT_PARENT_URL);
            newInstance.setParentName(DEFAULT_PARENT_NAME);
            newInstance.setName(DEFAULT_NAME);
            newInstance.setNew(DEFAULT_NEW);
            newInstance.setDate(DEFAULT_DATE);
            newInstance.setNumber(DEFAULT_NUMBER);

            return newInstance;
        }
    }

    /**
     * Default DownloadChapter creation factory.
     */
    public static final class DownloadChapter {
        public static final String DEFAULT_SOURCE = "No Source";
        public static final String DEFAULT_URL = "No Url";
        public static final String DEFAULT_PARENT_URL = "No Parent Url";
        public static final String DEFAULT_PARENT_NAME = "No Parent Name";
        public static final String DEFAULT_NAME = "No Name";
        public static final String DEFAULT_DIRECTORY = "No Directory";
        public static final int DEFAULT_CURRENT_PAGE = 0;
        public static final int DEFAULT_TOTAL_PAGES = 0;

        // TODO: Originally FLAG_FAILED.
        public static final int DEFAULT_FLAG = DownloadUtils.FLAG_PENDING;

        private DownloadChapter() {
            throw new AssertionError();
        }

        public static com.clipsub.csmanga.models.DownloadChapter constructDefault() {
            com.clipsub.csmanga.models.DownloadChapter newInstance = new com.clipsub.csmanga.models.DownloadChapter();

            newInstance.setSource(DEFAULT_SOURCE);
            newInstance.setUrl(DEFAULT_URL);
            newInstance.setParentUrl(DEFAULT_PARENT_URL);
            newInstance.setParentName(DEFAULT_PARENT_NAME);
            newInstance.setName(DEFAULT_NAME);
            newInstance.setDirectory(DEFAULT_DIRECTORY);
            newInstance.setCurrentPage(DEFAULT_CURRENT_PAGE);
            newInstance.setTotalPages(DEFAULT_TOTAL_PAGES);
            newInstance.setFlag(DEFAULT_FLAG);

            return newInstance;
        }
    }

    public static final class DownloadManga {
        public static final String DEFAULT_SOURCE = "No Source";
        public static final String DEFAULT_URL = "No Url";
        public static final String DEFAULT_ARTIST = "No Artist";
        public static final String DEFAULT_AUTHOR = "No Author";
        public static final String DEFAULT_DESCRIPTION = "No Description";
        public static final String DEFAULT_GENRE = "No Genre";
        public static final String DEFAULT_NAME = "No Name";
        public static final boolean DEFAULT_COMPLETED = false;
        public static final String DEFAULT_THUMBNAIL_URL = "No Thumbnail Url";

        private DownloadManga() {
            throw new AssertionError();
        }

        public static com.clipsub.csmanga.models.DownloadManga constructDefault() {
            com.clipsub.csmanga.models.DownloadManga newInstance = new com.clipsub.csmanga.models.DownloadManga();

            newInstance.setSource(DEFAULT_SOURCE);
            newInstance.setUrl(DEFAULT_URL);
            newInstance.setArtist(DEFAULT_ARTIST);
            newInstance.setAuthor(DEFAULT_AUTHOR);
            newInstance.setDescription(DEFAULT_DESCRIPTION);
            newInstance.setGenre(DEFAULT_GENRE);
            newInstance.setName(DEFAULT_NAME);
            newInstance.setCompleted(DEFAULT_COMPLETED);
            newInstance.setThumbnailUrl(DEFAULT_THUMBNAIL_URL);

            return newInstance;
        }
    }

    public static final class DownloadPage {
        public static final String DEFAULT_URL = "No Url";
        public static final String DEFAULT_PARENT_URL = "No Parent Url";
        public static final String DEFAULT_DIRECTORY = "No Directory";
        public static final String DEFAULT_NAME = "No Name";
        public static final int DEFAULT_FLAG = DownloadUtils.FLAG_FAILED;

        private DownloadPage() {
            throw new AssertionError();
        }

        public static com.clipsub.csmanga.models.DownloadPage constructDefault() {
            com.clipsub.csmanga.models.DownloadPage newInstance = new com.clipsub.csmanga.models.DownloadPage();
            newInstance.setUrl(DEFAULT_URL);
            newInstance.setParentUrl(DEFAULT_PARENT_URL);
            newInstance.setDirectory(DEFAULT_DIRECTORY);
            newInstance.setName(DEFAULT_NAME);
            newInstance.setFlag(DEFAULT_FLAG);

            return newInstance;
        }
    }

    public static final class FavoriteManga {
        public static final String DEFAULT_SOURCE = "No Source";
        public static final String DEFAULT_URL = "No Url";
        public static final String DEFAULT_NAME = "No Name";
        public static final String DEFAULT_THUMBNAIL_URL = "No Thumbnail Url";

        private FavoriteManga() {
            throw new AssertionError();
        }

        public static com.clipsub.csmanga.models.FavoriteManga constructDefault() {
            com.clipsub.csmanga.models.FavoriteManga newInstance = new com.clipsub.csmanga.models.FavoriteManga();

            newInstance.setSource(DEFAULT_SOURCE);
            newInstance.setUrl(DEFAULT_URL);
            newInstance.setName(DEFAULT_NAME);
            newInstance.setThumbnailUrl(DEFAULT_THUMBNAIL_URL);

            return newInstance;
        }
    }

    public static final class Manga {
        public static final String DEFAULT_SOURCE = "No Source";
        public static final String DEFAULT_URL = "No Url";
        public static final String DEFAULT_ARTIST = "No Artist";
        public static final String DEFAULT_AUTHOR = "No Author";
        public static final String DEFAULT_DESCRIPTION = "No Description";
        public static final String DEFAULT_GENRE = "No Genre";
        public static final String DEFAULT_NAME = "No Name";
        public static final boolean DEFAULT_COMPLETED = false;
        public static final String DEFAULT_THUMBNAIL_URL = "No Thumbnail Url";
        public static final int DEFAULT_RANK = 0;
        public static final long DEFAULT_UPDATED = 0;
        public static final int DEFAULT_UPDATE_COUNT = 0;
        public static final boolean DEFAULT_INITIALIZED = false;

        private Manga() {
            throw new AssertionError();
        }

        public static com.clipsub.csmanga.models.Manga constructDefault() {
            com.clipsub.csmanga.models.Manga newInstance = new com.clipsub.csmanga.models.Manga();

            newInstance.setSource(DEFAULT_SOURCE);
            newInstance.setUrl(DEFAULT_URL);
            newInstance.setArtist(DEFAULT_ARTIST);
            newInstance.setAuthor(DEFAULT_AUTHOR);
            newInstance.setDescription(DEFAULT_DESCRIPTION);
            newInstance.setGenre(DEFAULT_GENRE);
            newInstance.setName(DEFAULT_NAME);
            newInstance.setCompleted(DEFAULT_COMPLETED);
            newInstance.setThumbnailUrl(DEFAULT_THUMBNAIL_URL);
            newInstance.setRank(DEFAULT_RANK);
            newInstance.setUpdated(DEFAULT_UPDATED);
            newInstance.setUpdateCount(DEFAULT_UPDATE_COUNT);
            newInstance.setInitialized(DEFAULT_INITIALIZED);

            return newInstance;
        }
    }

    public static final class RecentChapter {
        public static final String DEFAULT_SOURCE = "No Source";
        public static final String DEFAULT_URL = "No Url";
        public static final String DEFAULT_PARENT_URL = "No Parent Url";
        public static final String DEFAULT_PARENT_NAME = "No Parent Name";
        public static final String DEFAULT_NAME = "No Name";
        public static final String DEFAULT_THUMBNAIL_URL = "No Thumbnail Url";
        public static final long DEFAULT_DATE = 0;
        public static final int DEFAULT_PAGE_NUMBER = 0;
        public static final boolean DEFAULT_OFFLINE = false;

        private RecentChapter() {
            throw new AssertionError();
        }

        public static com.clipsub.csmanga.models.RecentChapter constructDefault() {
            com.clipsub.csmanga.models.RecentChapter newInstance = new com.clipsub.csmanga.models.RecentChapter();

            newInstance.setSource(DEFAULT_SOURCE);
            newInstance.setUrl(DEFAULT_URL);
            newInstance.setParentUrl(DEFAULT_PARENT_URL);
            newInstance.setParentName(DEFAULT_PARENT_NAME);
            newInstance.setName(DEFAULT_NAME);
            newInstance.setThumbnailUrl(DEFAULT_THUMBNAIL_URL);
            newInstance.setDate(DEFAULT_DATE);
            newInstance.setPageNumber(DEFAULT_PAGE_NUMBER);
            newInstance.setOffline(DEFAULT_OFFLINE);

            return newInstance;
        }
    }

    public static final class SearchCatalogueWrapper {
        public static final String DEFAULT_NAME = SearchUtils.NAME_EMPTY;
        public static final String DEFAULT_STATUS = SearchUtils.STATUS_ALL;
        public static final String DEFAULT_ORDER_BY = SearchUtils.ORDER_BY_RANK;

        private SearchCatalogueWrapper() {
            throw new AssertionError();
        }

        public static com.clipsub.csmanga.utils.wrappers.SearchCatalogueWrapper constructDefault() {
            com.clipsub.csmanga.utils.wrappers.SearchCatalogueWrapper newInstance = new com.clipsub.csmanga.utils.wrappers.SearchCatalogueWrapper();

            newInstance.setNameArgs(DEFAULT_NAME);
            newInstance.setStatusArgs(DEFAULT_STATUS);
            newInstance.setOrderByArgs(DEFAULT_ORDER_BY);
            newInstance.setGenresArgs(new ArrayList<String>());

            return newInstance;
        }
    }

    public static final class UpdatePageMarker {
        public static final String DEFAULT_NEXT_PAGE_URL = "No Next Page Url";
        public static final int DEFAULT_LAST_MANGA_POSITION = 0;

        private UpdatePageMarker() {
            throw new AssertionError();
        }

        public static com.clipsub.csmanga.sources.UpdatePageMarker constructDefault(String initialUpdateUrl) {
            return new com.clipsub.csmanga.sources.UpdatePageMarker(initialUpdateUrl, DEFAULT_LAST_MANGA_POSITION);
        }
    }
}
