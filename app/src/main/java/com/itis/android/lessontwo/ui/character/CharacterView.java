package com.itis.android.lessontwo.ui.character;

import com.arellomobile.mvp.MvpView;

import com.itis.android.lessontwo.model.character.Character;

/**
 * Created by Home on 18.03.2018.
 */

public interface CharacterView extends MvpView {

    void getCharacterId();

    void setImage(Character character);

    void setDescription(Character character);

    void handleError(Throwable throwable);
}
