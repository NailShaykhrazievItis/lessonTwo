package com.itis.android.lessontwo.ui.comics;


import com.itis.android.lessontwo.repository.RepositoryProvider;


/**
 * Created by Tony on 3/5/2018.
 */

public class ComicsPresenter implements ComicsContract.Presenter {

    private final ComicsContract.View view;

    public ComicsPresenter(ComicsContract.View view) {
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
