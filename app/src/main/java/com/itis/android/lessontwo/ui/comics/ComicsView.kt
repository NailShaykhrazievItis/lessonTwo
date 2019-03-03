package com.itis.android.lessontwo.ui.comics

import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy
import com.arellomobile.mvp.viewstate.strategy.AddToEndStrategy
import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType
import com.itis.android.lessontwo.model.comics.Comics

interface ComicsView : MvpView {

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun getComicsId()

    @StateStrategyType(OneExecutionStateStrategy::class)
    fun handleError(error: Throwable)

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun setPageCount(comics: Comics)

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun setPrice(comics: Comics)

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun setDescription(comics: Comics)

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun setImage(comics: Comics)

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun showProgress()

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun hideProgress()
}
