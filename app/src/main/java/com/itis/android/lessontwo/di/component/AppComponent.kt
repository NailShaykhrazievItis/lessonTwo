package com.itis.android.lessontwo.di.component

import android.content.Context
import com.itis.android.lessontwo.App
import com.itis.android.lessontwo.api.CharactersService
import com.itis.android.lessontwo.api.ComicsService
import com.itis.android.lessontwo.di.module.AppModule
import com.itis.android.lessontwo.di.module.ComicsModule
import com.itis.android.lessontwo.di.module.NetModule
import com.itis.android.lessontwo.di.module.ServiceModule
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

/**
 * Created by Nail Shaykhraziev on 08.04.2018.
 */
@Singleton
@Component(modules = [AppModule::class, NetModule::class, ServiceModule::class])
interface AppComponent {

    fun plusComicsComponentBuilder(): ComicsComponent.Builder
    fun provideApp(): Context

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: App): Builder

        fun build(): AppComponent
    }
}
