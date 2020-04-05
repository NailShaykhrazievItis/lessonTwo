package com.itis.android.lessontwo.di

import com.itis.android.lessontwo.App
import com.itis.android.lessontwo.di.component.*
import com.itis.android.lessontwo.di.module.ComicsDetailsScModule
import com.itis.android.lessontwo.di.module.ComicsListScModule
import com.itis.android.lessontwo.di.module.ComicsModule

object Injector {

    lateinit var appComponent: AppComponent
    private var comicsComponent: ComicsComponent? = null
    private var comicsDetailsComponent: ComicsDetailsComponent? = null
    private var comicsListComponent: ComicsListComponent? = null

    fun init(app: App) {
        appComponent = DaggerAppComponent.builder()
                .application(app)
                .build()
    }

    fun plusComicsComponent(): ComicsComponent = comicsComponent ?: appComponent.plusComicsComponentBuilder()
            .comicsModule(ComicsModule())
            .build().also {
                comicsComponent = it
            }

    fun clearComicsComponent() {
        comicsComponent = null
    }

    fun plusComicsDetailsComponent(): ComicsDetailsComponent = comicsDetailsComponent ?: plusComicsComponent()
            .plusComicsDetailsComponentBuilder()
            .comicsDetailModule(ComicsDetailsScModule())
            .build().also {
                comicsDetailsComponent = it
            }

    fun clearComicsDetailsComponent() {
        comicsDetailsComponent = null
    }

    fun plusComicsListComponent(): ComicsListComponent = comicsListComponent ?: plusComicsComponent()
            .plusComicsListComponentBuilder()
            .comicsListModule(ComicsListScModule())
            .build().also {
                comicsListComponent = it
            }

    fun clearComicsListComponent() {
        comicsListComponent = null
    }
}
