package com.itis.android.lessontwo.ui.comicslist;

import android.support.annotation.VisibleForTesting;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.itis.android.lessontwo.model.entity.comics.Comics;
import com.itis.android.lessontwo.repository.RepositoryProvider;

import static com.itis.android.lessontwo.utils.Constants.DEFAULT_COMICS_SORT;
import static com.itis.android.lessontwo.utils.Constants.PAGE_SIZE;
import static com.itis.android.lessontwo.utils.Constants.ZERO_OFFSET;

/**
 * Created by Nail Shaykhraziev on 26.02.2018.
 */
@InjectViewState
public class ComicsListPresenter extends MvpPresenter<ComicsListView> {

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
        loadComics();
    }

    @VisibleForTesting // TODO check access. By default it is PRIVATE.
    // https://medium.com/modernnerd-code/java-for-humans-encapsulation-access-modifiers-a1ee247acb5e:
    // In Java, by default fields, method, and classes are PACKAGE-PRIVATE
    void loadComics() {
        RepositoryProvider.provideComicsRepository()
                .comics(ZERO_OFFSET, PAGE_SIZE, DEFAULT_COMICS_SORT)
                .doOnSubscribe(getViewState()::showLoading)
                .doAfterTerminate(getViewState()::hideLoading)
                .subscribe(getViewState()::showItems, getViewState()::handleError);
    }

    public void loadNextElements(int page) {
        RepositoryProvider.provideComicsRepository()
                .comics(page * PAGE_SIZE, PAGE_SIZE, DEFAULT_COMICS_SORT)
                .doOnSubscribe(getViewState()::showLoading)
                .doAfterTerminate(getViewState()::hideLoading)
                .doAfterTerminate(getViewState()::setNotLoading)
                .subscribe(getViewState()::addMoreItems, getViewState()::handleError);
    }

    public void onItemClick(Comics comics) {
        getViewState().showDetails(comics);
    }
}
