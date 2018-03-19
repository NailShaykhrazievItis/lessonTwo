package com.itis.android.lessontwo.ui.characters;

import com.arellomobile.mvp.MvpView;
import com.itis.android.lessontwo.model.character.Character;

/**
 * Created by Tony on 3/18/2018.
 */

public interface CharacterView extends MvpView {

    void getCharacterId();

    void handleError(Throwable error);

    void setDescription(Character character);

    void setImage(Character character);

    void setName(Character character);
}