package com.itis.android.lessontwo.ui.creatorslist;

import android.support.annotation.NonNull;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;
import com.itis.android.lessontwo.model.entity.creators.Creator;

import java.util.List;

import io.reactivex.disposables.Disposable;

/**
 * Created by valera071998@gmail.com on 16.03.2018.
 */

public interface CreatorsListView extends MvpView {

    @StateStrategyType(OneExecutionStateStrategy.class)
    void handleError(Throwable throwable);

    void showItems(@NonNull List<Creator> items);

    void showDetails(Creator item);

    void addMoreItems(List<Creator> items);

    void setNotLoading();

    void showLoading(Disposable disposable);

    void hideLoading();
}