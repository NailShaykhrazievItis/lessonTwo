package com.itis.android.lessontwo.ui.creators;

import android.support.annotation.NonNull;

import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;
import com.itis.android.lessontwo.model.creator.Creator;
import com.itis.android.lessontwo.ui.base.BaseView;

/**
 * Created by Blaheart on 05.03.2018.
 */

public interface CreatorContract {

    @StateStrategyType(AddToEndSingleStrategy.class)
    interface View extends BaseView<Presenter> {

        void getCreatorId();

        void setImage(Creator creator);

        void setName(Creator creator);

        void setStories(Creator creator);
    }

    interface Presenter {

        void init(long id);
    }
}
