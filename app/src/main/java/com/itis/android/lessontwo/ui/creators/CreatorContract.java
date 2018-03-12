package com.itis.android.lessontwo.ui.creators;

import com.itis.android.lessontwo.model.creators.Creators;
import com.itis.android.lessontwo.ui.base.BaseView;

import io.reactivex.annotations.NonNull;

/**
 * Created by Tony on 3/5/2018.
 */

public interface CreatorContract {

    interface View extends BaseView<Presenter> {
        void showCreators(@NonNull Creators creators);
    }

    interface Presenter {
        void loadCreators(long id);
    }
}
