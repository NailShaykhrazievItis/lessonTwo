package com.itis.android.lessontwo.ui.characterslist;

import android.support.annotation.NonNull;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy;
import com.arellomobile.mvp.viewstate.strategy.SkipStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;
import com.itis.android.lessontwo.model.character.Character;

import java.util.List;

import io.reactivex.disposables.Disposable;

/**
 * Created by Tony on 3/18/2018.
 */

@StateStrategyType(AddToEndSingleStrategy.class)
public interface CharactersListView extends MvpView {

    void showItems(@NonNull List<Character> items);

    @StateStrategyType(OneExecutionStateStrategy.class)
    void handleError(Throwable error);

    void addMoreItems(List<Character> items);

    void setNotLoading();

    void showLoading(Disposable disposable);

    void hideLoading();

    //* Navigation methods*/
    @StateStrategyType(SkipStrategy.class)
    void showDetails(Character item);

}