package com.itis.android.lessontwo.repository.characters;

import android.support.annotation.NonNull;
import com.itis.android.lessontwo.model.character.Character;
import io.reactivex.Single;
import java.util.List;

/**
 * Created by Tony on 3/18/2018.
 */

public interface CharactersRepository {

    @NonNull
    Single<List<Character>> characters(Long offset, Long limit);

    Single<Character> characters(Long id);

    Single<List<Character>> charactersTest(Long offset, Long limit);
}
