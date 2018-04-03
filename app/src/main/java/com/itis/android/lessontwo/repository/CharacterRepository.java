package com.itis.android.lessontwo.repository;

import android.support.annotation.NonNull;

import com.itis.android.lessontwo.model.character.Character;

import java.util.List;

import io.reactivex.Single;

/**
 * Created by Home on 12.03.2018.
 */

public interface CharacterRepository {

    @NonNull
    Single<List<Character>> characters(Long offset, Long limit);

    Single<List<Character>> charactersTest(Long offset, Long limit);

    Single<Character> character(Long id);
}
