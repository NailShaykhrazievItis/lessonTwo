package com.itis.android.lessontwo.di.module;

import android.support.annotation.NonNull;

import com.itis.android.lessontwo.api.ComicsService;
import com.itis.android.lessontwo.di.scope.ComicsScreenScope;
import com.itis.android.lessontwo.repository.ComicsRepository;
import com.itis.android.lessontwo.repository.ComicsRepositoryImpl;
import com.itis.android.lessontwo.ui.comics.ComicsPresenter;
import com.itis.android.lessontwo.ui.comicslist.ComicsAdapter;
import com.itis.android.lessontwo.ui.comicslist.ComicsListPresenter;

import java.util.ArrayList;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Nail Shaykhraziev on 09.04.2018.
 */
@Module
public class ComicsModule {

    @Provides
    @ComicsScreenScope
    ComicsRepository provideComicsRepository(@NonNull ComicsService service) {
        return new ComicsRepositoryImpl(service);
    }

    @Provides
    @ComicsScreenScope
    ComicsPresenter provideComicsPresenter(ComicsPresenter presenter) {
        return presenter;
    }

    @Provides
    @ComicsScreenScope
    ComicsListPresenter provideComicsListPresenter(@NonNull ComicsRepository repository) {
        return new ComicsListPresenter(repository);
    }

    @Provides
    @ComicsScreenScope
    ComicsAdapter provideComicsAdapter() {
        return new ComicsAdapter(new ArrayList<>());
    }
}
