package com.gmarat.testapp.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Data {

    @SerializedName("data")
    private final List<DataItem> mDataItems;

    public Data(List<DataItem> dataItems) {
        mDataItems = dataItems;
    }

    public List<DataItem> getDataItems() {
        return mDataItems;
    }
}
