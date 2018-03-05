package com.itis.android.lessontwo.api;


import com.itis.android.lessontwo.model.creators.CreatorsResponse;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by Tony on 3/5/2018.
 */

public interface CreatorsService {
    @GET("creators")
    Observable<CreatorsResponse> creators(@Query("offset") Long offset, @Query("limit") Long limit,
                                          @Query("orderBy") String orderBy);

    @GET("creators/{creatorId}")
    Observable<CreatorsResponse> creators(@Path("creatorId") Long id);

    @GET("creators/{creatorId}/comics")
    Observable<CreatorsResponse> comicsByCreator(@Path("creatorId") Long id);
}
