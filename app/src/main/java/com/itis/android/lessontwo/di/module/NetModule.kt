package com.itis.android.lessontwo.di.module

import com.facebook.stetho.okhttp3.StethoInterceptor
import com.itis.android.lessontwo.api.ApiKeyInterceptor
import com.itis.android.lessontwo.api.LoggingInterceptor
import dagger.Module
import dagger.Provides
import okhttp3.Interceptor
import okhttp3.OkHttpClient
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
    ): OkHttpClient =
            OkHttpClient.Builder()
                    .addInterceptor(apiKeyInterceptor)
                    .addInterceptor(loggingInterceptor)
                    .addInterceptor(stethoInterceptor)
                    .build()


    companion object {
        private const val NAME_API_KEY = "ApiKeyInterceptor"
        private const val NAME_LOGGING = "LoggingInterceptor"
        private const val NAME_STETHO = "StethoInterceptor"
    }
}
