package com.itis.android.lessontwo.ui.creatorslist;

import android.support.annotation.NonNull;

import com.itis.android.lessontwo.model.creators.Creators;
import com.itis.android.lessontwo.ui.base.BaseView;

import java.util.List;

import io.reactivex.disposables.Disposable;

/**
 * Created by Tony on 3/5/2018.
 */

public interface CreatorsListContract {

    interface View extends BaseView<Presenter> {

        void showItems(@NonNull List<Creators> items);

        void showDetails(Creators item);

        void addMoreItems(List<Creators> items);

        void setNotLoading();

        void showLoading(Disposable disposable);

        void hideLoading();
    }

    interface Presenter {

        void loadCreators();

        void loadNextElements(int page);

        void onItemClick(Creators creators);
    }
}
