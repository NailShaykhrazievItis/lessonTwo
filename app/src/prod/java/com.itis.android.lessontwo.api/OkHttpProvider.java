package com.itis.android.lessontwo.api;


import android.support.annotation.NonNull;

import okhttp3.OkHttpClient;

/**
 * Created by Nail Shaykhraziev on 25.03.2018.
 */
public final class OkHttpProvider {

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
                .addInterceptor(LoggingInterceptor.create())
                .build();
    }
}