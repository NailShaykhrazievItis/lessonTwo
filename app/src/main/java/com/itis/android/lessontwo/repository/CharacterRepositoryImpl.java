package com.itis.android.lessontwo.repository;

import android.support.annotation.NonNull;

import com.itis.android.lessontwo.api.ApiFactory;
import com.itis.android.lessontwo.model.entity.character.Character;
import com.itis.android.lessontwo.model.entity.character.CharactersResponse;
import com.itis.android.lessontwo.model.entity.character.CharactersResponseData;
import com.itis.android.lessontwo.repository.cache.ErrorReadFromCache;
import com.itis.android.lessontwo.repository.cache.ErrorSingleReadFromCache;
import com.itis.android.lessontwo.repository.cache.RewriteCache;
import com.itis.android.lessontwo.utils.RxUtils;

import java.util.List;

import io.reactivex.Single;

/**
 * Created by Ruslan on 09.03.2018.
 */

public class CharacterRepositoryImpl implements CharacterRepository {

    @NonNull
    @Override
    public Single<List<Character>> characters(Long offset, Long limit, String sort) {
        return ApiFactory.getCharactersService()
                .characters(offset, limit, sort)
                .map(CharactersResponse::getData)
                .map(CharactersResponseData::getResults)
                .flatMap(new RewriteCache<>(Character.class))
                .onErrorResumeNext(new ErrorReadFromCache<>(Character.class))
                .compose(RxUtils.asyncSingle());
    }

    @Override
    public Single<Character> character(Long id) {
        return ApiFactory.getCharactersService()
                .character(id)
                .map(CharactersResponse::getData)
                .map(CharactersResponseData::getResults)
                .map(c -> c.get(0))
                .onErrorResumeNext(new ErrorSingleReadFromCache<>(Character.class, id))
                .compose(RxUtils.asyncSingle());
    }
}
