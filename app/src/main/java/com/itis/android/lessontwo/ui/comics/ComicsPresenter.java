package com.itis.android.lessontwo.ui.comics;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.itis.android.lessontwo.repository.RepositoryProvider;

/**
 * Created by Tony on 3/5/2018.
 */
@InjectViewState
public class ComicsPresenter extends MvpPresenter<ComicsView> {

    @Override
    public void onFirstViewAttach() {
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
