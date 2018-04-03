package com.itis.android.lessontwo.repository;

import com.itis.android.lessontwo.api.ApiFactory;
import com.itis.android.lessontwo.model.entity.creators.Creator;
import com.itis.android.lessontwo.model.entity.creators.CreatorsResponse;
import com.itis.android.lessontwo.model.entity.creators.CreatorsResponseData;
import com.itis.android.lessontwo.repository.cache.ErrorReadFromCache;
import com.itis.android.lessontwo.repository.cache.ErrorSingleReadFromCache;
import com.itis.android.lessontwo.repository.cache.RewriteCache;
import com.itis.android.lessontwo.utils.RxUtils;

import java.util.List;

import io.reactivex.Single;

/**
 * Created by valera071998@gmail.com on 11.03.2018.
 */

public class CreatorRepositoryImpl implements CreatorRepository {

    @Override
    public Single<List<Creator>> creators(Long offset, Long limit, String sort) {
        return ApiFactory.getCreatorsService()
                .creators(offset, limit, sort)
                .map(CreatorsResponse::getData)
                .map(CreatorsResponseData::getResults)
                .flatMap(new RewriteCache<>(Creator.class))
                .onErrorResumeNext(new ErrorReadFromCache<>(Creator.class))
                .compose(RxUtils.asyncSingle());
    }

    @Override
    public Single<List<Creator>> creatorsTest(Long offset, Long limit, String sort) {
        return ApiFactory.getCreatorsService()
                .creators(offset, limit, sort)
                .map(CreatorsResponse::getData)
                .map(CreatorsResponseData::getResults)
                .flatMap(new RewriteCache<>(Creator.class))
                .onErrorResumeNext(new ErrorReadFromCache<>(Creator.class))
                .compose(RxUtils.asyncSingle());
    }

    @Override
    public Single<Creator> creator(Long id) {
        return ApiFactory.getCreatorsService()
                .creator(id)
                .map(CreatorsResponse::getData)
                .map(CreatorsResponseData::getResults)
                .map(list -> list.get(0))
                .onErrorResumeNext(new ErrorSingleReadFromCache<>(Creator.class, id))
                .compose(RxUtils.asyncSingle());
    }
}
