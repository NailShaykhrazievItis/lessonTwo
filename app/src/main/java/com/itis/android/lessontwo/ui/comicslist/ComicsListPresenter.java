package com.itis.android.lessontwo.ui.comicslist;

import android.annotation.SuppressLint;
import android.support.annotation.VisibleForTesting;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.itis.android.lessontwo.model.comics.Comics;
import com.itis.android.lessontwo.repository.ComicsRepository;

import static com.itis.android.lessontwo.utils.Constants.DEFAULT_COMICS_SORT;
import static com.itis.android.lessontwo.utils.Constants.PAGE_SIZE;
import static com.itis.android.lessontwo.utils.Constants.ZERO_OFFSET;

/**
 * Created by Nail Shaykhraziev on 26.02.2018.
 */
@InjectViewState
public class ComicsListPresenter extends MvpPresenter<ComicsListView> {

    private final ComicsRepository repository;

    public ComicsListPresenter(ComicsRepository repository) {
        this.repository = repository;
    }

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
        loadComics();
    }

    @SuppressLint("CheckResult")
    void loadNextElements(int page) {
        repository
                .comics(page * PAGE_SIZE, PAGE_SIZE, DEFAULT_COMICS_SORT)
                .doOnSubscribe(getViewState()::showLoading)
                .doAfterTerminate(getViewState()::hideLoading)
                .doAfterTerminate(getViewState()::setNotLoading)
                .subscribe(getViewState()::addMoreItems, getViewState()::handleError);
    }

    void onItemClick(Comics comics) {
        getViewState().showDetails(comics);
    }

    @SuppressLint("CheckResult")
    @VisibleForTesting
    void loadComics() {
        repository
                .comics(ZERO_OFFSET, PAGE_SIZE, DEFAULT_COMICS_SORT)
                .doOnSubscribe(getViewState()::showLoading)
                .doAfterTerminate(getViewState()::hideLoading)
                .subscribe(getViewState()::showItems, getViewState()::handleError);
    }
}
