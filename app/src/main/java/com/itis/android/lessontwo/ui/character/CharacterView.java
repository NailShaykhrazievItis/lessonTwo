package com.itis.android.lessontwo.ui.character;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;
import com.itis.android.lessontwo.model.character.Character;

/**
 * Created by User on 18.03.2018.
 */
@StateStrategyType(AddToEndSingleStrategy.class)
public interface CharacterView extends MvpView {

    void getCharacterId();

    void handleError(Throwable error);

    void setDescription(Character character);

    void setImage(Character character);
}
