package com.itis.android.lessontwo.model.comics;

import com.google.gson.annotations.SerializedName;
import com.itis.android.lessontwo.model.general.Image;
import com.itis.android.lessontwo.model.general.ListItem;

import java.util.List;

/**
 * Created by Nail Shaykhraziev on 25.02.2018.
 */

public class Comics implements ListItem{

    @SerializedName("id")
    private Long id;

    @SerializedName("title")
    private String mTitle;

    @SerializedName("pageCount")
    private int pageCount;

    @SerializedName("textObjects")
    private List<ComicsTextObject> textObjects;

    @SerializedName("prices")
    private List<ComicsPrintPrice> prices;

    @SerializedName("images")
    private List<Image> images;

    public Comics() {
    }

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public int getPageCount() {
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    public List<ComicsTextObject> getTextObjects() {
        return textObjects;
    }

    public void setTextObjects(List<ComicsTextObject> textObjects) {
        this.textObjects = textObjects;
    }

    public List<ComicsPrintPrice> getPrices() {
        return prices;
    }

    public void setPrices(List<ComicsPrintPrice> prices) {
        this.prices = prices;
    }

    public List<Image> getImages() {
        return images;
    }

    public void setImages(List<Image> images) {
        this.images = images;
    }

    @Override
    public String getName() {
        return getTitle();
    }

    @Override
    public String getDescription() {
        if (!getTextObjects().isEmpty()){
            return getTextObjects().get(0).getText();
        }
        return "";
    }

    @Override
    public Image getImage() {
        if (!images.isEmpty()){
            return images.get(0);
        } else {
            return null;
        }
    }
}
