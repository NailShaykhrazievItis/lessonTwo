package com.itis.android.lessontwo.ui.character;

import android.support.annotation.NonNull;

import com.itis.android.lessontwo.model.character.Character;
import com.itis.android.lessontwo.ui.base.BaseView;

/**
 * Created by a9 on 03.03.18.
 */

public interface CharacterContract {

    interface View extends BaseView<Presenter> {

        void showCharacter(@NonNull Character item);
    }

    interface Presenter {

        void loadCharacter(long id);
    }
}
