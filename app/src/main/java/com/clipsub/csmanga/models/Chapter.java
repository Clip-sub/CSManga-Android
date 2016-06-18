package com.clipsub.csmanga.models;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Model for "chapter". Each chapter contains list of images and source url to the corresponding chapter.
 */
public class Chapter implements Parcelable {

    /**
     * Class name for logging.
     */
    public static final String TAG = Chapter.class.getSimpleName();

    /**
     * Parcelable key for Parcelable implementation of this class.
     */
    public static final String PARCELABLE_KEY = TAG + ":" + "ParcelableKey";

    /**
     * Parcelable implementation interface.
     */
    public static final Creator<Chapter> CREATOR = new Creator<Chapter>() {
        @Override
        public Chapter createFromParcel(Parcel source) {
            return new Chapter(source);
        }

        @Override
        public Chapter[] newArray(int size) {
            return new Chapter[size];
        }
    };

    private Long _id;

    private String source;
    private String url;
    private String parentUrl;
    private String parentName;
    private String name;
    private boolean isNew;
    private long date;
    private int number;

    /**
     * Public empty constructor for @Chapter model.
     */
    public Chapter() {
    }

    /**
     * Private constructor for Chapter model.
     *
     * @param inputParcel Input parcel data.
     */
    private Chapter(Parcel inputParcel) {
        _id = inputParcel.readLong();
        if (_id < 0) {
            _id = null;
        }

        source = inputParcel.readString();
        url = inputParcel.readString();
        parentUrl = inputParcel.readString();
        parentName = inputParcel.readString();
        name = inputParcel.readString();
        isNew = inputParcel.readByte() != 0;
        date = inputParcel.readLong();
        number = inputParcel.readInt();
    }

    /**
     * Get chapter's ID.
     *
     * @return ID of the chapter in database.
     */
    public Long get_id() {
        return _id;
    }

    /**
     * Get chapter's source.
     *
     * @return Name of chapter's source. Example: MangaFox.
     */
    public String getSource() {
        return source;
    }

    /**
     * Set chapter's source.
     *
     * @param source Name of the source you want to set to.
     */
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

    public boolean isNew() {
        return isNew;
    }

    public void setNew(boolean aNew) {
        isNew = aNew;
    }

    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
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
        dest.writeByte((byte) (isNew ? 1 : 0));
        dest.writeLong(date);
        dest.writeInt(number);
    }
}
