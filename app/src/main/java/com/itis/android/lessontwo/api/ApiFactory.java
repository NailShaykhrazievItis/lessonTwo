package com.itis.android.lessontwo.api;

import android.support.annotation.NonNull;

import com.facebook.stetho.okhttp3.StethoInterceptor;
import com.itis.android.lessontwo.BuildConfig;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Nail Shaykhraziev on 24.02.2018.
 */

public final class ApiFactory {

    private static OkHttpClient sClient;

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
    public static CreatorsService getCreatorService() {
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
        sClient = null;
        sClient = getClient();
        comicsService = buildRetrofit().create(ComicsService.class);
        charactersService = buildRetrofit().create(CharactersService.class);
        creatorsService = buildRetrofit().create(CreatorsService.class);
    }

    @NonNull
    private static Retrofit buildRetrofit() {
        return new Retrofit.Builder()
                .baseUrl(BuildConfig.API_ENDPOINT)
                .client(getClient())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

    @NonNull
    private static OkHttpClient getClient() {
        OkHttpClient client = sClient;
        if (client == null) {
            synchronized (ApiFactory.class) {
                client = sClient;
                if (client == null) {
                    client = sClient = buildClient();
                }
            }
        }
        return client;
    }

    @NonNull
    private static OkHttpClient buildClient() {
        return new OkHttpClient.Builder()
                .addInterceptor(ApiKeyInterceptor.create())
                .addInterceptor(LoggingInterceptor.create())
                .addInterceptor(new StethoInterceptor())
                .build();
    }

    public static void setCharactersService(CharactersService charactersService) {
        ApiFactory.charactersService = charactersService;
    }

    public static void setComicsService(ComicsService comicsService) {
        ApiFactory.comicsService = comicsService;
    }
}
