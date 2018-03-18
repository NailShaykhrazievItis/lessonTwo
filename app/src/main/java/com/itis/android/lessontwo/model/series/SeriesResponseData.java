package com.itis.android.lessontwo.model.series;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by a9 on 18.03.18.
 */

public class SeriesResponseData {

    @SerializedName("offset")
    private String offset;

    @SerializedName("limit")
    private String limit;

    @SerializedName("total")
    private String total;

    @SerializedName("count")
    private String count;

    @SerializedName("results")
    private List<Series> results;

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

    public List<Series> getResults() {
        return results;
    }

    public void setResults(List<Series> results) {
        this.results = results;
    }
}
