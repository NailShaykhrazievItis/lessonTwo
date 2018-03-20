package com.itis.android.lessontwo.model.series;

import com.google.gson.annotations.SerializedName;
import com.itis.android.lessontwo.model.general.Image;
import com.itis.android.lessontwo.model.general.ListItem;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by a9 on 18.03.18.
 */

public class Series extends RealmObject implements ListItem {

    @PrimaryKey
    @SerializedName("id")
    private Long id;

    @SerializedName("title")
    private String title;

    @SerializedName("description")
    private String description;

    @SerializedName("thumbnail")
    private Image image;

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public String getName() {
        return title;
    }

    public void setName(String title){
        this.title = title;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }


}
