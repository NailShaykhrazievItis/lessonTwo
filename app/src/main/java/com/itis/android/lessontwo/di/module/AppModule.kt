package com.itis.android.lessontwo.di.module

import android.content.Context
import com.itis.android.lessontwo.App
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule(private val app: App) {

    @Provides
    @Singleton
    fun provideApp(): Context = app
}
