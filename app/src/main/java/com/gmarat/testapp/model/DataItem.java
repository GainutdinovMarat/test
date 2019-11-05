package com.gmarat.testapp.model;

import com.google.gson.annotations.SerializedName;

public class DataItem {

    @SerializedName("url")
    private final String mUrl;
    @SerializedName("title")
    private final String mTitle;

    public DataItem(String url, String title) {
        mUrl = url;
        mTitle = title;
    }

    public String getUrl() {
        return mUrl;
    }

    public String getTitle() {
        return mTitle;
    }
}
