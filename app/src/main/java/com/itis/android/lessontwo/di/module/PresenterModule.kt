package com.itis.android.lessontwo.di.module

import com.itis.android.lessontwo.repository.ComicsRepository
import com.itis.android.lessontwo.ui.comics.ComicsPresenter
import com.itis.android.lessontwo.ui.comicslist.ComicsListPresenter
import dagger.Module
import dagger.Provides

@Module
class PresenterModule {

    @Provides
    fun provideComicsListPresenter(repository: ComicsRepository): ComicsListPresenter =
         ComicsListPresenter(repository)

    @Provides
    fun provideComicsPresenter(repository: ComicsRepository): ComicsPresenter =
            ComicsPresenter(repository)

//    @Provides
//    fun provideComicsPresenter(presenter: ComicsPresenter): ComicsPresenter = presenter
}
