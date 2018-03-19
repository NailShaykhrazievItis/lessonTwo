package com.itis.android.lessontwo.ui.creatorslist;

import android.support.annotation.NonNull;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy;
import com.arellomobile.mvp.viewstate.strategy.SkipStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;
import com.itis.android.lessontwo.model.creators.Creators;

import java.util.List;

import io.reactivex.disposables.Disposable;

/**
 * Created by Tony on 3/19/2018.
 */

@StateStrategyType(AddToEndSingleStrategy.class)
public interface CreatorsListView extends MvpView{

    void showItems(@NonNull List<Creators> items);

    @StateStrategyType(OneExecutionStateStrategy.class)
    void handleError(Throwable error);

    void addMoreItems(List<Creators> items);

    void setNotLoading();

    void showLoading(Disposable disposable);

    void hideLoading();

    //* Navigation methods*/
    @StateStrategyType(SkipStrategy.class)
    void showDetails(Creators item);
}
