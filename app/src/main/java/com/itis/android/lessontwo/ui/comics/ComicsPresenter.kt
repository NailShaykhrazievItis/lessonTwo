package com.itis.android.lessontwo.ui.comics

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.itis.android.lessontwo.repository.RepositoryProvider
import io.reactivex.rxkotlin.subscribeBy

@InjectViewState
class ComicsPresenter : MvpPresenter<ComicsView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.getComicsId()
    }

    fun init(id: Long) {
        RepositoryProvider.provideComicsRepository()
                .comics(id)
                .doOnSubscribe { viewState.showProgress() }
                .doAfterTerminate { viewState.hideProgress() }
                .subscribeBy(onSuccess = { comics ->
                    viewState.setImage(comics)
                    viewState.setDescription(comics)
                    viewState.setPrice(comics)
                    viewState.setPageCount(comics)
                }, onError = { viewState.handleError(it) })
    }
}
