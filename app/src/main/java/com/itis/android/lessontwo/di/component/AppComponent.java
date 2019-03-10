package com.itis.android.lessontwo.di.component;

import android.content.Context;

import com.itis.android.lessontwo.api.CharactersService;
import com.itis.android.lessontwo.api.ComicsService;
import com.itis.android.lessontwo.di.module.AppModule;
import com.itis.android.lessontwo.di.module.NetModule;
import com.itis.android.lessontwo.di.module.ServiceModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by Nail Shaykhraziev on 08.04.2018.
 */
@Singleton
@Component(modules = {
        AppModule.class,
        NetModule.class,
        ServiceModule.class
})
public interface AppComponent {

    Context provideApp();

    ComicsService comicsService();

    CharactersService characterService();
}
