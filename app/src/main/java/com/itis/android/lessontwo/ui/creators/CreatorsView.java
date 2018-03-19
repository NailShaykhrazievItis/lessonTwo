package com.itis.android.lessontwo.ui.creators;

import com.arellomobile.mvp.MvpView;
import com.itis.android.lessontwo.model.character.Character;
import com.itis.android.lessontwo.model.creators.Creators;

/**
 * Created by Tony on 3/19/2018.
 */

public interface CreatorsView extends MvpView {

    void setImage (Creators creators);

    void getCreatorId();

    void setName(Creators creators);

    void handleError(Throwable error);
}
