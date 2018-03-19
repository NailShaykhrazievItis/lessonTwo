package com.itis.android.lessontwo.repository.creators;

import android.support.annotation.NonNull;

import com.itis.android.lessontwo.api.ApiFactory;
import com.itis.android.lessontwo.model.creators.Creators;
import com.itis.android.lessontwo.model.creators.CreatorsResponse;
import com.itis.android.lessontwo.model.creators.CreatorsResponseData;
import com.itis.android.lessontwo.repository.cache.ErrorReadFromCache;
import com.itis.android.lessontwo.repository.cache.ErrorSingleReadFromCache;
import com.itis.android.lessontwo.repository.cache.RewriteCache;
import com.itis.android.lessontwo.utils.RxUtils;

import java.util.List;

import io.reactivex.Single;

/**
 * Created by Aizat on 12.03.2018.
 */

public class CreatorsRepositoryImpl implements CreatorsRepository {

    @NonNull
    @Override
    public Single<List<Creators>> creators(Long offset, Long limit, String sort) {
        return ApiFactory.getCreatorService()
                .creators(offset, limit, sort)
                .map(CreatorsResponse::getData)
                .map(CreatorsResponseData::getResults)
                .flatMap(new RewriteCache<>(Creators.class))
                .onErrorResumeNext(new ErrorReadFromCache<>(Creators.class))
                .compose(RxUtils.asyncSingle());
    }

    @Override
    public Single<Creators> creators(Long id) {
        return ApiFactory.getCreatorService()
                .creators(id)
                .map(CreatorsResponse::getData)
                .map(CreatorsResponseData::getResults)
                .map(c -> c.get(0))
                .onErrorResumeNext(new ErrorSingleReadFromCache<>(Creators.class, id))
                .compose(RxUtils.asyncSingle());
    }
}
