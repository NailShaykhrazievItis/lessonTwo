package com.itis.android.lessontwo.ui.characterslist;

import android.support.annotation.NonNull;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;
import com.itis.android.lessontwo.model.entity.character.Character;

import java.util.List;

import io.reactivex.disposables.Disposable;

/**
 * Created by Ruslan on 17.03.2018.
 */

public interface CharactersListView extends MvpView {

    @StateStrategyType(OneExecutionStateStrategy.class)
    void handleError(Throwable throwable);

    void showItems(@NonNull List<Character> items);

    void showDetails(Character item);

    void addMoreItems(List<Character> items);

    void setNotLoading();

    void showLoading(Disposable disposable);

    void hideLoading();
}
