package com.itis.android.lessontwo.di.component;

import com.itis.android.lessontwo.di.module.ComicsModule;
import com.itis.android.lessontwo.di.scope.ComicsScreenScope;
import com.itis.android.lessontwo.ui.comics.ComicsActivity;
import com.itis.android.lessontwo.ui.comicslist.ComicsListActivity;

import dagger.Component;

/**
 * Created by Nail Shaykhraziev on 09.04.2018.
 */
@ComicsScreenScope
@Component(dependencies = {ApiComponent.class, AppComponent.class},
        modules = {ComicsModule.class})
public interface ComicsComponent {

    void inject(ComicsActivity comicsActivity);

    void inject(ComicsListActivity comicsListActivity);
}
