package com.itis.android.lessontwo.di.component

import com.itis.android.lessontwo.di.module.ComicsModule
import com.itis.android.lessontwo.di.scope.ComicsScope
import com.itis.android.lessontwo.ui.comics.ComicsActivity
import com.itis.android.lessontwo.ui.comicslist.ComicsListActivity
import dagger.Component

@Component(dependencies = [AppComponent::class], modules = [ComicsModule::class])
@ComicsScope
interface ComicsComponent {

    fun inject(comicsActivity: ComicsActivity)

    fun inject(comicsListActivity: ComicsListActivity)
}
