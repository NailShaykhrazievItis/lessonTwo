package com.itis.android.lessontwo.model.creator;

import com.google.gson.annotations.SerializedName;

public class CreatorStory {

    @SerializedName("name")
    private String name;

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }
}