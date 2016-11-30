package com.clipsub.csmanga.models;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Model for each chapter download.
 */
public class DownloadChapter implements Parcelable {

    public static final String TAG = DownloadChapter.class.getSimpleName();

    public static final String PARCELABLE_KEY = TAG + ":" + "ParcelableKey";

    public static final Creator<DownloadChapter> CREATOR = new Creator<DownloadChapter>() {
        @Override
        public DownloadChapter createFromParcel(Parcel source) {
            return new DownloadChapter(source);
        }

        @Override
        public DownloadChapter[] newArray(int size) {
            return new DownloadChapter[size];
        }
    };

    private Long _id;

    private String source;
    private String url;
    private String parentUrl;
    private String parentName;
    private String name;
    private String directory;

    private int currentPage;
    private int totalPages;
    private int flag;

    public DownloadChapter() {
    }

    private DownloadChapter(Parcel inputParcel) {
        _id = inputParcel.readLong();
        if (_id < 0) {
            _id = null;
        }

        source = inputParcel.readString();
        url = inputParcel.readString();
        parentUrl = inputParcel.readString();
        parentName = inputParcel.readString();
        name = inputParcel.readString();
        directory = inputParcel.readString();

        currentPage = inputParcel.readInt();
        totalPages = inputParcel.readInt();
        flag = inputParcel.readInt();
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

    public String getParentName() {
        return parentName;
    }

    public void setParentName(String parentName) {
        this.parentName = parentName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDirectory() {
        return directory;
    }

    public void setDirectory(String directory) {
        this.directory = directory;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
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
        dest.writeString(parentName);
        dest.writeString(name);
        dest.writeString(directory);

        dest.writeInt(currentPage);
        dest.writeInt(totalPages);
        dest.writeInt(flag);
    }
}
