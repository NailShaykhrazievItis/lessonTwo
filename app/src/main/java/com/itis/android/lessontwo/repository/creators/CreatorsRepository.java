package com.itis.android.lessontwo.repository.creators;

import android.support.annotation.NonNull;

import com.itis.android.lessontwo.model.creators.Creators;

import java.util.List;

import io.reactivex.Single;

/**
 * Created by Aizat on 12.03.2018.
 */

public interface CreatorsRepository {

    @NonNull
    Single<List<Creators>> creators(Long offset, Long limit, String sort);

    Single<Creators> creators(Long id);
}
