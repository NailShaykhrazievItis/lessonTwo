package com.itis.android.lessontwo.model.creator;

import com.google.gson.annotations.SerializedName;

public class CreatorResponse {

    @SerializedName("data")
    private CreatorResponseData data;

    public CreatorResponseData getData() {
        return data;
    }

    public void setData(CreatorResponseData data) {
        this.data = data;
    }
}