package com.itis.android.lessontwo.api;

import android.support.annotation.NonNull;

import com.facebook.stetho.okhttp3.StethoInterceptor;
import com.itis.android.lessontwo.BuildConfig;
import com.itis.android.lessontwo.api.services.CharactersService;
import com.itis.android.lessontwo.api.services.ComicsService;
import com.itis.android.lessontwo.api.services.StoriesService;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Nail Shaykhraziev on 24.02.2018.
 */

public final class ApiFactory {

    private static volatile ComicsService comicsService;
    private static volatile CharactersService charactersService;
    private static volatile StoriesService storiesService;

    private ApiFactory() {
    }

    @NonNull
    public static ComicsService getComicsService() {
        ComicsService service = comicsService;
        if (service == null) {
            synchronized (ApiFactory.class) {
                service = comicsService;
                if (service == null) {
                    service = comicsService = buildRetrofit().create(ComicsService.class);
                }
            }
        }
        return service;
    }

    @NonNull
    public static CharactersService getCharactersService() {
        CharactersService service = charactersService;
        if (service == null) {
            synchronized (ApiFactory.class) {
                service = charactersService;
                if (service == null) {
                    service = charactersService = buildRetrofit().create(CharactersService.class);
                }
            }
        }
        return service;
    }

    @NonNull
    public static StoriesService getStoriesService() {
        StoriesService service = storiesService;
        if (service == null) {
            synchronized (ApiFactory.class) {
                service = storiesService;
                if (service == null) {
                    service = storiesService = buildRetrofit().create(StoriesService.class);
                }
            }
        }
        return service;
    }

    public static void recreate() {
        OkHttpProvider.recreate();
        comicsService = buildRetrofit().create(ComicsService.class);
        charactersService = buildRetrofit().create(CharactersService.class);
        storiesService = buildRetrofit().create(StoriesService.class);
    }

    public static void setComicsService(ComicsService comicsService) {
        ApiFactory.comicsService = comicsService;
    }

    public static void setCharactersService(CharactersService charactersService) {
        ApiFactory.charactersService = charactersService;
    }

    public static void setStoriesService(StoriesService storiesService) {
        ApiFactory.storiesService = storiesService;
    }

    @NonNull
    private static Retrofit buildRetrofit() {
        return new Retrofit.Builder()
                .baseUrl(BuildConfig.API_ENDPOINT)
                .client(OkHttpProvider.provideClient())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }
}
