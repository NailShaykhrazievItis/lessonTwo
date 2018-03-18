package com.itis.android.lessontwo.ui.characters;

import com.arellomobile.mvp.MvpView;
import com.itis.android.lessontwo.model.entity.character.Character;

/**
 * Created by Ruslan on 17.03.2018.
 */

public interface CharactersView extends MvpView {

    void getCharacterId();

    void handleError(Throwable error);

    void setDescription(Character character);

    void setImage(Character character);

    void setName(Character character);
}
