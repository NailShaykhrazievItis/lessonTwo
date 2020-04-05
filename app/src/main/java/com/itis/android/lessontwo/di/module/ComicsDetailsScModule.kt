package com.itis.android.lessontwo.di.module

import com.itis.android.lessontwo.di.scope.ComicsDetailsScope
import com.itis.android.lessontwo.repository.ComicsRepository
import com.itis.android.lessontwo.ui.comics.ComicsPresenter
import dagger.Module
import dagger.Provides

@Module
class ComicsDetailsScModule {

    @Provides
    @ComicsDetailsScope
    fun provideComicsPresenter(repository: ComicsRepository): ComicsPresenter = ComicsPresenter(repository)
}
