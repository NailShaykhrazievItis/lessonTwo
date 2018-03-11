package com.itis.android.lessontwo.repository;

import android.support.annotation.NonNull;

import com.itis.android.lessontwo.model.entity.comics.Comics;

import java.util.List;

import io.reactivex.Single;

/**
 * Created by Ruslan on 09.03.2018.
 */

public interface ComicsRepository {

    @NonNull
    Single<List<Comics>> comics(Long offset, Long limit, String sort);

    Single<Comics> comics(Long id);
}
