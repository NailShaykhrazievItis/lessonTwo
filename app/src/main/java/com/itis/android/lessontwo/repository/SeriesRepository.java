package com.itis.android.lessontwo.repository;

import android.support.annotation.NonNull;

import com.itis.android.lessontwo.model.series.Series;

import java.util.List;

import io.reactivex.Single;

/**
 * Created by a9 on 18.03.18.
 */

public interface SeriesRepository {

    @NonNull
    Single<List<Series>> series(Long offset, Long limit);

    Single<Series> series(Long id);
}
