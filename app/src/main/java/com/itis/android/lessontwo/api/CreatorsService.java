package com.itis.android.lessontwo.api;

import com.itis.android.lessontwo.model.creator.CreatorResponse;
import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface CreatorsService {

    @GET("creators")
    Single<CreatorResponse> creators(@Query("offset") Long offset, @Query("limit") Long limit);

    @GET("creators_test")
    Single<CreatorResponse> creatorsTest(@Query("offset") Long offset, @Query("limit") Long limit);

    @GET("creators/{creatorId}")
    Single<CreatorResponse> creators(@Path("creatorId") Long id);
}