package com.itis.android.lessontwo.model.stories;

import com.google.gson.annotations.SerializedName;
import com.itis.android.lessontwo.model.character.Character;

import java.util.List;

/**
 * Created by Aizat on 05.03.2018.
 */

class StoriesResponseData {

    @SerializedName("offset")
    private String offset;

    @SerializedName("limit")
    private String limit;

    @SerializedName("total")
    private String total;

    @SerializedName("count")
    private String count;

    @SerializedName("results")
    private List<Stories> results;

    public String getOffset() {
        return offset;
    }

    public void setOffset(String offset) {
        this.offset = offset;
    }

    public String getLimit() {
        return limit;
    }

    public void setLimit(String limit) {
        this.limit = limit;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public List<Stories> getResults() {
        return results;
    }

    public void setResults(List<Stories> results) {
        this.results = results;
    }
}
