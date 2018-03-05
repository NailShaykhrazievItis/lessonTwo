package com.itis.android.lessontwo.model.events;

import com.google.gson.annotations.SerializedName;
import com.itis.android.lessontwo.model.general.Image;
import com.itis.android.lessontwo.model.general.ListItem;

import java.util.Date;

/**
 * Created by Aizat on 05.03.2018.
 */

public class Events implements ListItem{
    @SerializedName("id")
    private Long id;

    @SerializedName("title")
    private String title;

    @SerializedName("description")
    private String description;

    @SerializedName("thumbnail")
    private Image image;

    @SerializedName("start")
    private Date start;

    @SerializedName("end")
    private Date end;

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

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

    public Date getStart() {
        return start;
    }

    public void setStart(Date start) {
        this.start = start;
    }

    public Date getEnd() {
        return end;
    }

    public void setEnd(Date end) {
        this.end = end;
    }
}
