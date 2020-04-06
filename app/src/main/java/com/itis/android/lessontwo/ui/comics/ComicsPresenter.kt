package com.itis.android.lessontwo.ui.comics

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.itis.android.lessontwo.di.scope.ComicsDetailsScope
import com.itis.android.lessontwo.repository.ComicsRepository
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.rxkotlin.subscribeBy
import javax.inject.Inject

@InjectViewState
@ComicsDetailsScope
class ComicsPresenter @Inject constructor(
        private val comicsRepository: ComicsRepository
) : MvpPresenter<ComicsView>() {

    private val disposables = CompositeDisposable()

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
                .addTo(disposables)
    }
}
