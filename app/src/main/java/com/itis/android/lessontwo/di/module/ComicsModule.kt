package com.itis.android.lessontwo.di.module

import com.itis.android.lessontwo.api.ComicsService
import com.itis.android.lessontwo.di.scope.ComicsScope
import com.itis.android.lessontwo.model.comics.Comics
import com.itis.android.lessontwo.repository.ComicsRepository
import com.itis.android.lessontwo.repository.ComicsRepositoryImpl
import com.itis.android.lessontwo.ui.comics.ComicsPresenter
import com.itis.android.lessontwo.ui.comicslist.ComicsAdapter
import com.itis.android.lessontwo.ui.comicslist.ComicsListPresenter
import dagger.Module
import dagger.Provides

@Module
class ComicsModule {

    @Provides
    @ComicsScope
    fun provideComicsRepository(service: ComicsService): ComicsRepository =
            ComicsRepositoryImpl(service)

    @Provides
    @ComicsScope
    fun provideComicsListPresenter(repository: ComicsRepository): ComicsListPresenter =
            ComicsListPresenter(repository)

    @Provides
    @ComicsScope
    fun provideComicsPresenter(repository: ComicsRepository): ComicsPresenter =
            ComicsPresenter(repository)

    @Provides
    @ComicsScope
    fun provideComicsAdapter(): ComicsAdapter = ComicsAdapter(ArrayList<Comics>())
}
