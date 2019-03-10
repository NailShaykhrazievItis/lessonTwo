package com.itis.android.lessontwo.di.module

import com.itis.android.lessontwo.BuildConfig
import com.itis.android.lessontwo.api.CharactersService
import com.itis.android.lessontwo.api.ComicsService
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class ServiceModule {

    @Provides
    @Singleton
    fun provideComicsService(retrofit: Retrofit): ComicsService =
            retrofit.create(ComicsService::class.java)

    @Provides
    @Singleton
    fun provideCharactersService(retrofit: Retrofit): CharactersService =
            retrofit.create(CharactersService::class.java)

    @Provides
    @Singleton
    fun provideRetrofit(client: OkHttpClient): Retrofit =
            Retrofit.Builder()
                    .baseUrl(BuildConfig.API_ENDPOINT)
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build()
}
