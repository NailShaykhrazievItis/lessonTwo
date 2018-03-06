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

    @SerializedName("images")
    private Image image;

    @SerializedName("stories")
    private CreatorStoryList stories;

    public Creator() {
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
        StringBuilder descBuilder = new StringBuilder();
        for (String story: getStories()){
            descBuilder.append(story).append(", ");
        }
        if (descBuilder.length() >= 2)
            description = descBuilder.substring(0, descBuilder.length() - 2);
        else
            description = descBuilder.toString();
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