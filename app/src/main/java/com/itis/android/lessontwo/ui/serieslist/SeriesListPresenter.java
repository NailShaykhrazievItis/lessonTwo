package com.itis.android.lessontwo.ui.serieslist;

import android.support.annotation.VisibleForTesting;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.itis.android.lessontwo.model.series.Series;
import com.itis.android.lessontwo.repository.RepositoryProvider;

import static com.itis.android.lessontwo.utils.Constants.PAGE_SIZE;
import static com.itis.android.lessontwo.utils.Constants.ZERO_OFFSET;

/**
 * Created by a9 on 18.03.18.
 */
@InjectViewState
public class SeriesListPresenter extends MvpPresenter<SeriesListView> {

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
        loadSeries();
    }

    void loadNextElements(int page) {
        RepositoryProvider.provideSeriesRepository()
                .series(page * PAGE_SIZE, PAGE_SIZE)
                .doOnSubscribe(getViewState()::showLoading)
                .doAfterTerminate(getViewState()::hideLoading)
                .doAfterTerminate(getViewState()::setNotLoading)
                .subscribe(getViewState()::addMoreItems, getViewState()::handleError);
    }

    void onItemClick(Series series) {
        getViewState().showDetails(series);
    }

    @VisibleForTesting
    void loadSeries() {
        RepositoryProvider.provideSeriesRepository()
                .series(ZERO_OFFSET, PAGE_SIZE)
                .doOnSubscribe(getViewState()::showLoading)
                .doAfterTerminate(getViewState()::hideLoading)
                .subscribe(getViewState()::showItems, getViewState()::handleError);
    }
}
