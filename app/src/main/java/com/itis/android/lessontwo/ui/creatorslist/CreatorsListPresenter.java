package com.itis.android.lessontwo.ui.creatorslist;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.itis.android.lessontwo.api.ApiFactory;
import com.itis.android.lessontwo.model.creators.Creators;
import com.itis.android.lessontwo.model.creators.CreatorsResponse;
import com.itis.android.lessontwo.model.creators.CreatorsResponseData;
import com.itis.android.lessontwo.repository.RepositoryProvider;
import com.itis.android.lessontwo.utils.RxUtils;

import static com.itis.android.lessontwo.utils.Constants.DEFAULT_CREATORS_SORT;
import static com.itis.android.lessontwo.utils.Constants.PAGE_SIZE;
import static com.itis.android.lessontwo.utils.Constants.ZERO_OFFSET;

/**
 * Created by Tony on 3/5/2018.
 */

@InjectViewState
public class CreatorsListPresenter extends MvpPresenter<CreatorsListView> {

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
        loadCreators();
    }

    public void loadCreators() {
        RepositoryProvider.provideCreatorsRepository()
                .creators(ZERO_OFFSET, PAGE_SIZE, DEFAULT_CREATORS_SORT)
                .doOnSubscribe(getViewState()::showLoading)
                .doAfterTerminate(getViewState()::hideLoading)
                .subscribe(getViewState()::showItems, getViewState()::handleError);
    }


    public void loadNextElements(int page) {
        RepositoryProvider.provideCreatorsRepository()
                .creators(ZERO_OFFSET, PAGE_SIZE, DEFAULT_CREATORS_SORT)
                .doOnSubscribe(getViewState()::showLoading)
                .doAfterTerminate(getViewState()::hideLoading)
                .doAfterTerminate(getViewState()::setNotLoading)
                .subscribe(getViewState()::addMoreItems, getViewState()::handleError);
    }

    public void onItemClick(Creators creators) {
        getViewState().showDetails(creators);
    }
}
