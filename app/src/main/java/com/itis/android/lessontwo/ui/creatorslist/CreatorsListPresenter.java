package com.itis.android.lessontwo.ui.creatorslist;

import android.support.annotation.VisibleForTesting;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.itis.android.lessontwo.model.entity.creators.Creator;
import com.itis.android.lessontwo.repository.RepositoryProvider;

import static com.itis.android.lessontwo.utils.Constants.DEFAULT_CREATOR_SORT;
import static com.itis.android.lessontwo.utils.Constants.PAGE_SIZE;
import static com.itis.android.lessontwo.utils.Constants.ZERO_OFFSET;

/**
 * Created by valera071998@gmail.com on 02.03.2018.
 */
@InjectViewState
public class CreatorsListPresenter extends MvpPresenter<CreatorsListView> {

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
        load();
    }

    @VisibleForTesting
    public void load() {
        RepositoryProvider.provideCreatorRepository()
                .creators(ZERO_OFFSET, PAGE_SIZE, DEFAULT_CREATOR_SORT)
                .doOnSubscribe(getViewState()::showLoading)
                .doAfterTerminate(getViewState()::hideLoading)
                .subscribe(getViewState()::showItems, getViewState()::handleError);
    }

    public void loadNextElements(int page) {
        RepositoryProvider.provideCreatorRepository()
                .creators(page * PAGE_SIZE, PAGE_SIZE, DEFAULT_CREATOR_SORT)
                .doOnSubscribe(getViewState()::showLoading)
                .doAfterTerminate(getViewState()::hideLoading)
                .doAfterTerminate(getViewState()::setNotLoading)
                .subscribe(getViewState()::addMoreItems, getViewState()::handleError);
    }

    public void onItemClick(Creator creator) {
        getViewState().showDetails(creator);
    }
}
