package com.itis.android.lessontwo.api;

import com.itis.android.lessontwo.model.entity.comics.ComicsResponse;
import com.itis.android.lessontwo.model.entity.creators.CreatorsResponse;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by valera071998@gmail.com on 02.03.2018.
 */

public interface CreatorsService {

    @GET("creators")
    Single<CreatorsResponse> creators(@Query("offset") Long offset, @Query("limit") Long limit,
                                      @Query("orderBy") String orderBy);

    @GET("creators/{creatorId}")
    Single<CreatorsResponse> creator(@Path("creatorId") Long id);

    @GET("creators/{creatorId}/comics")
    Single<ComicsResponse> comics(@Path("creatorId") Long id);
}
