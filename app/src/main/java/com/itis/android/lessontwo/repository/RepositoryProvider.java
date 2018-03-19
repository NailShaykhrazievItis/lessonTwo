package com.itis.android.lessontwo.repository;

import android.support.annotation.MainThread;
import android.support.annotation.NonNull;

/**
 * Created by Nail Shaykhraziev on 07.03.2018.
 */
public class RepositoryProvider {

    private static ComicsRepository comicsRepository;

    private static CreatorRepository creatorRepository;

    private static CharacterRepository characterRepository;

    @NonNull
    public static ComicsRepository provideComicsRepository() {
        if (comicsRepository == null) {
            comicsRepository = new ComicsRepositoryImpl();
        }
        return comicsRepository;
    }

    @NonNull
    public static CreatorRepository provideCreatorRepository() {
        if (creatorRepository == null) {
            creatorRepository = new CreatorRepositoryImpl();
        }
        return creatorRepository;
    }

    @NonNull
    public static CharacterRepository provideCharacterRepository() {
        if (characterRepository == null) {
            characterRepository = new CharacterRepositoryImpl();
        }
        return characterRepository;
    }

    @MainThread
    public static void init() {
        comicsRepository = new ComicsRepositoryImpl();
        creatorRepository = new CreatorRepositoryImpl();
    }
}
