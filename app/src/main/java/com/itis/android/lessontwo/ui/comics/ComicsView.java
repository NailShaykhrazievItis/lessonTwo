package com.itis.android.lessontwo.ui.comics;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;
import com.itis.android.lessontwo.model.comics.Comics;

/**
 * Created by User on 18.03.2018.
 */
@StateStrategyType(AddToEndSingleStrategy.class)
public interface ComicsView extends MvpView {

    void getComicsId();

    void handleError(Throwable error);

    void setPageCount(Comics comics);

    void setPrice(Comics comics);

    void setDescription(Comics comics);

    void setImage(Comics comics);
}
