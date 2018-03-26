package com.itis.android.lessontwo.repository;

import android.support.annotation.MainThread;
import android.support.annotation.NonNull;

/**
 * Created by Nail Shaykhraziev on 07.03.2018.
 */
public class RepositoryProvider {

    private static ComicsRepository comicsRepository;
    private static CharacterRepository characterRepository;
    private static SeriesRepository seriesRepository;

    @NonNull
    public static ComicsRepository provideComicsRepository() {
        if (comicsRepository == null) {
            comicsRepository = new ComicsRepositoryImpl();
        }
        return comicsRepository;
    }

    public static void setComicsRepository(ComicsRepository comicsRepository) {
        RepositoryProvider.comicsRepository = comicsRepository;
    }

    @NonNull
    public static CharacterRepository provideCharacterRepository() {
        if (characterRepository == null) {
            characterRepository = new CharacterRepositoryImpl();
        }
        return characterRepository;
    }

    public static void setCharactersRepository(CharacterRepository charactersRepository) {
        RepositoryProvider.characterRepository = charactersRepository;
    }

    @NonNull
    public static SeriesRepository provideSeriesRepository() {
        if (seriesRepository == null) {
            seriesRepository = new SeriesRepositoryImpl();
        }
        return seriesRepository;
    }

    public static void setSeriesRepository(SeriesRepository seriesRepository) {
        RepositoryProvider.seriesRepository = seriesRepository;
    }

    @MainThread
    public static void init() {
        comicsRepository = new ComicsRepositoryImpl();
        characterRepository = new CharacterRepositoryImpl();
        seriesRepository = new SeriesRepositoryImpl();
    }
}
