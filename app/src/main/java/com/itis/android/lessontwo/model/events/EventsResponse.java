package com.itis.android.lessontwo.model.events;

import com.google.gson.annotations.SerializedName;
import com.itis.android.lessontwo.model.character.CharactersResponseData;

/**
 * Created by Aizat on 05.03.2018.
 */

public class EventsResponse {

    @SerializedName("data")
    private EventsResponseData data;

    public EventsResponseData getData() {
        return data;
    }

    public void setData(EventsResponseData data) {
        this.data = data;
    }
}
