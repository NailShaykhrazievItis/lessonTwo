package com.itis.android.lessontwo.ui.creators;

import com.arellomobile.mvp.MvpView;
import com.itis.android.lessontwo.model.entity.creators.Creator;

/**
 * Created by valera071998@gmail.com on 16.03.2018.
 */

public interface CreatorsView extends MvpView {

    void handleError(Throwable error);

    void getCreatorById();

    void setDescription(Creator creator);

    void setImage(Creator creator);

    void setName(Creator creator);
}
