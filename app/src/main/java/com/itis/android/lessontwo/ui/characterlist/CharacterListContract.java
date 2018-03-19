package com.itis.android.lessontwo.ui.characterlist;

import android.support.annotation.NonNull;
import com.arellomobile.mvp.viewstate.strategy.AddToEndStrategy;
import com.arellomobile.mvp.viewstate.strategy.SkipStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;
import com.itis.android.lessontwo.model.character.Character;
import com.itis.android.lessontwo.ui.base.BaseView;
import io.reactivex.disposables.Disposable;
import java.util.List;

public interface CharacterListContract {

    @StateStrategyType(SkipStrategy.class)
    interface View extends BaseView<Presenter> {

        @StateStrategyType(AddToEndStrategy.class)
        void showItems(@NonNull List<Character> items);

        void showDetails(Character item);

        @StateStrategyType(AddToEndStrategy.class)
        void addMoreItems(List<Character> items);

        void setNotLoading();

        void showLoading(Disposable disposable);

        void hideLoading();
    }

    interface Presenter {

        void loadCharacters();

        void loadNextElements(int page);

        void onItemClick(Character character);
    }
}
