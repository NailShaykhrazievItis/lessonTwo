package com.itis.android.lessontwo.model.creator;

import com.google.gson.annotations.SerializedName;
import java.util.List;

class CreatorStoryList {

    @SerializedName("items")
    private List<Story> stories;

    public List<Story> getStories() {
        return stories;
    }

    public void setStories(final List<Story> stories) {
        this.stories = stories;
    }
}