package com.itis.android.lessontwo.ui.comicslist;

import android.support.annotation.NonNull;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;
import com.itis.android.lessontwo.model.entity.comics.Comics;

import java.util.List;

import io.reactivex.disposables.Disposable;

/**
 * Created by valera071998@gmail.com on 16.03.2018.
 */

@StateStrategyType(AddToEndSingleStrategy.class)
public interface ComicsListView extends MvpView {

    @StateStrategyType(OneExecutionStateStrategy.class)
    void handleError(Throwable throwable);

    void showItems(@NonNull List<Comics> comics);

    void addMoreItems(List<Comics> comics);

    void setNotLoading();

    void showLoading(Disposable disposable);

    void hideLoading();

    void showDetails(Comics comics);
}
