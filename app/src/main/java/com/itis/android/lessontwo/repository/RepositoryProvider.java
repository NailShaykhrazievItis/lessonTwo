package com.itis.android.lessontwo.repository;

import android.support.annotation.MainThread;
import android.support.annotation.NonNull;

/**
 * Created by Nail Shaykhraziev on 07.03.2018.
 */
public class RepositoryProvider {

    private static ComicsRepository comicsRepository;
    private static CharactersRepository charactersRepository;
    private static StoriesRepository storiesRepository;

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

    @NonNull
    public static StoriesRepository provideStoriesRepostitory() {
        if (storiesRepository == null) {
            storiesRepository = new StoriesRepositoryImpl();
        }
        return storiesRepository;
    }

    public static void setComicsRepository(ComicsRepository repository) {
        comicsRepository = repository;
    }

    public static void setCharactersRepository(CharactersRepository repository) {
        charactersRepository = repository;
    }

    public static void setStoriesRepository(StoriesRepository repository) {
        storiesRepository = repository;
    }

    @MainThread
    public static void init() {
        comicsRepository = new ComicsRepositoryImpl();
        charactersRepository = new CharactersRepositoryImpl();
        storiesRepository = new StoriesRepositoryImpl();
    }
}
