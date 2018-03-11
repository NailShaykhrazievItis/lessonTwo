package com.itis.android.lessontwo.repository;

import android.support.annotation.NonNull;
import com.itis.android.lessontwo.api.ApiFactory;
import com.itis.android.lessontwo.model.creator.Creator;
import com.itis.android.lessontwo.model.creator.CreatorResponse;
import com.itis.android.lessontwo.model.creator.CreatorResponseData;
import com.itis.android.lessontwo.repository.cache.ErrorReadFromCache;
import com.itis.android.lessontwo.repository.cache.ErrorSingleReadFromCache;
import com.itis.android.lessontwo.repository.cache.RewriteCache;
import com.itis.android.lessontwo.utils.RxUtils;
import io.reactivex.Single;
import java.util.List;

public class CreatorRepositoryImpl implements CreatorRepository {

    @NonNull
    @Override
    public Single<List<Creator>> creators(final Long offset, final Long limit, final String sort) {
        return ApiFactory.getCreatorsService()
                .creators(offset, limit, sort)
                .map(CreatorResponse::getData)
                .map(CreatorResponseData::getResults)
                .flatMap(new RewriteCache<>(Creator.class))
                .onErrorResumeNext(new ErrorReadFromCache<>(Creator.class))
                .compose(RxUtils.asyncSingle());
    }

    @Override
    public Single<Creator> creator(final Long id) {
        return ApiFactory.getCreatorsService()
                .creators(id)
                .map(CreatorResponse::getData)
                .map(CreatorResponseData::getResults)
                .map(c -> c.get(0))
                .onErrorResumeNext(new ErrorSingleReadFromCache<>(Creator.class, id))
                .compose(RxUtils.asyncSingle());
    }
}
