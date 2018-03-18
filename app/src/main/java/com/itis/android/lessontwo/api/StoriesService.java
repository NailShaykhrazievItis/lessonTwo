package com.itis.android.lessontwo.api;

import com.itis.android.lessontwo.model.story.StoryResponse;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by User on 16.03.2018.
 */

public interface StoriesService {

    @GET("stories")
    Single<StoryResponse> stories(@Query("offset") Long offset, @Query("limit") Long limit);

    @GET("stories/{storyId}")
    Single<StoryResponse> stories(@Path("storyId") Long id);
}
