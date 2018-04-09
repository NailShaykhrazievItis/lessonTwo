package com.itis.android.lessontwo.di.module;

import android.support.annotation.NonNull;

import com.itis.android.lessontwo.repository.ComicsRepository;
import com.itis.android.lessontwo.ui.comics.ComicsPresenter;
import com.itis.android.lessontwo.ui.comicslist.ComicsListPresenter;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Nail Shaykhraziev on 08.04.2018.
 */
@Module
public class PresenterModule {

    @Provides
    @Singleton
    ComicsListPresenter provideComicsListPresenter(@NonNull ComicsRepository repository) {
        return new ComicsListPresenter(repository);
    }

    @Provides
    ComicsPresenter provideComicsPresenter(ComicsPresenter presenter) {
        return presenter;
    }
}
