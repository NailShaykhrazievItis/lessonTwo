package com.itis.android.lessontwo.api;

import com.itis.android.lessontwo.model.series.SeriesResponse;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by a9 on 18.03.18.
 */

public interface SeriesService {

    @GET("series")
    Single<SeriesResponse> series(@Query("offset") Long offset, @Query("limit") Long limit);

    //как-то не очень

    @GET("series_test")
    Single<SeriesResponse> seriesTest(@Query("offset") Long offset, @Query("limit") Long limit);

    @GET("series/{seriesId}")
    Single<SeriesResponse> series(@Path("seriesId") Long id);
}
