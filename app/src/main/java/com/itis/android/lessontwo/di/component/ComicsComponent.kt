package com.itis.android.lessontwo.di.component

import com.itis.android.lessontwo.di.module.DataModule
import com.itis.android.lessontwo.di.module.PresenterModule
import com.itis.android.lessontwo.di.scope.ComicsScope
import com.itis.android.lessontwo.ui.comics.ComicsActivity
import com.itis.android.lessontwo.ui.comicslist.ComicsListActivity
import dagger.Component

@Component(dependencies = [
    ApiComponent::class, AppComponent::class
], modules = [
    PresenterModule::class, DataModule::class
])
@ComicsScope
interface ComicsComponent {

    fun inject(comicsActivity: ComicsActivity)

    fun inject(comicsListActivity: ComicsListActivity)
}
