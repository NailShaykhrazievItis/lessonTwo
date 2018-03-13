package com.itis.android.lessontwo.ui.comicslist;

import android.support.annotation.NonNull;
import com.itis.android.lessontwo.model.comics.Comics;
import com.itis.android.lessontwo.ui.base.BaseView;
import io.reactivex.disposables.Disposable;
import java.util.List;

/**
 * Created by Nail Shaykhraziev on 26.02.2018.
 */

public interface ComicsListContract {

    interface View extends BaseView<Presenter> {

        void showItems(@NonNull List<Comics> items);

        void showDetails(Comics item);

        void addMoreItems(List<Comics> items);

        void setNotLoading();

        void showLoading(Disposable disposable);

        void hideLoading();
    }

    interface Presenter {

        void loadComics();

        void loadNextElements(int page);

        void onItemClick(Comics comics);
    }
}
