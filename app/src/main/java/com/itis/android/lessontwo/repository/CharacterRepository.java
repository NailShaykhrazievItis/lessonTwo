package com.itis.android.lessontwo.repository;

import android.support.annotation.NonNull;

import com.itis.android.lessontwo.model.character.Character;

import java.util.List;

import io.reactivex.Single;

public interface CharacterRepository {

    @NonNull
    Single<List<Character>> characters(Long offset, Long limit);

    Single<Character> character(Long id);

    Single<List<Character>> characterTest(Long offset, Long limit);
}
