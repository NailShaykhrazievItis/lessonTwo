package com.itis.android.lessontwo.di.module

import com.facebook.stetho.okhttp3.StethoInterceptor
import com.itis.android.lessontwo.BuildConfig
import com.itis.android.lessontwo.api.ApiKeyInterceptor
import com.itis.android.lessontwo.api.LoggingInterceptor
import dagger.Module
import dagger.Provides
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton

@Module
class NetModule {

    @Provides
    @Singleton
    @Named(NAME_API_KEY)
    fun provideApiKeyInterceptor(): Interceptor = ApiKeyInterceptor.create()

    @Provides
    @Singleton
    @Named(NAME_LOGGING)
    fun provideLoggingInterceptor(): Interceptor = LoggingInterceptor.create()

    @Provides
    @Singleton
    @Named(NAME_STETHO)
    fun provideStethoInterceptor(): Interceptor = StethoInterceptor()

    @Provides
    @Singleton
    fun provideOkHttpClient(
            @Named(NAME_API_KEY) apiKeyInterceptor: Interceptor,
            @Named(NAME_LOGGING) loggingInterceptor: Interceptor,
            @Named(NAME_STETHO) stethoInterceptor: Interceptor
    ): OkHttpClient = OkHttpClient.Builder()
            .addInterceptor(apiKeyInterceptor)
            .addInterceptor(loggingInterceptor)
            .addInterceptor(stethoInterceptor)
            .build()

    @Provides
    @Singleton
    @Named(NAME_BASE_URL)
    fun provideBaseUrlString(): String = BuildConfig.API_ENDPOINT

    @Provides
    @Singleton
    fun provideGsonConverterFactory(): GsonConverterFactory = GsonConverterFactory.create()

    @Provides
    @Singleton
    fun provideRxJava2CallAdapterFactory(): RxJava2CallAdapterFactory = RxJava2CallAdapterFactory.create()

    @Provides
    @Singleton
    fun provideRetrofit(
            client: OkHttpClient,
            converterFactory: GsonConverterFactory,
            callAdapterFactory: RxJava2CallAdapterFactory,
            @Named(NAME_BASE_URL) baseUrl: String
    ): Retrofit = Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(client)
            .addConverterFactory(converterFactory)
            .addCallAdapterFactory(callAdapterFactory)
            .build()

    companion object {
        private const val NAME_API_KEY = "ApiKeyInterceptor"
        private const val NAME_LOGGING = "LoggingInterceptor"
        private const val NAME_STETHO = "StethoInterceptor"

        private const val NAME_BASE_URL = "BaseURL"
    }
}
