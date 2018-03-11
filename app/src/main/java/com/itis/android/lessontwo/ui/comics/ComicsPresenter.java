package com.itis.android.lessontwo.ui.comics;

import com.itis.android.lessontwo.repository.RepositoryProvider;
import com.itis.android.lessontwo.ui.comics.ComicsContract.View;

public class ComicsPresenter implements ComicsContract.Presenter {

    private final View view;

    public ComicsPresenter(View view) {
        this.view = view;
        this.view.setPresenter(this);
    }

    @Override
    public void loadComics(long id) {
        RepositoryProvider.provideComicsRepository()
                .comics(id)
                .subscribe(view::showComics, view::handleError);
    }
}
