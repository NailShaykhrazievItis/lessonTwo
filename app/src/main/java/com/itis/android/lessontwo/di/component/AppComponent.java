package com.itis.android.lessontwo.di.component;

import android.content.Context;

import com.itis.android.lessontwo.di.module.AppModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by Nail Shaykhraziev on 08.04.2018.
 */
@Singleton
@Component(modules = {
        AppModule.class,
})
public interface AppComponent {

    Context provideApp();
}
