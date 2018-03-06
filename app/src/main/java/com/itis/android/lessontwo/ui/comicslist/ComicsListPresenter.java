package com.itis.android.lessontwo.ui.comicslist;

import static com.itis.android.lessontwo.utils.Constants.DEFAULT_COMICS_SORT;
import static com.itis.android.lessontwo.utils.Constants.PAGE_SIZE;
import static com.itis.android.lessontwo.utils.Constants.ZERO_OFFSET;

import com.itis.android.lessontwo.api.ApiFactory;
import com.itis.android.lessontwo.model.comics.Comics;
import com.itis.android.lessontwo.model.comics.ComicsResponse;
import com.itis.android.lessontwo.model.comics.ComicsResponseData;
import com.itis.android.lessontwo.repository.ErrorReadFromCache;
import com.itis.android.lessontwo.repository.RewriteCache;
import com.itis.android.lessontwo.utils.RxUtils;

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
        ApiFactory.getComicsService()
                .comics(ZERO_OFFSET, PAGE_SIZE, DEFAULT_COMICS_SORT)
                .map(ComicsResponse::getData)
                .map(ComicsResponseData::getResults)
                .flatMap(new RewriteCache<>(Comics.class))
                .onErrorResumeNext(new ErrorReadFromCache<>(Comics.class))
                .compose(RxUtils.async())
                .subscribe(view::showItems, view::handleError);
    }

    @Override
    public void loadNextElements(int page) {
        ApiFactory.getComicsService()
                .comics(page * PAGE_SIZE, PAGE_SIZE, DEFAULT_COMICS_SORT)
                .map(ComicsResponse::getData)
                .map(ComicsResponseData::getResults)
                .doOnTerminate(view::setNotLoading)
                .compose(RxUtils.async())
                .subscribe(view::addMoreItems, view::handleError);
    }

    @Override
    public void onItemClick(Comics comics) {
        view.showDetails(comics);
    }
}
