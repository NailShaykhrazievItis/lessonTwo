package com.itis.android.lessontwo.repository;

import android.support.annotation.MainThread;
import android.support.annotation.NonNull;

import com.itis.android.lessontwo.repository.comics.ComicsRepository;
import com.itis.android.lessontwo.repository.comics.ComicsRepositoryImpl;
import com.itis.android.lessontwo.repository.creators.CreatorsRepository;
import com.itis.android.lessontwo.repository.creators.CreatorsRepositoryImpl;

/**
 * Created by Aizat on 12.03.2018.
 */

public class RepositoryProvider {

    private static CreatorsRepository creatorsRepository;

    private static ComicsRepository comicsRepository;

    @NonNull
    public static CreatorsRepository provideCreatorsRepository() {
        if(creatorsRepository == null) {
            creatorsRepository = new CreatorsRepositoryImpl();
        }
        return creatorsRepository;
    }

    @NonNull
    public static ComicsRepository provideComicsRepository() {
        if (comicsRepository == null) {
            comicsRepository = new ComicsRepositoryImpl();
        }
        return comicsRepository;
    }

    @MainThread
    public static void init() {
        creatorsRepository = new CreatorsRepositoryImpl();
        comicsRepository = new ComicsRepositoryImpl();
    }
}
