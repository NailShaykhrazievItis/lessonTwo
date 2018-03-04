package com.itis.android.lessontwo.model.creator;

import com.google.gson.annotations.SerializedName;

public class CreatorResponse {

    @SerializedName("data")
    private CreatorResponse data;

    public CreatorResponse getData() {
        return data;
    }

    public void setData(CreatorResponse data) {
        this.data = data;
    }
}