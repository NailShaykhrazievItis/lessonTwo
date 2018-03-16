package com.itis.android.lessontwo.ui.comics;

import com.arellomobile.mvp.MvpPresenter;
import com.itis.android.lessontwo.model.entity.comics.Comics;
import com.itis.android.lessontwo.repository.RepositoryProvider;
import com.itis.android.lessontwo.ui.base.BaseContract;

/**
 * Created by Ruslan on 02.03.2018.
 */

public class ComicsPresenter extends MvpPresenter<ComicsView> {

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
        getViewState().getComicsId();
    }

    public void init(Long id) {
        RepositoryProvider.provideComicsRepository()
                .comics(id)
                .subscribe(comics -> {
                    getViewState().setImage(comics);
                    getViewState().setDescription(comics);
                    getViewState().setPrice(comics);
                    getViewState().setPageCount(comics);
                }, getViewState()::handleError);
    }
}
