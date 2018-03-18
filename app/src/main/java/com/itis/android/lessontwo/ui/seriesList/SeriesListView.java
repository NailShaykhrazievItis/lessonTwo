package com.itis.android.lessontwo.ui.seriesList;

import android.support.annotation.NonNull;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.AddToEndStrategy;
import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;
import com.itis.android.lessontwo.model.series.Series;

import java.util.List;

import io.reactivex.disposables.Disposable;

/**
 * Created by a9 on 18.03.18.
 */

@StateStrategyType(AddToEndSingleStrategy.class)
public interface SeriesListView extends MvpView {

    void showItems(@NonNull List<Series> items);

    @StateStrategyType(AddToEndStrategy.class)
    void addMoreItems(@NonNull List<Series> items);

    void setNotLoading();

    void showLoading(Disposable disposable);

    void hideLoading();

    @StateStrategyType(OneExecutionStateStrategy.class)
    void handleError(Throwable throwable);

    //* Navigation methods*/
    void showDetails(Series item);
}
