package com.itis.android.lessontwo.api;

import android.support.annotation.NonNull;

import com.itis.android.lessontwo.BuildConfig;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Nail Shaykhraziev on 24.02.2018.
 */

public final class ApiFactory {

    private static volatile ComicsService comicsService;
    private static volatile CharactersService charactersService;
    private static volatile CreatorsService creatorsService;

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
    public static CreatorsService getCreatorsService() {
        CreatorsService service = creatorsService;
        if (service == null) {
            synchronized (ApiFactory.class) {
                service = creatorsService;
                if (service == null) {
                    service = creatorsService = buildRetrofit().create(CreatorsService.class);
                }
            }
        }
        return service;
    }

    public static void recreate() {
        OkHttpProvider.recreate();
        comicsService = buildRetrofit().create(ComicsService.class);
        charactersService = buildRetrofit().create(CharactersService.class);
        creatorsService = buildRetrofit().create(CreatorsService.class);
    }

    public static void setComicsService(ComicsService comicsService) {
        ApiFactory.comicsService = comicsService;
    }

    public static void setCreatorsService(final CreatorsService creatorsService) {
        ApiFactory.creatorsService = creatorsService;
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
