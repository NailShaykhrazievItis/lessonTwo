package com.itis.android.lessontwo.model.creator;

import com.google.gson.annotations.SerializedName;
import com.itis.android.lessontwo.model.general.Image;
import com.itis.android.lessontwo.model.general.ListItem;
import java.util.ArrayList;
import java.util.List;

public class Creator implements ListItem {

    @SerializedName("id")
    private Long id;

    @SerializedName("fullName")
    private String name;

    private String description;

    @SerializedName("thumbnail")
    private Image image;

    @SerializedName("stories")
    private CreatorStoryList stories;

    public Creator() {
//        description = "";
//        for (String story: getStories()){
//            description += story + " ";
//        }
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public Image getImage() {
        return image;
    }

    public List<String> getStories() {
        List<String> storyNames = new ArrayList<>();
        for (CreatorStory story: stories.getStories()) {
            storyNames.add(story.getName());
        }
        return storyNames;
    }
}