package com.itis.android.lessontwo.ui.characters;

import android.support.annotation.NonNull;
import com.itis.android.lessontwo.model.character.Character;
import com.itis.android.lessontwo.ui.base.BaseView;

/**
 * Created by User on 04.03.2018.
 */

public interface CharacterContract {

    interface View extends BaseView<CharacterContract.Presenter> {

        void showCharacter(@NonNull Character character);
    }

    interface Presenter {

        void initCharacter(long id);
    }
}
