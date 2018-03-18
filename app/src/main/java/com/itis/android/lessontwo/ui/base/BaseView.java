package com.itis.android.lessontwo.ui.base;

import com.arellomobile.mvp.MvpView;

/**
 * Created by Nail Shaykhraziev on 26.02.2018.
 */
public interface BaseView<T> extends MvpView{

//    void setPresenter(T presenter);

    void handleError(Throwable error);
}
