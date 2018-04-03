package com.itis.android.lessontwo.model.entity.comics;

import com.google.gson.annotations.SerializedName;
import com.itis.android.lessontwo.model.general.Image;
import com.itis.android.lessontwo.model.general.ListItem;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;


/**
 * Created by Nail Shaykhraziev on 25.02.2018.
 */
public class Comics extends RealmObject implements ListItem {

    @PrimaryKey
    @SerializedName("id")
    private Long id;

    @SerializedName("title")
    private String mTitle;

    @SerializedName("pageCount")
    private int pageCount;

    @SerializedName("textObjects")
    private RealmList<ComicsTextObject> textObjects;

    @SerializedName("prices")
    private RealmList<ComicsPrintPrice> prices;

    @SerializedName("images")
    private RealmList<Image> images;

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

    public RealmList<ComicsTextObject> getTextObjects() {
        return textObjects;
    }

    public void setTextObjects(RealmList<ComicsTextObject> textObjects) {
        this.textObjects = textObjects;
    }

    public RealmList<ComicsPrintPrice> getPrices() {
        return prices;
    }

    public void setPrices(RealmList<ComicsPrintPrice> prices) {
        this.prices = prices;
    }

    public RealmList<Image> getImages() {
        return images;
    }

    public void setImages(RealmList<Image> images) {
        this.images = images;
    }

    @Override
    public String getName() {
        return getTitle();
    }

    @Override
    public String getDescription() {
        if (!getTextObjects().isEmpty()) {
            return getTextObjects().get(0).getText();
        }
        return "";
    }

    @Override
    public Image getImage() {
        if (!images.isEmpty()) {
            return images.get(0);
        } else {
            return null;
        }
    }
}
