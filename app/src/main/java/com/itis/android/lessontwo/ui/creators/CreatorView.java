package com.itis.android.lessontwo.ui.creators;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;
import com.itis.android.lessontwo.model.creator.Creator;

/**
 * Created by Blaheart on 05.03.2018.
 */
@StateStrategyType(AddToEndSingleStrategy.class)
interface CreatorView extends MvpView {

    void getCreatorId();

    void handleError(Throwable error);

    void setImage(Creator creator);

    void setName(Creator creator);

    void setStories(Creator creator);
}
