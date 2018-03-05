package com.itis.android.lessontwo.model.creator;

import com.google.gson.annotations.SerializedName;
import java.util.List;

public class CreatorStoryList {

    @SerializedName("items")
    private List<CreatorStory> stories;

    public List<CreatorStory> getStories() {
        return stories;
    }

    public void setStories(final List<CreatorStory> stories) {
        this.stories = stories;
    }
}