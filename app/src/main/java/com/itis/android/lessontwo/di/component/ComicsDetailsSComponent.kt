package com.itis.android.lessontwo.di.component

import com.itis.android.lessontwo.di.module.ComicsDetailsScModule
import com.itis.android.lessontwo.di.scope.ComicsDetailsScope
import com.itis.android.lessontwo.ui.comics.ComicsActivity
import dagger.Subcomponent

@Subcomponent(modules = [ComicsDetailsScModule::class])
@ComicsDetailsScope
interface ComicsDetailsSComponent {

    fun inject(comicsActivity: ComicsActivity)
}