package com.itis.android.lessontwo.ui.creatorslist;

import android.support.annotation.NonNull;

import com.itis.android.lessontwo.model.comics.Comics;
import com.itis.android.lessontwo.model.creator.Creator;
import com.itis.android.lessontwo.ui.base.BaseView;
import com.itis.android.lessontwo.ui.comicslist.ComicsListContract;

import java.util.List;

import io.reactivex.disposables.Disposable;

/**
 * Created by Bulat Murtazin on 05.03.2018.
 * KPFU ITIS 11-601
 */

public interface CreatorListContract {

    interface View extends BaseView<Presenter> {

        void showItems(@NonNull List<Creator> items);

        void showDetails(Creator item);

        void addMoreItems(List<Creator> items);

        void setNotLoading();

        void showLoading(Disposable disposable);

        void hideLoading();

    }

    interface Presenter {

        void loadCreators();

        void loadNextElements(int page);

        void onItemClick(Creator creator);
    }
}
