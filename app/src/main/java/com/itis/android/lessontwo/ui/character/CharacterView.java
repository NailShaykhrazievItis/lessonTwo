package com.itis.android.lessontwo.ui.character;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;
import com.itis.android.lessontwo.model.character.Character;

@StateStrategyType(AddToEndSingleStrategy.class)
interface CharacterView extends MvpView {

    void getCharacterId();

    void handleError(Throwable error);

    void setCharacterDescription(Character character);

    void setCharacterImage(Character character);

    void setCharacterName(Character character);
}
