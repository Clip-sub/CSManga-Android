package com.clipsub.csmanga.model.database;

import android.os.Parcel;
import android.os.Parcelable;

public class RecentChapter implements Parcelable {

    public static final String TAG = RecentChapter.class.getSimpleName();

    public static final String PARCELABLE_KEY = TAG + ":" + "ParcelableKey";

    public static final Creator<RecentChapter> CREATOR = new Creator<RecentChapter>() {
        @Override
        public RecentChapter createFromParcel(Parcel source) {
            return new RecentChapter(source);
        }

        @Override
        public RecentChapter[] newArray(int size) {
            return new RecentChapter[size];
        }
    };

    private Long _id;

    private String source;
    private String url;
    private String parentUrl;

    private String name;
    private String thumbnailUrl;

    private Long date;
    private int pageNumber;

    private boolean offline;

    public RecentChapter() {
    }

    private RecentChapter(Parcel inputParcel) {
        _id = inputParcel.readLong();
        if (_id < 0) {
            _id = null;
        }

        source = inputParcel.readString();
        url = inputParcel.readString();
        parentUrl = inputParcel.readString();

        name = inputParcel.readString();
        thumbnailUrl = inputParcel.readString();

        date = inputParcel.readLong();
        pageNumber = inputParcel.readInt();
        offline = inputParcel.readByte() != 0;
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

    public String getParentUrl() {
        return parentUrl;
    }

    public void setParentUrl(String parentUrl) {
        this.parentUrl = parentUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getThumbnailUrl() {
        return thumbnailUrl;
    }

    public void setThumbnailUrl(String thumbnailUrl) {
        this.thumbnailUrl = thumbnailUrl;
    }

    public Long getDate() {
        return date;
    }

    public void setDate(Long date) {
        this.date = date;
    }

    public int getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    public boolean isOffline() {
        return offline;
    }

    public void setOffline(boolean offline) {
        this.offline = offline;
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
        dest.writeString(parentUrl);

        dest.writeString(name);
        dest.writeString(thumbnailUrl);

        dest.writeLong(date);
        dest.writeInt(pageNumber);

        dest.writeByte((byte) (offline ? 1 : 0));
    }
}
