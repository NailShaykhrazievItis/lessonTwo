package com.itis.android.lessontwo.ui.comics;

import android.support.annotation.NonNull;

import com.itis.android.lessontwo.model.comics.Comics;
import com.itis.android.lessontwo.ui.base.BaseView;

/**
 * Created by Ruslan on 02.03.2018.
 */

public interface ComicsContract {

    interface View extends BaseView<Presenter> {

        void showComics(@NonNull Comics item);
    }

    interface Presenter {

        void loadComics(long id);
    }
}
