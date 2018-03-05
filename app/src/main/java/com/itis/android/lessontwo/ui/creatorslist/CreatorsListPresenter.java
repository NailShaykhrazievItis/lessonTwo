package com.itis.android.lessontwo.ui.creatorslist;

import com.itis.android.lessontwo.api.ApiFactory;
import com.itis.android.lessontwo.model.creators.Creators;
import com.itis.android.lessontwo.model.creators.CreatorsResponse;
import com.itis.android.lessontwo.model.creators.CreatorsResponseData;
import com.itis.android.lessontwo.utils.RxUtils;

import static com.itis.android.lessontwo.utils.Constants.DEFAULT_COMICS_SORT;
import static com.itis.android.lessontwo.utils.Constants.PAGE_SIZE;
import static com.itis.android.lessontwo.utils.Constants.ZERO_OFFSET;

/**
 * Created by Tony on 3/5/2018.
 */

public class CreatorsListPresenter implements CreatorsListContract.Presenter {
    private final CreatorsListContract.View view;

    public CreatorsListPresenter(CreatorsListContract.View view) {
        this.view = view;
        this.view.setPresenter(this);
    }

    @Override
    public void loadCreators() {
        ApiFactory.getCreatorService()
                .creators(ZERO_OFFSET, PAGE_SIZE, DEFAULT_COMICS_SORT)
                .map(CreatorsResponse::getData)
                .map(CreatorsResponseData::getResults)
//                .doOnSubscribe(view::showLoading)
//                .doOnTerminate(view::hideLoading)
                .compose(RxUtils.async())
                .subscribe(view::showItems, view::handleError);
    }

    @Override
    public void loadNextElements(int page) {
        ApiFactory.getCreatorService()
                .creators(page * PAGE_SIZE, PAGE_SIZE, DEFAULT_COMICS_SORT)
                .map(CreatorsResponse::getData)
                .map(CreatorsResponseData::getResults)
                .doOnTerminate(view::setNotLoading)
                .compose(RxUtils.async())
                .subscribe(view::addMoreItems, view::handleError);
    }

    @Override
    public void onItemClick(Creators creators) {
        view.showDetails(creators);
    }
}
