package com.itis.android.lessontwo.ui.characters;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;
import com.itis.android.lessontwo.model.character.Character;

/**
 * Created by Tony on 3/18/2018.
 */

public interface CharacterView extends MvpView {

    void getCharacterId();

    @StateStrategyType(OneExecutionStateStrategy.class)
    void handleError(Throwable error);

    void setDescription(Character character);

    void setImage(Character character);
}