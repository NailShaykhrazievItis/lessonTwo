package com.itis.android.lessontwo.ui.creatorslist;

import android.support.annotation.NonNull;
import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndStrategy;
import com.arellomobile.mvp.viewstate.strategy.SkipStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;
import com.itis.android.lessontwo.model.creator.Creator;
import io.reactivex.disposables.Disposable;
import java.util.List;

/**
 * Created by Bulat Murtazin on 05.03.2018.
 * KPFU ITIS 11-601
 */

@StateStrategyType(SkipStrategy.class)
interface CreatorListView extends MvpView {

    @StateStrategyType(AddToEndStrategy.class)
    void addMoreItems(List<Creator> items);

    void hideLoading();

    void handleError(Throwable error);

    void setNotLoading();

    void showDetails(Creator item);

    @StateStrategyType(AddToEndStrategy.class)
    void showItems(@NonNull List<Creator> items);

    void showLoading(Disposable disposable);
}
