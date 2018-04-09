package com.itis.android.lessontwo.di;

import com.facebook.stetho.okhttp3.StethoInterceptor;
import com.itis.android.lessontwo.api.ApiKeyInterceptor;
import com.itis.android.lessontwo.api.LoggingInterceptor;
import com.itis.android.lessontwo.api.MockingInterceptor;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;

/**
 * Created by Nail Shaykhraziev on 09.04.2018.
 */
@Module
public class ClientModule {

    @Provides
    @Singleton
    OkHttpClient provideOkHttpClient() {
        return new OkHttpClient.Builder()
                .addInterceptor(ApiKeyInterceptor.create())
                .addInterceptor(LoggingInterceptor.create())
                .addInterceptor(MockingInterceptor.create())
                .addInterceptor(new StethoInterceptor())
                .build();
    }
}
