package com.itis.android.lessontwo.ui.character;

import android.support.annotation.NonNull;
import com.arellomobile.mvp.MvpPresenter;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;
import com.itis.android.lessontwo.model.character.Character;
import com.itis.android.lessontwo.ui.base.BaseView;

public interface CharacterContract {

    @StateStrategyType(AddToEndSingleStrategy.class)
    interface View extends BaseView<CharacterContract.Presenter> {

        void showCharacter(@NonNull Character character);
    }

    interface Presenter{

        void loadCharacter(long id);
    }
}
