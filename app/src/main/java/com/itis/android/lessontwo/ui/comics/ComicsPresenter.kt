package com.itis.android.lessontwo.ui.comics

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.itis.android.lessontwo.repository.ComicsRepository
import io.reactivex.rxkotlin.subscribeBy

@InjectViewState
class ComicsPresenter (
        private val comicsRepository: ComicsRepository
) : MvpPresenter<ComicsView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.getComicsId()
    }

    fun init(id: Long) {
       comicsRepository.comics(id)
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
