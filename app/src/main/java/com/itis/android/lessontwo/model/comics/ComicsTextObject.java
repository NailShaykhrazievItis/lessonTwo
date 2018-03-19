package com.itis.android.lessontwo.model.comics;

import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;

/**
 * Created by Nail Shaykhraziev on 25.02.2018.
 */

public class ComicsTextObject extends RealmObject {

    @SerializedName("text")
    private String text;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
