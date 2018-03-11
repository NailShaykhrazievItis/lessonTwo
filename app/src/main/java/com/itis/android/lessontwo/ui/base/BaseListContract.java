package com.itis.android.lessontwo.ui.base;

import android.support.annotation.NonNull;

import com.itis.android.lessontwo.model.entity.comics.Comics;

import java.util.List;

import io.reactivex.disposables.Disposable;

/**
 * Created by valera071998@gmail.com on 02.03.2018.
 */

public interface BaseListContract {

    interface View<T> extends BaseView<Presenter<T>>{

        void showItems(@NonNull List<T> items);

        void showDetails(T item);

        void addMoreItems(List<T> items);

        void setNotLoading();

        void showLoading(Disposable disposable);

        void hideLoading();
    }

    interface Presenter<T> {

        void load();

        void loadNextElements(int page);

        void onItemClick(T item);
    }
}
