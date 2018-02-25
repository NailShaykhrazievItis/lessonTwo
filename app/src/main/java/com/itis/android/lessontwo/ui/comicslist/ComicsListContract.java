package com.itis.android.lessontwo.ui.comicslist;

import android.support.annotation.NonNull;

import com.itis.android.lessontwo.model.comics.Comics;
import com.itis.android.lessontwo.ui.base.BaseView;

import java.util.List;

import io.reactivex.disposables.Disposable;

/**
 * Created by Nail Shaykhraziev on 26.02.2018.
 */

public interface ComicsListContract {

    interface View extends BaseView<Presenter>{

        void showItems(@NonNull List<Comics> items);

        void showDetails(Comics item);

        void showLoading(Disposable disposable);

        void hideLoading();
    }

    interface Presenter {

        void loadComics();

        void onItemClick(Comics comics);
    }
}
