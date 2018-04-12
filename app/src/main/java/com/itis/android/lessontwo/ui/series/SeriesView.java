package com.itis.android.lessontwo.ui.series;

import com.arellomobile.mvp.MvpView;

import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;
import com.itis.android.lessontwo.model.series.Series;

/**
 * Created by Home on 19.03.2018.
 */

public interface SeriesView extends MvpView{

    void getSeriesId();

    void setDescription(Series series);

    void setImage(Series series);

    @StateStrategyType(OneExecutionStateStrategy.class)
    void handleError(Throwable throwable);
}
