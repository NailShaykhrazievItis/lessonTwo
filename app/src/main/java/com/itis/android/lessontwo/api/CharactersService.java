package com.itis.android.lessontwo.api;

import com.itis.android.lessontwo.model.character.CharactersResponse;
import com.itis.android.lessontwo.model.comics.ComicsResponse;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by Nail Shaykhraziev on 25.02.2018.
 */

public interface CharactersService {

    @GET("characters")
    Observable<CharactersResponse> characters(@Query("offset") Long offset, @Query("limit") Long limit);

    @GET("characters/{characterId}")
    Observable<CharactersResponse> character(@Path("characterId") Long id);

    @GET("characters/{characterId/comics}")
    Observable<ComicsResponse> comicsByCharacter(@Path("characterId") Long id);
}
