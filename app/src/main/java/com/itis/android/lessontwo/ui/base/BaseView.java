package com.itis.android.lessontwo.ui.base;

/**
 * Created by Nail Shaykhraziev on 26.02.2018.
 */
public interface BaseView<T> {

    void setPresenter(T presenter);

    void handleError(Throwable error);
}
