package com.itis.android.lessontwo.api;

import android.support.annotation.NonNull;

import com.facebook.stetho.okhttp3.StethoInterceptor;

import okhttp3.OkHttpClient;

public class OkHttpProvider {

    private static volatile OkHttpClient sClient;

    private OkHttpProvider() {
    }

    @NonNull
    public static OkHttpClient provideClient() {
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

    public static void recreate() {
        sClient = null;
        sClient = buildClient();
    }

    @NonNull
    private static OkHttpClient buildClient() {
        return new OkHttpClient.Builder()
                .addInterceptor(ApiKeyInterceptor.create())
                .addInterceptor(MockingInterceptor.create())
                .addInterceptor(LoggingInterceptor.create())
                .addInterceptor(new StethoInterceptor())
                .build();

    }
}
