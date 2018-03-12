package com.itis.android.lessontwo.ui.creatorslist;

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

public class CreatorsListPresenter implements CreatorsListContract.Presenter {

    private final CreatorsListContract.View view;

    CreatorsListPresenter(CreatorsListContract.View view) {
        this.view = view;
        this.view.setPresenter(this);
    }

    @Override
    public void loadCreators() {
        RepositoryProvider.provideCreatorsRepository()
                .creators(ZERO_OFFSET, PAGE_SIZE, DEFAULT_CREATORS_SORT)
                .doOnSubscribe(view::showLoading)
                .doAfterTerminate(view::hideLoading)
                .subscribe(view::showItems, view::handleError);
    }

    @Override
    public void loadNextElements(int page) {
        RepositoryProvider.provideCreatorsRepository()
                .creators(ZERO_OFFSET, PAGE_SIZE, DEFAULT_CREATORS_SORT)
                .doOnSubscribe(view::showLoading)
                .doAfterTerminate(view::hideLoading)
                .doAfterTerminate(view::setNotLoading)
                .subscribe(view::addMoreItems, view::handleError);
    }
    @Override
    public void onItemClick(Creators creators) {
        view.showDetails(creators);
    }
}
