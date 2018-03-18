package com.itis.android.lessontwo.ui.characterslist;

import android.support.annotation.NonNull;

import com.arellomobile.mvp.MvpView;

import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

import com.itis.android.lessontwo.model.character.Character;

import io.reactivex.disposables.Disposable;

import java.util.List;

/**
 * Created by Home on 18.03.2018.
 */
@StateStrategyType(AddToEndSingleStrategy.class)
public interface CharactersListView extends MvpView {

    @StateStrategyType(OneExecutionStateStrategy.class)
    void handleError(Throwable error);

    void showItems(@NonNull List<Character> items);

    void showDetails(Character item);

    void addMoreItems(List<Character> items);

    void setNotLoading();

    void showLoading(Disposable disposable);

    void hideLoading();
}
