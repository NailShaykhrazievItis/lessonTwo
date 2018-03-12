package com.itis.android.lessontwo.model.creators;

import com.google.gson.annotations.SerializedName;


/**
 * Created by Aizat on 05.03.2018.
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
