package com.itis.android.lessontwo.repository;

import android.support.annotation.NonNull;

import com.itis.android.lessontwo.api.ComicsService;
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
 * Created by Nail Shaykhraziev on 07.03.2018.
 */
public class ComicsRepositoryImpl implements ComicsRepository {

    private ComicsService comicsService;

    public ComicsRepositoryImpl(ComicsService comicsService) {
        this.comicsService = comicsService;
    }

    @NonNull
    @Override
    public Single<List<Comics>> comics(Long offset, Long limit, String sort) {
        return comicsService
                .comics(offset, limit, sort)
                .map(ComicsResponse::getData)
                .map(ComicsResponseData::getResults)
                .flatMap(new RewriteCache<>(Comics.class))
                .onErrorResumeNext(new ErrorReadFromCache<>(Comics.class))
                .compose(RxUtils.asyncSingle());
    }

    @Override
    public Single<List<Comics>> comicsTest(Long offset, Long limit, String sort) {
        return comicsService
                .comicsTest(offset, limit, sort)
                .map(ComicsResponse::getData)
                .map(ComicsResponseData::getResults)
                .flatMap(new RewriteCache<>(Comics.class))
                .onErrorResumeNext(new ErrorReadFromCache<>(Comics.class))
                .compose(RxUtils.asyncSingle());
    }

    @Override
    public Single<Comics> comics(final Long id) {
        return comicsService
                .comics(id)
                .map(ComicsResponse::getData)
                .map(ComicsResponseData::getResults)
                .map(c -> c.get(0))
                .onErrorResumeNext(new ErrorSingleReadFromCache<>(Comics.class, id))
                .compose(RxUtils.asyncSingle());
    }
}
