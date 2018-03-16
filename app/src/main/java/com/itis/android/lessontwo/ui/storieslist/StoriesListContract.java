package com.itis.android.lessontwo.ui.storieslist;

import android.support.annotation.NonNull;

import com.itis.android.lessontwo.model.story.Story;
import com.itis.android.lessontwo.ui.base.BaseView;

import java.util.List;

import io.reactivex.disposables.Disposable;

/**
 * Created by User on 16.03.2018.
 */

public interface StoriesListContract {

    interface View extends BaseView<Presenter> {

        void showItems(@NonNull List<Story> items);

        void showDetails(Story item);

        void addMoreItems(List<Story> items);

        void setNotLoading();

        void showLoading(Disposable disposable);

        void hideLoading();
    }

    interface Presenter {

        void loadStories();

        void loadNextElements(int page);

        void onItemClick(Story story);
    }
}
