package com.itis.android.lessontwo.di.component;

import android.content.Context;

import com.itis.android.lessontwo.di.ClientModule;
import com.itis.android.lessontwo.di.module.AppModule;
import com.itis.android.lessontwo.di.module.DataModule;
import com.itis.android.lessontwo.di.module.PresenterModule;
import com.itis.android.lessontwo.di.module.ServiceModule;
import com.itis.android.lessontwo.ui.comics.ComicsActivity;
import com.itis.android.lessontwo.ui.comicslist.ComicsListActivity;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by Nail Shaykhraziev on 08.04.2018.
 */
@Singleton
@Component(modules = {
        DataModule.class,
        PresenterModule.class,
        AppModule.class,
        ClientModule.class,
        ServiceModule.class
})
public interface AppComponent {

    void inject(ComicsActivity comicsActivity);

    void inject(ComicsListActivity comicsListActivity);

    Context provideApp();
}
