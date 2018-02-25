package com.itis.android.lessontwo.model.character;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Nail Shaykhraziev on 25.02.2018.
 */

public class CharactersResponseData {

    @SerializedName("offset")
    private String offset;

    @SerializedName("limit")
    private String limit;

    @SerializedName("total")
    private String total;

    @SerializedName("count")
    private String count;

    @SerializedName("results")
    private List<Character> results;

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

    public List<Character> getResults() {
        return results;
    }

    public void setResults(List<Character> results) {
        this.results = results;
    }
}
