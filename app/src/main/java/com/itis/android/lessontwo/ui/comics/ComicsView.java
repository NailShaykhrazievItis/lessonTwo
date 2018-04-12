package com.itis.android.lessontwo.ui.comics;

import com.arellomobile.mvp.MvpView;

import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;
import com.itis.android.lessontwo.model.comics.Comics;

/**
 * Created by Home on 18.03.2018.
 */

public interface ComicsView extends MvpView{

    void getComicsId();

    void setPageCount(Comics comics);

    void setPrice(Comics comics);

    void setDescription(Comics comics);

    void setImage(Comics comics);

    @StateStrategyType(OneExecutionStateStrategy.class)
    void handleError(Throwable throwable);
}
