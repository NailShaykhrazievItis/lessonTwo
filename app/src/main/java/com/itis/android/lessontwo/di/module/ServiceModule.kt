package com.itis.android.lessontwo.di.module

import com.itis.android.lessontwo.api.CharactersService
import com.itis.android.lessontwo.api.ComicsService
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
class ServiceModule {

    @Provides
    @Singleton
    fun provideComicsService(retrofit: Retrofit): ComicsService =
            retrofit.create(ComicsService::class.java)

    @Provides
    @Singleton
    fun provideCharactersService(retrofit: Retrofit): CharactersService =
            retrofit.create(CharactersService::class.java)
}
