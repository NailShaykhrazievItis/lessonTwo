package com.itis.android.lessontwo.ui.comicslist;

import android.support.annotation.NonNull;
import com.arellomobile.mvp.MvpPresenter;
import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.AddToEndStrategy;
import com.arellomobile.mvp.viewstate.strategy.SkipStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;
import com.itis.android.lessontwo.model.comics.Comics;
import com.itis.android.lessontwo.ui.base.BaseView;
import io.reactivex.disposables.Disposable;
import java.util.List;

/**
 * Created by Nail Shaykhraziev on 26.02.2018.
 */

public interface ComicsListContract {

    @StateStrategyType(SkipStrategy.class)
    interface View extends BaseView<Presenter> {

        @StateStrategyType(AddToEndSingleStrategy.class)
        void showItems(@NonNull List<Comics> items);

        void showDetails(Comics item);

        @StateStrategyType(AddToEndStrategy.class)
        void addMoreItems(List<Comics> items);

        void setNotLoading();

        void showLoading(Disposable disposable);

        void hideLoading();
    }

    interface Presenter {

        void loadComics();

        void loadNextElements(int page);

        void onItemClick(Comics comics);
    }
}
