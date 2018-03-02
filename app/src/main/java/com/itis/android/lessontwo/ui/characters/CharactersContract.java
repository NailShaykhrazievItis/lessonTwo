package com.itis.android.lessontwo.ui.characters;

import android.support.annotation.NonNull;

import com.itis.android.lessontwo.model.character.Character;
import com.itis.android.lessontwo.ui.base.BaseView;

/**
 * Created by Ruslan on 02.03.2018.
 */

public interface CharactersContract {

    interface View extends BaseView<CharactersContract.Presenter> {

        void showCharacter(@NonNull Character item);
    }

    interface Presenter {

        void loadCharacter(long id);
    }
}
