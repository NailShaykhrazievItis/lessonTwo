package com.itis.android.lessontwo.ui.comics;

import android.annotation.SuppressLint;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.itis.android.lessontwo.repository.ComicsRepository;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by Nail Shaykhraziev on 12.03.2018.
 */
@Singleton
@InjectViewState
public class ComicsPresenter extends MvpPresenter<ComicsView> {

    private final ComicsRepository repository;

    @Inject
    public ComicsPresenter(ComicsRepository repository) {
        this.repository = repository;
    }

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
        getViewState().getComicsId();
    }

    @SuppressLint("CheckResult")
    public void init(Long id) {
        repository
                .comics(id)
                .subscribe(comics -> {
                    getViewState().setImage(comics);
                    getViewState().setDescription(comics);
                    getViewState().setPrice(comics);
                    getViewState().setPageCount(comics);
                }, getViewState()::handleError);
    }
}
