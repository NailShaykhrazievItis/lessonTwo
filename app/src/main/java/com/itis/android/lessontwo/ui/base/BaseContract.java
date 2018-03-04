package com.itis.android.lessontwo.ui.base;

import android.support.annotation.NonNull;

/**
 * Created by valera071998@gamil.com on 02.03.2018.
 */

public interface BaseContract {

    interface View<T> extends BaseView<Presenter> {
        void show(@NonNull T item);
    }

    interface Presenter {
        void load(long id);
    }
}
