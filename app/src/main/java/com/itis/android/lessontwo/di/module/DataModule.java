package com.itis.android.lessontwo.di.module;

import android.support.annotation.NonNull;

import com.itis.android.lessontwo.api.ComicsService;
import com.itis.android.lessontwo.repository.ComicsRepository;
import com.itis.android.lessontwo.repository.ComicsRepositoryImpl;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Nail Shaykhraziev on 08.04.2018.
 */
@Module
public class DataModule {

    @Provides
    @Singleton
    ComicsRepository provideComicsRepository(@NonNull ComicsService service) {
        return new ComicsRepositoryImpl(service);
    }

//    @Binds
//    @Singleton
//    public abstract ComicsRepositoryImpl newsRepository(ComicsRepositoryImpl repo);
}
