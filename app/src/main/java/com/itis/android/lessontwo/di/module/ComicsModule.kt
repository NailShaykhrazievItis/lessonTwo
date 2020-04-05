package com.itis.android.lessontwo.di.module

import com.itis.android.lessontwo.api.ComicsService
import com.itis.android.lessontwo.di.scope.ComicsScope
import com.itis.android.lessontwo.repository.ComicsRepository
import com.itis.android.lessontwo.repository.ComicsRepositoryImpl
import dagger.Module
import dagger.Provides

@Module
class ComicsModule {

    @Provides
    @ComicsScope
    fun provideComicsRepository(service: ComicsService): ComicsRepository = ComicsRepositoryImpl(service)
}
