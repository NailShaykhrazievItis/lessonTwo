package com.itis.android.lessontwo.model.comics;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Nail Shaykhraziev on 25.02.2018.
 */

public class ComicsPrintPrice {

    @SerializedName("price")
    private float price;

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }
}
