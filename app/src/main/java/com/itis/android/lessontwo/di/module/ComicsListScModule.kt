package com.itis.android.lessontwo.di.module

import com.itis.android.lessontwo.di.scope.ComicsListScope
import com.itis.android.lessontwo.model.comics.Comics
import com.itis.android.lessontwo.repository.ComicsRepository
import com.itis.android.lessontwo.ui.comicslist.ComicsAdapter
import com.itis.android.lessontwo.ui.comicslist.ComicsListPresenter
import dagger.Module
import dagger.Provides

@Module
class ComicsListScModule {

    @Provides
    @ComicsListScope
    fun provideComicsListPresenter(repository: ComicsRepository): ComicsListPresenter =
            ComicsListPresenter(repository)

    @Provides
    @ComicsListScope
    fun provideComicsAdapter(): ComicsAdapter = ComicsAdapter(ArrayList<Comics>())
}