package com.itis.android.lessontwo.ui.comicslist;

import android.support.annotation.NonNull;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;
import com.itis.android.lessontwo.model.comics.Comics;

import java.util.List;

import io.reactivex.disposables.Disposable;

/**
 * Created by Nail Shaykhraziev on 26.02.2018.
 */
@StateStrategyType(AddToEndSingleStrategy.class)
public interface ComicsListView extends MvpView {

    void showItems(@NonNull List<Comics> items);

    @StateStrategyType(OneExecutionStateStrategy.class)
    void handleError(Throwable error);

    void addMoreItems(List<Comics> items);

    void setNotLoading();

    void showLoading(Disposable disposable);

    void hideLoading();

    //* Navigation methods*/
    void showDetails(Comics item);
}