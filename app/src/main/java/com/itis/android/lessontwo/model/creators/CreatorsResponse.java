package com.itis.android.lessontwo.model.creators;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by valera071998@gamil.com on 02.03.2018.
 */

public class CreatorsResponse {

    @SerializedName("data")
    @Expose
    private CreatorsResponseData data;

    public CreatorsResponseData getData() {
        return data;
    }

    public void setData(CreatorsResponseData data) {
        this.data = data;
    }
}