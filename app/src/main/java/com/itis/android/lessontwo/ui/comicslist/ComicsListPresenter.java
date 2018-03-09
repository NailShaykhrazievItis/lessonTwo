package com.itis.android.lessontwo.ui.comicslist;

import static com.itis.android.lessontwo.utils.Constants.DEFAULT_COMICS_SORT;
import static com.itis.android.lessontwo.utils.Constants.PAGE_SIZE;
import static com.itis.android.lessontwo.utils.Constants.ZERO_OFFSET;

import com.itis.android.lessontwo.model.comics.Comics;
import com.itis.android.lessontwo.repository.RepositoryProvider;

/**
 * Created by Nail Shaykhraziev on 26.02.2018.
 */

public class ComicsListPresenter implements ComicsListContract.Presenter {

    private final ComicsListContract.View view;

    public ComicsListPresenter(ComicsListContract.View view) {
        this.view = view;
        this.view.setPresenter(this);
    }

    @Override
    public void loadComics() {
        RepositoryProvider.provideComicsRepository()
                .comics(ZERO_OFFSET, PAGE_SIZE, DEFAULT_COMICS_SORT)
                .doOnSubscribe(view::showLoading)
                .doAfterTerminate(view::hideLoading)
                .subscribe(view::showItems, view::handleError);
    }

    @Override
    public void loadNextElements(int page) {
        RepositoryProvider.provideComicsRepository()
                .comics(page * PAGE_SIZE, PAGE_SIZE, DEFAULT_COMICS_SORT)
                .doOnSubscribe(view::showLoading)
                .doAfterTerminate(view::hideLoading)
                .doAfterTerminate(view::setNotLoading)
                .subscribe(view::addMoreItems, view::handleError);
    }

    @Override
    public void onItemClick(Comics comics) {
        view.showDetails(comics);
    }
}
