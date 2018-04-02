package com.itis.android.lessontwo.repository.comics;

import android.support.annotation.NonNull;

import com.itis.android.lessontwo.api.ApiFactory;
import com.itis.android.lessontwo.model.comics.Comics;
import com.itis.android.lessontwo.model.comics.ComicsResponse;
import com.itis.android.lessontwo.model.comics.ComicsResponseData;
import com.itis.android.lessontwo.repository.cache.ErrorReadFromCache;
import com.itis.android.lessontwo.repository.cache.ErrorSingleReadFromCache;
import com.itis.android.lessontwo.repository.cache.RewriteCache;
import com.itis.android.lessontwo.utils.RxUtils;

import java.util.List;

import io.reactivex.Single;

/**
 * Created by Aizat on 12.03.2018.
 */

public class ComicsRepositoryImpl implements ComicsRepository{

    @NonNull
    @Override
    public Single<List<Comics>> comics(Long offset, Long limit, String sort) {
        return ApiFactory.getComicsService()
                .comics(offset, limit, sort)
                .map(ComicsResponse::getData)
                .map(ComicsResponseData::getResults)
                .flatMap(new RewriteCache<>(Comics.class))
                .onErrorResumeNext(new ErrorReadFromCache<>(Comics.class))
                .compose(RxUtils.asyncSingle());
    }

    @Override
    public Single<Comics> comics(final Long id) {
        return ApiFactory.getComicsService()
                .comics(id)
                .map(ComicsResponse::getData)
                .map(ComicsResponseData::getResults)
                .map(c -> c.get(0))
                .onErrorResumeNext(new ErrorSingleReadFromCache<>(Comics.class, id))
                .compose(RxUtils.asyncSingle());
    }

    @Override
    public Single<List<Comics>> comicsTest(Long offset, Long limit, String sort) {
        return ApiFactory.getComicsService()
                .comicsTest(offset, limit, sort)
                .map(ComicsResponse::getData)
                .map(ComicsResponseData::getResults)
                .flatMap(new RewriteCache<>(Comics.class))
                .onErrorResumeNext(new ErrorReadFromCache<>(Comics.class))
                .compose(RxUtils.asyncSingle());
    }

}
