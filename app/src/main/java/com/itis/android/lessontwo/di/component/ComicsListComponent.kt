package com.itis.android.lessontwo.di.component

import com.itis.android.lessontwo.di.module.ComicsListScModule
import com.itis.android.lessontwo.di.scope.ComicsListScope
import com.itis.android.lessontwo.ui.comicslist.ComicsListActivity
import dagger.Subcomponent

@Subcomponent(modules = [ComicsListScModule::class])
@ComicsListScope
interface ComicsListComponent {

    fun inject(comicsListActivity: ComicsListActivity)

    @Subcomponent.Builder
    interface Builder {

        fun comicsListModule(comicsModule: ComicsListScModule): Builder

        fun build(): ComicsListComponent
    }
}
