package com.itis.android.lessontwo.repository;

import android.support.annotation.NonNull;

import com.itis.android.lessontwo.api.ApiFactory;
import com.itis.android.lessontwo.model.story.Story;
import com.itis.android.lessontwo.model.story.StoryResponse;
import com.itis.android.lessontwo.model.story.StoryResponseData;
import com.itis.android.lessontwo.repository.cache.ErrorReadFromCache;
import com.itis.android.lessontwo.repository.cache.ErrorSingleReadFromCache;
import com.itis.android.lessontwo.repository.cache.RewriteCache;
import com.itis.android.lessontwo.utils.RxUtils;

import java.util.List;

import io.reactivex.Single;

/**
 * Created by User on 16.03.2018.
 */

public class StoriesRepositoryImpl implements StoriesRepository {


    @NonNull
    @Override
    public Single<List<Story>> stories(Long offset, Long limit) {
        return ApiFactory.getStoriesService()
                .stories(offset, limit)
                .map(StoryResponse::getData)
                .map(StoryResponseData::getResults)
                .flatMap(new RewriteCache<>(Story.class))
                .onErrorResumeNext(new ErrorReadFromCache<>(Story.class))
                .compose(RxUtils.asyncSingle());
    }

    @Override
    public Single<Story> stories(Long id) {
        return ApiFactory.getStoriesService()
                .stories(id)
                .map(StoryResponse::getData)
                .map(StoryResponseData::getResults)
                .map(c -> c.get(0))
                .onErrorResumeNext(new ErrorSingleReadFromCache<>(Story.class, id))
                .compose(RxUtils.asyncSingle());
    }
}
