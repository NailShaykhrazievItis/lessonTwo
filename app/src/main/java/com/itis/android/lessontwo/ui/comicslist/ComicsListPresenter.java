package com.itis.android.lessontwo.ui.comicslist;

import com.arellomobile.mvp.MvpPresenter;
import com.itis.android.lessontwo.model.entity.comics.Comics;
import com.itis.android.lessontwo.repository.RepositoryProvider;

import static com.itis.android.lessontwo.utils.Constants.DEFAULT_COMICS_SORT;
import static com.itis.android.lessontwo.utils.Constants.PAGE_SIZE;
import static com.itis.android.lessontwo.utils.Constants.ZERO_OFFSET;

/**
 * Created by Nail Shaykhraziev on 26.02.2018.
 */

public class ComicsListPresenter extends MvpPresenter<ComicsListView> {

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
        loadComics();
    }

    public void loadComics() {
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
