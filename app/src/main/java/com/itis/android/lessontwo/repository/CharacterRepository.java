package com.itis.android.lessontwo.repository;

import android.support.annotation.NonNull;

import com.itis.android.lessontwo.model.entity.character.Character;

import java.util.List;

import io.reactivex.Single;

/**
 * Created by Ruslan on 09.03.2018.
 */

public interface CharacterRepository {

    @NonNull
    Single<List<Character>> characters(Long offset, Long limit, String sort);

    Single<List<Character>> charactersTest(Long offset, Long limit, String sort);

    Single<Character> character(Long id);
}
