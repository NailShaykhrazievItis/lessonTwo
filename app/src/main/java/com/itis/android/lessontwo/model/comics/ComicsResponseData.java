package com.itis.android.lessontwo.model.comics;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Nail Shaykhraziev on 25.02.2018.
 */

public class ComicsResponseData {

    @SerializedName("offset")
    private Long offset;

    @SerializedName("limit")
    private Long limit;

    @SerializedName("total")
    private Long total;

    @SerializedName("count")
    private Long count;

    @SerializedName("results")
    private List<Comics> results;

    public Long getOffset() {
        return offset;
    }

    public void setOffset(Long offset) {
        this.offset = offset;
    }

    public Long getLimit() {
        return limit;
    }

    public void setLimit(Long limit) {
        this.limit = limit;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }

    public List<Comics> getResults() {
        return results;
    }

    public void setResults(List<Comics> results) {
        this.results = results;
    }
}
