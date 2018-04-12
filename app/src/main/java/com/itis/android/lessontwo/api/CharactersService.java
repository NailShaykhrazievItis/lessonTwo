package com.itis.android.lessontwo.api;

import com.itis.android.lessontwo.model.character.CharactersResponse;
import com.itis.android.lessontwo.model.comics.ComicsResponse;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by Nail Shaykhraziev on 25.02.2018.
 */

public interface CharactersService {

    @GET("characters")
    Single<CharactersResponse> characters(@Query("offset") Long offset, @Query("limit") Long limit);

    //как-то не очень
    @GET("characters_test")
    Single<CharactersResponse> charactersTest(@Query("offset") Long offset, @Query("limit") Long limit);

    @GET("characters/{characterId}")
    Single<CharactersResponse> character(@Path("characterId") Long id);

    @GET("characters/{characterId}/comics")
    Single<ComicsResponse> comicsByCharacter(@Path("characterId") Long id);
}
