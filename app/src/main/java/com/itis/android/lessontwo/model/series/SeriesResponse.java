package com.itis.android.lessontwo.model.series;

import com.google.gson.annotations.SerializedName;

/**
 * Created by a9 on 18.03.18.
 */

public class SeriesResponse {

    @SerializedName("data")
    private SeriesResponseData data;

    public SeriesResponseData getData() {
        return data;
    }

    public void setData(SeriesResponseData data) {
        this.data = data;
    }
}
