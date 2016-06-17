package com.clipsub.csmanga.models;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Model for "Manga" entity.
 */
public class Manga implements Parcelable {

    /**
     * Class name for logging.
     */
    public static final String TAG = Manga.class.getSimpleName();

    /**
     * Parcelable key for Parcelable implementation of this class.
     */
    public static final String PARCELABLE_KEY = TAG + ":" + "ParcelableKey";

    /**
     * Parcelable implementation interface.
     */
    public static final Creator<Manga> CREATOR = new Creator<Manga>() {
        @Override
        public Manga createFromParcel(Parcel source) {
            return new Manga(source);
        }

        @Override
        public Manga[] newArray(int size) {
            return new Manga[size];
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

    private int rank;
    private Long updated;
    private int updateCount;

    private boolean initialized;

    public Manga() {
    }

    private Manga(Parcel inputParcel) {
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

        initialized = inputParcel.readByte() != 0;
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

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public Long getUpdated() {
        return updated;
    }

    public void setUpdated(Long updated) {
        this.updated = updated;
    }

    public int getUpdateCount() {
        return updateCount;
    }

    public void setUpdateCount(int updateCount) {
        this.updateCount = updateCount;
    }

    public boolean isInitialized() {
        return initialized;
    }

    public void setInitialized(boolean initialized) {
        this.initialized = initialized;
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

        dest.writeInt(rank);
        dest.writeLong(updated);
        dest.writeInt(updateCount);

        dest.writeByte((byte) (initialized ? 1 : 0));
    }
}
