package com.itis.android.lessontwo.ui.series;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import com.itis.android.lessontwo.repository.RepositoryProvider;

/**
 * Created by Home on 19.03.2018.
 */
@InjectViewState
public class SeriesPresenter extends MvpPresenter<SeriesView>{

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
        getViewState().getSeriesId();
    }

    public void init(long id){
        RepositoryProvider.provideSeriesRepository()
                .series(id)
                .subscribe(series -> {
                    getViewState().setDescription(series);
                    getViewState().setImage(series);
                },getViewState()::handleError);
    }
}
