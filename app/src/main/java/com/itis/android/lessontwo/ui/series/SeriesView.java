package com.itis.android.lessontwo.ui.series;

import com.arellomobile.mvp.MvpView;

import com.itis.android.lessontwo.model.series.Series;

/**
 * Created by Home on 19.03.2018.
 */

public interface SeriesView extends MvpView{

    void getSeriesId();

    void setDescription(Series series);

    void setImage(Series series);

    void handleError(Throwable throwable);

}
