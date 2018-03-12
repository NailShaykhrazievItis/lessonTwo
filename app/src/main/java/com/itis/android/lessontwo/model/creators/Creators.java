package com.itis.android.lessontwo.model.creators;

import com.google.gson.annotations.SerializedName;
import com.itis.android.lessontwo.model.general.Image;
import com.itis.android.lessontwo.model.general.ListItem;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by Aizat on 05.03.2018.
 */

public class Creators extends RealmObject implements ListItem{

    @PrimaryKey
    @SerializedName("id")
    private Long id;

    @SerializedName("fullName")
    private String fullName;

    @SerializedName("thumbnail")
    private Image image;

//    @SerializedName("comics")
//    private List<Comics> comics;

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    @Override
    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    //    public List<Comics> getComics() {
//        return comics;
//    }
//
//    public void setComics(List<Comics> comics) {
//        this.comics = comics;
//    }
}
