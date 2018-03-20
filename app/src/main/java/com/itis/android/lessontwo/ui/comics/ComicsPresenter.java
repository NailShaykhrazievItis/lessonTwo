package com.itis.android.lessontwo.ui.comics;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.itis.android.lessontwo.repository.RepositoryProvider;

@InjectViewState
public class ComicsPresenter extends MvpPresenter<ComicsContract.View> implements ComicsContract.Presenter {

    @Override
    public void loadComics(long id) {
        RepositoryProvider.provideComicsRepository()
                .comics(id)
                .subscribe(getViewState()::showComics, getViewState()::handleError);
    }
}
