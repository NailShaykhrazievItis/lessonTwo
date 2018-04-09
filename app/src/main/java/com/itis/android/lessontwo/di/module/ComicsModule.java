package com.itis.android.lessontwo.di.module;

import com.itis.android.lessontwo.di.scope.ComicsScreenScope;
import com.itis.android.lessontwo.ui.comics.ComicsPresenter;
import com.itis.android.lessontwo.ui.comicslist.ComicsAdapter;

import java.util.ArrayList;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Nail Shaykhraziev on 09.04.2018.
 */
@Module
@Deprecated
public class ComicsModule {

//    @Provides
//    @Singleton
//    ComicsService provideComicsService(@NonNull Retrofit retrofit) {
//        return retrofit.create(ComicsService.class);
//    }

    @Provides
    @ComicsScreenScope
    ComicsPresenter provideComicsPresenter(ComicsPresenter presenter) {
        return presenter;
    }

    @Provides
    @ComicsScreenScope
    ComicsAdapter provideComicsAdapter() {
        return new ComicsAdapter(new ArrayList<>());
    }
}
