package com.itis.android.lessontwo.model.creator;

import com.google.gson.annotations.SerializedName;
import java.util.List;

public class CreatorResponseData {

    @SerializedName("offset")
    private Long offset;

    @SerializedName("limit")
    private Long limit;

    @SerializedName("total")
    private Long total;

    @SerializedName("count")
    private Long count;

    @SerializedName("results")
    private List<Creator> results;

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

    public List<Creator> getResults() {
        return results;
    }

    public void setResults(List<Creator> results) {
        this.results = results;
    }
}