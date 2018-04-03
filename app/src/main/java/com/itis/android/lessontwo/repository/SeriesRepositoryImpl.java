package com.itis.android.lessontwo.repository;

import android.support.annotation.NonNull;

import com.itis.android.lessontwo.api.ApiFactory;
import com.itis.android.lessontwo.model.series.Series;
import com.itis.android.lessontwo.model.series.SeriesResponse;
import com.itis.android.lessontwo.model.series.SeriesResponseData;
import com.itis.android.lessontwo.repository.cache.ErrorReadFromCache;
import com.itis.android.lessontwo.repository.cache.ErrorSingleReadFromCache;
import com.itis.android.lessontwo.repository.cache.RewriteCache;
import com.itis.android.lessontwo.utils.RxUtils;

import java.util.List;

import io.reactivex.Single;

/**
 * Created by a9 on 18.03.18.
 */

public class SeriesRepositoryImpl implements SeriesRepository {

    @NonNull
    @Override
    public Single<List<Series>> series(Long offset, Long limit) {
        return ApiFactory.getSeriesService()
                .series(offset, limit)
                .map(SeriesResponse::getData)
                .map(SeriesResponseData::getResults)
                .flatMap(new RewriteCache<>(Series.class))
                .onErrorResumeNext(new ErrorReadFromCache<>(Series.class))
                .compose(RxUtils.asyncSingle());
    }

    @Override
    public Single<List<Series>> seriesTest(Long offset, Long limit) {
        return ApiFactory.getSeriesService()
                .seriesTest(offset, limit)
                .map(SeriesResponse::getData)
                .map(SeriesResponseData::getResults)
                .flatMap(new RewriteCache<>(Series.class))
                .onErrorResumeNext(new ErrorReadFromCache<>(Series.class))
                .compose(RxUtils.asyncSingle());
    }

    @Override
    public Single<Series> series(Long id) {
        return ApiFactory.getSeriesService()
                .series(id)
                .map(SeriesResponse::getData)
                .map(SeriesResponseData::getResults)
                .map(s -> s.get(0))
                .onErrorResumeNext(new ErrorSingleReadFromCache<>(Series.class, id))
                .compose(RxUtils.asyncSingle());
    }
}
