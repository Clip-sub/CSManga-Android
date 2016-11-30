package com.clipsub.csmanga.sources;

import android.os.Parcel;
import android.os.Parcelable;

public class UpdatePageMarker implements Parcelable {

    public static final String TAG = UpdatePageMarker.class.getSimpleName();

    public static final String PARCELABLE_KEY = TAG + ":" + "ParcelableKey";
    public static final Creator<UpdatePageMarker> CREATOR = new Creator<UpdatePageMarker>() {
        @Override
        public UpdatePageMarker createFromParcel(Parcel source) {
            return new UpdatePageMarker(source);
        }

        @Override
        public UpdatePageMarker[] newArray(int size) {
            return new UpdatePageMarker[size];
        }
    };

    private String mNextPageUrl;
    private int mLastMangaPosition;

    public UpdatePageMarker(String nextPageUrl, int lastMangaPosition) {
        mNextPageUrl = nextPageUrl;
        mLastMangaPosition = lastMangaPosition;
    }

    private UpdatePageMarker(Parcel inputParcel) {
        mNextPageUrl = inputParcel.readString();
        mLastMangaPosition = inputParcel.readInt();
    }

    public int getLastMangaPosition() {
        return mLastMangaPosition;
    }

    public void setLastMangaPosition(int mLastMangaPosition) {
        this.mLastMangaPosition = mLastMangaPosition;
    }

    public String getNextPageUrl() {
        return mNextPageUrl;
    }

    public void setNextPageUrl(String mNextPageUrl) {
        this.mNextPageUrl = mNextPageUrl;
    }

    public void appendUpdateMarker(UpdatePageMarker newUpdatePageMarker) {
        this.mNextPageUrl = newUpdatePageMarker.mNextPageUrl;
        this.mLastMangaPosition += newUpdatePageMarker.mLastMangaPosition;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(mNextPageUrl);
        dest.writeInt(mLastMangaPosition);
    }
}
