package com.itis.android.lessontwo.di.component

import com.itis.android.lessontwo.di.module.ComicsDetailsScModule
import com.itis.android.lessontwo.di.module.ComicsListScModule
import com.itis.android.lessontwo.di.module.ComicsModule
import com.itis.android.lessontwo.di.scope.ComicsScope
import dagger.Subcomponent

@Subcomponent(modules = [ComicsModule::class])
@ComicsScope
interface ComicsComponent {

    fun plusComicsDetailsSComponent(comicsDetailsModule: ComicsDetailsScModule): ComicsDetailsSComponent

    fun plusComicsListSComponent(comicsListModule: ComicsListScModule): ComicsListSComponent
}