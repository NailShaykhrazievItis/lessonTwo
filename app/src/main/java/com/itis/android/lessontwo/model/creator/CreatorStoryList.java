package com.itis.android.lessontwo.model.creator;

import com.google.gson.annotations.SerializedName;
import io.realm.RealmList;
import io.realm.RealmObject;
import java.util.List;

public class CreatorStoryList extends RealmObject{

    @SerializedName("items")
    private RealmList<CreatorStory> stories;

    public RealmList<CreatorStory> getStories() {
        return stories;
    }

    public void setStories(final RealmList<CreatorStory> stories) {
        this.stories = stories;
    }
}