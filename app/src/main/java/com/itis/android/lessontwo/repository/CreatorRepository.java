package com.itis.android.lessontwo.repository;

import com.itis.android.lessontwo.model.entity.character.Character;
import com.itis.android.lessontwo.model.entity.creators.Creator;

import java.util.List;

import io.reactivex.Single;
import io.reactivex.annotations.NonNull;

/**
 * Created by valera071998@gmail.com on 11.03.2018.
 */

public interface CreatorRepository {

    @NonNull
    Single<List<Creator>> creators(Long offset, Long limit, String sort);

    Single<List<Creator>> creatorsTest(Long offset, Long limit, String sort);

    Single<Creator> creator(Long id);
}