package com.itis.android.lessontwo.ui.comics;

import android.support.annotation.NonNull;
import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;
import com.itis.android.lessontwo.model.comics.Comics;
import com.itis.android.lessontwo.ui.base.BaseView;

public interface ComicsContract {

    @StateStrategyType(AddToEndSingleStrategy.class)
    interface View extends BaseView<Presenter> {

        void showComics(@NonNull Comics comics);
    }

    interface Presenter {

        void loadComics(long id);
    }
}
