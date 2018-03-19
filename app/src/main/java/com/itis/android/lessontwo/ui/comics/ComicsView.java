package com.itis.android.lessontwo.ui.comics;

import com.arellomobile.mvp.MvpView;
import com.itis.android.lessontwo.model.comics.Comics;

/**
 * Created by Nail Shaykhraziev on 12.03.2018.
 */

public interface ComicsView extends MvpView {

    void getComicsId();

    void handleError(Throwable error);

    void setPageCount(Comics comics);

    void setPrice(Comics comics);

    void setDescription(Comics comics);

    void setImage(Comics comics);
}
