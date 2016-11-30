package com.clipsub.csmanga.models;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Model for "DownloadPage" entity.
 */
public class DownloadPage implements Parcelable {

    /**
     * Class name for logging.
     */
    public static final String TAG = DownloadPage.class.getSimpleName();

    /**
     * Parcelable key for Parcelable implementation of this class.
     */
    public static final String PARCELABLE_KEY = TAG + ":" + "ParcelableKey";

    public static final Creator<DownloadPage> CREATOR = new Creator<DownloadPage>() {
        @Override
        public DownloadPage createFromParcel(Parcel source) {
            return new DownloadPage(source);
        }

        @Override
        public DownloadPage[] newArray(int size) {
            return new DownloadPage[size];
        }
    };

    private Long _id;

    private String url;
    private String parentUrl;

    private String directory;
    private String name;
    private int flag;



    /**
     * Public empty constructor for DownloadPage class.
     */
    public DownloadPage() {
    }

    /**
     * Private constructor for parcelable.
     *
     * @param inputParcel Input parcel.
     */
    private DownloadPage(Parcel inputParcel) {
        _id = inputParcel.readLong();
        if (_id < 0) {
            _id = null;
        }

        url = inputParcel.readString();
        parentUrl = inputParcel.readString();

        directory = inputParcel.readString();

        name = inputParcel.readString();

        flag = inputParcel.readInt();
    }

    public Long get_id() {
        return _id;
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

    public String getDirectory() {
        return directory;
    }

    public void setDirectory(String directory) {
        this.directory = directory;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

        dest.writeString(url);
        dest.writeString(parentUrl);

        dest.writeString(directory);

        dest.writeString(name);

        dest.writeInt(flag);
    }
}
