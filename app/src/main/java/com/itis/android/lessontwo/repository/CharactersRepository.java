package com.itis.android.lessontwo.repository;

import android.support.annotation.NonNull;
import com.itis.android.lessontwo.model.character.Character;
import io.reactivex.Single;
import java.util.List;

/**
 * Created by Admin on 11.03.2018.
 */

public interface CharactersRepository {

    @NonNull
    Single<List<Character>> characters(Long offset, Long limit);

    Single<Character> characters(Long id);
}
