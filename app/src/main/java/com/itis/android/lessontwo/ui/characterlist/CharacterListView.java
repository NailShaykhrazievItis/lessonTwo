package com.itis.android.lessontwo.ui.characterlist;

import android.support.annotation.NonNull;
import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndStrategy;
import com.arellomobile.mvp.viewstate.strategy.SkipStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;
import com.itis.android.lessontwo.model.character.Character;
import io.reactivex.disposables.Disposable;
import java.util.List;

@StateStrategyType(SkipStrategy.class)
interface CharacterListView extends MvpView {

    @StateStrategyType(AddToEndStrategy.class)
    void addMoreItems(List<Character> items);

    void handleError(Throwable error);

    void hideLoading();

    void setNotLoading();

    void showDetails(Character item);

    @StateStrategyType(AddToEndStrategy.class)
    void showItems(@NonNull List<Character> items);

    void showLoading(Disposable disposable);
}
