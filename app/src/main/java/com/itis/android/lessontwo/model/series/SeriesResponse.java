package com.itis.android.lessontwo.model.series;

import com.google.gson.annotations.SerializedName;
import com.itis.android.lessontwo.model.character.CharactersResponseData;

/**
 * Created by Aizat on 05.03.2018.
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
