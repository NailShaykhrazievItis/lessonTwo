package com.itis.android.lessontwo.repository.characters;

import android.support.annotation.NonNull;
import com.itis.android.lessontwo.api.ApiFactory;
import com.itis.android.lessontwo.model.character.Character;
import com.itis.android.lessontwo.model.character.CharactersResponse;
import com.itis.android.lessontwo.model.character.CharactersResponseData;
import com.itis.android.lessontwo.repository.cache.ErrorReadFromCache;
import com.itis.android.lessontwo.repository.cache.ErrorSingleReadFromCache;
import com.itis.android.lessontwo.repository.cache.RewriteCache;
import com.itis.android.lessontwo.utils.RxUtils;
import io.reactivex.Single;
import java.util.List;

public class CharactersRepositoryImpl implements CharactersRepository {

    @NonNull
    @Override
    public Single<List<Character>> characters(final Long offset, final Long limit) {
        return ApiFactory.getCharactersService()
                .characters(offset, limit)
                .map(CharactersResponse::getData)
                .map(CharactersResponseData::getResults)
                .flatMap(new RewriteCache<>(Character.class))
                .onErrorResumeNext(new ErrorReadFromCache<>(Character.class))
                .compose(RxUtils.asyncSingle());
    }

    @Override
    public Single<List<Character>> charactersTest(Long offset, Long limit) {
        return ApiFactory.getCharactersService()
                .charactersTest(offset, limit)
                .map(CharactersResponse::getData)
                .map(CharactersResponseData::getResults)
                .flatMap(new RewriteCache<>(Character.class))
                .onErrorResumeNext(new ErrorReadFromCache<>(Character.class))
                .compose(RxUtils.asyncSingle());
    }

    @Override
    public Single<Character> characters(final Long id) {
        return ApiFactory.getCharactersService()
                .characters(id)
                .map(CharactersResponse::getData)
                .map(CharactersResponseData::getResults)
                .map(c -> c.get(0))
                .onErrorResumeNext(new ErrorSingleReadFromCache<>(Character.class, id))
                .compose(RxUtils.asyncSingle());
    }
}