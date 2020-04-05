package com.itis.android.lessontwo.di.component

import com.itis.android.lessontwo.di.module.ComicsModule
import com.itis.android.lessontwo.di.scope.ComicsScope
import dagger.Subcomponent

@Subcomponent(modules = [ComicsModule::class])
@ComicsScope
interface ComicsComponent {

    fun plusComicsDetailsComponentBuilder(): ComicsDetailsComponent.Builder

    fun plusComicsListComponentBuilder(): ComicsListComponent.Builder

    @Subcomponent.Builder
    interface Builder {

        fun comicsModule(comicsModule: ComicsModule): Builder

        fun build(): ComicsComponent
    }
}
