package com.itis.android.lessontwo.repository;

import android.support.annotation.NonNull;

import com.itis.android.lessontwo.model.character.Character;

import io.reactivex.Single;

import java.util.List;

/**
 * Created by Home on 12.03.2018.
 */

public interface CharacterRepository {

    @NonNull
    Single<List<Character>> characters(Long offset, Long limit);

    Single<Character> character(Long id);
}
