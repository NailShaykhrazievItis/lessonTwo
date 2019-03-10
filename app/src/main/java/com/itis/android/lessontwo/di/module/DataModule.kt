package com.itis.android.lessontwo.di.module

import com.itis.android.lessontwo.api.ComicsService
import com.itis.android.lessontwo.repository.ComicsRepository
import com.itis.android.lessontwo.repository.ComicsRepositoryImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DataModule {

    @Provides
    @Singleton
    fun provideComicsRepository(service: ComicsService): ComicsRepository =
         ComicsRepositoryImpl(service)

    //    @Binds
    //    @Singleton
    //    public abstract ComicsRepositoryImpl newsRepository(ComicsRepositoryImpl repo);
}
