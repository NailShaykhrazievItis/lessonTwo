package com.itis.android.lessontwo.model.entity.comics;

import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;

/**
 * Created by Nail Shaykhraziev on 25.02.2018.
 */
public class ComicsPrintPrice extends RealmObject{

    @SerializedName("price")
    private float price;

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }
}
