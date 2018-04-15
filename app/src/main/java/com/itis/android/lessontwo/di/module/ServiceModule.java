package com.itis.android.lessontwo.di.module;

import android.support.annotation.NonNull;

import com.itis.android.lessontwo.BuildConfig;
import com.itis.android.lessontwo.api.CharactersService;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Nail Shaykhraziev on 09.04.2018.
 */
@Module
public class ServiceModule {

//    @Provides
//    @Singleton
//    ComicsService provideComicsService(@NonNull Retrofit retrofit) {
//        return retrofit.create(ComicsService.class);
//    }

    @Provides
    @Singleton
    CharactersService provideCharactersService(@NonNull Retrofit retrofit) {
        return retrofit.create(CharactersService.class);
    }

    @Provides
    @Singleton
    Retrofit provideRetrofit(@NonNull OkHttpClient client) {
        return new Retrofit.Builder()
                .baseUrl(BuildConfig.API_ENDPOINT)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }
}
