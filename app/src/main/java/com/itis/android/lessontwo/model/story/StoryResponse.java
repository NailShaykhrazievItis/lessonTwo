package com.itis.android.lessontwo.model.story;

import com.google.gson.annotations.SerializedName;

/**
 * Created by User on 16.03.2018.
 */

public class StoryResponse {

    @SerializedName("data")
    private StoryResponseData data;

    public StoryResponseData getData() {
        return data;
    }

    public void setData(StoryResponseData data) {
        this.data = data;
    }
}
