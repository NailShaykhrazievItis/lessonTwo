package com.itis.android.lessontwo.ui.comics;

import com.itis.android.lessontwo.model.comics.Comics;
import com.itis.android.lessontwo.ui.base.BaseView;

import io.reactivex.annotations.NonNull;

/**
 * Created by Tony on 3/5/2018.
 */

public interface ComicsContract {

    interface View extends BaseView<Presenter> {
        void showComics(@NonNull Comics item);
    }

    interface Presenter {
        void loadComics(long id);
    }
}
