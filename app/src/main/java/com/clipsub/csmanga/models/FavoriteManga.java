package com.clipsub.csmanga.models;

import android.os.Parcel;
import android.os.Parcelable;

public class FavoriteManga implements Parcelable {

    public static final String TAG = FavoriteManga.class.getSimpleName();

    public static final String PARCELABLE_KEY = TAG + ":" + "ParcelableKey";

    /**
     * Public CREATOR field that generates instances of Parcelable class from a Parcel.
     */
    public static final Creator<FavoriteManga> CREATOR = new Creator<FavoriteManga>() {
        @Override
        public FavoriteManga createFromParcel(Parcel source) {
            return new FavoriteManga(source);
        }

        @Override
        public FavoriteManga[] newArray(int size) {
            return new FavoriteManga[size];
        }
    };

    private Long _id;

    private String source;
    private String url;

    private String name;
    private String thumbnailUrl;

    public FavoriteManga() {
    }

    private FavoriteManga(Parcel inputParcel) {
        _id = inputParcel.readLong();
        if (_id < 0) {
            _id = null;
        }

        source = inputParcel.readString();
        url = inputParcel.readString();

        name = inputParcel.readString();
        thumbnailUrl = inputParcel.readString();
    }

    public Long get_id() {
        return _id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getThumbnailUrl() {
        return thumbnailUrl;
    }

    public void setThumbnailUrl(String thumbnailUrl) {
        this.thumbnailUrl = thumbnailUrl;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
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
    }
}
