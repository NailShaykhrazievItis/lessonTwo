package com.itis.android.lessontwo.model.stories;

import com.google.gson.annotations.SerializedName;
import com.itis.android.lessontwo.model.character.CharactersResponseData;

/**
 * Created by Aizat on 05.03.2018.
 */

public class StoriesResponse {

    @SerializedName("data")
    private StoriesResponseData data;

    public StoriesResponseData getData() {
        return data;
    }

    public void setData(StoriesResponseData data) {
        this.data = data;
    }
}
