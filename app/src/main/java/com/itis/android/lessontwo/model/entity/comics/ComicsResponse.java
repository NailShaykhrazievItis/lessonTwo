package com.itis.android.lessontwo.model.entity.comics;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Nail Shaykhraziev on 25.02.2018.
 */

public class ComicsResponse {

    @SerializedName("data")
    private ComicsResponseData data;

    public ComicsResponseData getData() {
        return data;
    }

    public void setData(ComicsResponseData data) {
        this.data = data;
    }
}
