package com.clipsub.csmanga.utils.wrappers;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

public class SearchCatalogueWrapper implements Parcelable {
    public static final String TAG = SearchCatalogueWrapper.class.getSimpleName();

    public static final String PARCELABLE_KEY = TAG + ":" + "ParcelableKey";

    public static final Creator<SearchCatalogueWrapper> CREATOR = new Creator<SearchCatalogueWrapper>() {
        @Override
        public SearchCatalogueWrapper createFromParcel(Parcel source) {
            return new SearchCatalogueWrapper(source);
        }

        @Override
        public SearchCatalogueWrapper[] newArray(int size) {
            return new SearchCatalogueWrapper[size];
        }
    };

    /**
     * Public default constructor.
     */
    public SearchCatalogueWrapper() {
    }

    public SearchCatalogueWrapper(Parcel inputParcel) {
        mNameArgs = inputParcel.readString();
        mStatusArgs = inputParcel.readString();
        mOrderByArgs = inputParcel.readString();

        mGenresArgs = new ArrayList<String>();
        inputParcel.readStringList(mGenresArgs);
    }

    private String mNameArgs;
    private String mStatusArgs;
    private String mOrderByArgs;
    private List<String> mGenresArgs;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(mNameArgs);
        dest.writeString(mStatusArgs);
        dest.writeString(mOrderByArgs);
        dest.writeStringList(mGenresArgs);
    }

    /**
     * Get the genres argument data.
     *
     * @return Genres argument data as list of strings.
     */
    public List<String> getGenresArgs() {
        return mGenresArgs;
    }

    public void setGenresArgs(List<String> mGenresArgs) {
        this.mGenresArgs = mGenresArgs;
    }

    public String getNameArgs() {
        return mNameArgs;
    }

    public void setNameArgs(String mNameArgs) {
        this.mNameArgs = mNameArgs;
    }

    public String getOrderByArgs() {
        return mOrderByArgs;
    }

    public void setOrderByArgs(String mOrderByArgs) {
        this.mOrderByArgs = mOrderByArgs;
    }

    public String getStatusArgs() {
        return mStatusArgs;
    }

    public void setStatusArgs(String mStatusArgs) {
        this.mStatusArgs = mStatusArgs;
    }
}
