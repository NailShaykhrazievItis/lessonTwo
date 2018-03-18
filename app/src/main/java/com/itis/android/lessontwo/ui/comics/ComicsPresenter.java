package com.itis.android.lessontwo.ui.comics;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.itis.android.lessontwo.repository.RepositoryProvider;
import com.itis.android.lessontwo.ui.comics.ComicsContract.View;

@InjectViewState
public class ComicsPresenter extends MvpPresenter<ComicsContract.View> implements ComicsContract.Presenter {

    @Override
    public void loadComics(long id) {
        RepositoryProvider.provideComicsRepository()
                .comics(id)
                .subscribe(getViewState()::showComics, getViewState()::handleError);
    }
}
