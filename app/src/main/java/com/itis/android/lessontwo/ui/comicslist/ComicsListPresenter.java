package com.itis.android.lessontwo.ui.comicslist;

import com.itis.android.lessontwo.model.entity.comics.Comics;
import com.itis.android.lessontwo.repository.RepositoryProvider;
import com.itis.android.lessontwo.ui.base.BaseListContract;

import static com.itis.android.lessontwo.utils.Constants.DEFAULT_COMICS_SORT;
import static com.itis.android.lessontwo.utils.Constants.PAGE_SIZE;
import static com.itis.android.lessontwo.utils.Constants.ZERO_OFFSET;

/**
 * Created by Nail Shaykhraziev on 26.02.2018.
 */

public class ComicsListPresenter implements BaseListContract.Presenter<Comics> {

    private final BaseListContract.View<Comics> view;

    public ComicsListPresenter(BaseListContract.View<Comics> view) {
        this.view = view;
        this.view.setPresenter(this);
    }

    @Override
    public void load() {
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
