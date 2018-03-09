package com.itis.android.lessontwo.model.entity.character;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Nail Shaykhraziev on 25.02.2018.
 */

public class CharactersResponse {

    @SerializedName("data")
    private CharactersResponseData data;

    public CharactersResponseData getData() {
        return data;
    }

    public void setData(CharactersResponseData data) {
        this.data = data;
    }
}
