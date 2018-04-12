package com.itis.android.lessontwo.ui.character;

import com.arellomobile.mvp.MvpView;

import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;
import com.itis.android.lessontwo.model.character.Character;

/**
 * Created by Home on 18.03.2018.
 */

public interface CharacterView extends MvpView {

    void getCharacterId();

    void setImage(Character character);

    void setDescription(Character character);

    @StateStrategyType(OneExecutionStateStrategy.class)
    void handleError(Throwable throwable);
}
