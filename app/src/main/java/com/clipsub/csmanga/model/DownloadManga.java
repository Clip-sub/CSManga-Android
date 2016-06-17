package com.clipsub.csmanga.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Model class for a download of Manga entity.
 */
public class DownloadManga implements Parcelable {

    public static final String TAG = DownloadManga.class.getSimpleName();

    public static final String PARCELABLE_KEY = TAG + ":" + "ParcelableKey";

    public static final Creator<DownloadManga> CREATOR = new Creator<DownloadManga>() {
        @Override
        public DownloadManga createFromParcel(Parcel source) {
            return new DownloadManga(source);
        }

        @Override
        public DownloadManga[] newArray(int size) {
            return new DownloadManga[size];
        }
    };

    private Long _id;

    private String source;
    private String url;

    private String artist;
    private String author;
    private String description;
    private String genre;
    private String name;

    private boolean completed;
    private String thumbnailUrl;

    /**
     * Default empty constructor.
     */
    public DownloadManga() {
    }

    /**
     * Constructor to use when re-constructing the Parcelable object.
     *
     * @param inputParcel The input parcelable.
     */
    private DownloadManga(Parcel inputParcel) {
        _id = inputParcel.readLong();
        if (_id < 0) {
            _id = null;
        }

        source = inputParcel.readString();
        url = inputParcel.readString();

        artist = inputParcel.readString();
        author = inputParcel.readString();
        description = inputParcel.readString();
        genre = inputParcel.readString();
        name = inputParcel.readString();

        completed = inputParcel.readByte() != 0;
        thumbnailUrl = inputParcel.readString();
    }

    public Long get_id() {
        return _id;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public String getThumbnailUrl() {
        return thumbnailUrl;
    }

    public void setThumbnailUrl(String thumbnailUrl) {
        this.thumbnailUrl = thumbnailUrl;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        if (_id != null) {
            dest.writeLong(_id);
        } else {
            dest.writeLong(-1);
        }

        dest.writeString(source);
        dest.writeString(url);
        dest.writeString(artist);
        dest.writeString(author);
        dest.writeString(description);
        dest.writeString(genre);
        dest.writeString(name);
        dest.writeByte((byte) (completed ? 1 : 0));
        dest.writeString(thumbnailUrl);
    }
}
