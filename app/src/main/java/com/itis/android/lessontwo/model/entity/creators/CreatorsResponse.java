package com.itis.android.lessontwo.model.entity.creators;

import com.google.gson.annotations.SerializedName;

/**
 * Created by valera071998@gamil.com on 02.03.2018.
 */

public class CreatorsResponse {

    @SerializedName("data")
    private CreatorsResponseData data;

    public CreatorsResponseData getData() {
        return data;
    }

    public void setData(CreatorsResponseData data) {
        this.data = data;
    }
}
