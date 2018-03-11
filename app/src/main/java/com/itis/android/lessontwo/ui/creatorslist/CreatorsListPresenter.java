package com.itis.android.lessontwo.ui.creatorslist;

import com.itis.android.lessontwo.api.ApiFactory;
import com.itis.android.lessontwo.model.entity.creators.Creator;
import com.itis.android.lessontwo.model.entity.creators.CreatorsResponse;
import com.itis.android.lessontwo.model.entity.creators.CreatorsResponseData;
import com.itis.android.lessontwo.repository.RepositoryProvider;
import com.itis.android.lessontwo.ui.base.BaseListContract;
import com.itis.android.lessontwo.utils.RxUtils;

import static com.itis.android.lessontwo.utils.Constants.DEFAULT_CREATOR_SORT;
import static com.itis.android.lessontwo.utils.Constants.PAGE_SIZE;
import static com.itis.android.lessontwo.utils.Constants.ZERO_OFFSET;

/**
 * Created by valera071998@gmail.com on 02.03.2018.
 */

public class CreatorsListPresenter implements BaseListContract.Presenter<Creator> {

    private final BaseListContract.View<Creator> view;

    public CreatorsListPresenter(BaseListContract.View<Creator> view) {
        this.view = view;
        this.view.setPresenter(this);
    }

    @Override
    public void load() {
        RepositoryProvider.provideCreatorRepository()
                .creators(ZERO_OFFSET, PAGE_SIZE, DEFAULT_CREATOR_SORT)
                .doOnSubscribe(view::showLoading)
                .doAfterTerminate(view::hideLoading)
                .subscribe(view::showItems, view::handleError);
    }

    @Override
    public void loadNextElements(int page) {
        RepositoryProvider.provideCreatorRepository()
                .creators(page * PAGE_SIZE, PAGE_SIZE, DEFAULT_CREATOR_SORT)
                .doOnSubscribe(view::showLoading)
                .doAfterTerminate(view::hideLoading)
                .doAfterTerminate(view::setNotLoading)
                .subscribe(view::addMoreItems, view::handleError);
    }

    @Override
    public void onItemClick(Creator creator) {
        view.showDetails(creator);
    }
}
