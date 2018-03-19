package com.itis.android.lessontwo.ui.storieslist;

import static com.itis.android.lessontwo.utils.Constants.PAGE_SIZE;
import static com.itis.android.lessontwo.utils.Constants.ZERO_OFFSET;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.itis.android.lessontwo.repository.RepositoryProvider;

/**
 * Created by User on 16.03.2018.
 */
@InjectViewState
public class StoriesListPresenter extends MvpPresenter<StoriesListView> {

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
        loadStories();
    }

    public void loadStories() {
        RepositoryProvider.provideStoriesRepostitory()
                .stories(ZERO_OFFSET, PAGE_SIZE)
                .doOnSubscribe(getViewState()::showLoading)
                .doAfterTerminate(getViewState()::hideLoading)
                .subscribe(getViewState()::showItems, getViewState()::handleError);
    }

    public void loadNextElements(int page) {
        RepositoryProvider.provideStoriesRepostitory()
                .stories(page * PAGE_SIZE, PAGE_SIZE)
                .doOnSubscribe(getViewState()::showLoading)
                .doAfterTerminate(getViewState()::hideLoading)
                .doAfterTerminate(getViewState()::setNotLoading)
                .subscribe(getViewState()::addMoreItems, getViewState()::handleError);
    }
}
