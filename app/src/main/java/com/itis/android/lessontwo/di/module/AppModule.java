package com.itis.android.lessontwo.di.module;

import android.content.Context;

import com.itis.android.lessontwo.App;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Nail Shaykhraziev on 09.04.2018.
 */
@Module
public class AppModule {

    private App app;

    public AppModule(App app) {
        this.app = app;
    }

    @Provides
    @Singleton
    Context provideApp() {
        return app;
    }
}
