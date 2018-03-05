package com.itis.android.lessontwo.api;

import com.itis.android.lessontwo.model.creator.CreatorResponse;
import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface CreatorsService {

    @GET("creators")
    Observable<CreatorResponse> creators(@Query("offset") Long offset, @Query("limit") Long limit,
            @Query("orderBy") String orderBy);

    @GET("creators/{creatorId}")
    Observable<CreatorResponse> creators(@Path("creatorId") Long id);
}