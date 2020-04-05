package com.itis.android.lessontwo.di.component

import com.itis.android.lessontwo.di.module.ComicsDetailsScModule
import com.itis.android.lessontwo.di.scope.ComicsDetailsScope
import com.itis.android.lessontwo.ui.comics.ComicsActivity
import dagger.Subcomponent

@Subcomponent(modules = [ComicsDetailsScModule::class])
@ComicsDetailsScope
interface ComicsDetailsComponent {

    fun inject(comicsActivity: ComicsActivity)

    @Subcomponent.Builder
    interface Builder {

        fun comicsDetailModule(comicsModule: ComicsDetailsScModule): Builder

        fun build(): ComicsDetailsComponent
    }
}
