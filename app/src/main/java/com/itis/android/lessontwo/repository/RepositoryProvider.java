package com.itis.android.lessontwo.repository;

import android.support.annotation.MainThread;
import android.support.annotation.NonNull;

/**
 * Created by Nail Shaykhraziev on 07.03.2018.
 */
public class RepositoryProvider {

    private static ComicsRepository comicsRepository;
    private static CharactersRepository charactersRepository;

    @NonNull
    public static ComicsRepository provideComicsRepository() {
        if (comicsRepository == null) {
            comicsRepository = new ComicsRepositoryImpl();
        }
        return comicsRepository;
    }

    @NonNull
    public static CharactersRepository provideCharactersRepostitory() {
        if (charactersRepository == null) {
            charactersRepository = new CharactersRepositoryImpl();
        }
        return charactersRepository;
    }

    @MainThread
    public static void init() {
        comicsRepository = new ComicsRepositoryImpl();
        charactersRepository = new CharactersRepositoryImpl();
    }
}
