package com.itis.android.lessontwo.ui.creatorslist;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.itis.android.lessontwo.api.ApiFactory;
import com.itis.android.lessontwo.model.comics.Comics;
import com.itis.android.lessontwo.model.comics.ComicsResponse;
import com.itis.android.lessontwo.model.comics.ComicsResponseData;
import com.itis.android.lessontwo.model.creator.Creator;
import com.itis.android.lessontwo.model.creator.CreatorResponse;
import com.itis.android.lessontwo.model.creator.CreatorResponseData;
import com.itis.android.lessontwo.ui.comicslist.ComicsListContract;
import com.itis.android.lessontwo.utils.RxUtils;

import static com.itis.android.lessontwo.utils.Constants.DEFAULT_COMICS_SORT;
import static com.itis.android.lessontwo.utils.Constants.DEFAULT_CREATOR_SORT;
import static com.itis.android.lessontwo.utils.Constants.PAGE_SIZE;
import static com.itis.android.lessontwo.utils.Constants.ZERO_OFFSET;

/**
 * Created by Bulat Murtazin on 05.03.2018.
 * KPFU ITIS 11-601
 */

@InjectViewState
public class CreatorListPresenter extends MvpPresenter<CreatorListContract.View> implements CreatorListContract.Presenter {

    @Override
    public void loadCreators() {
        ApiFactory.getCreatorsService()
                .creators(ZERO_OFFSET, PAGE_SIZE, DEFAULT_CREATOR_SORT)
                .map(CreatorResponse::getData)
                .map(CreatorResponseData::getResults)
//                .doOnSubscribe(view::showLoading)
//                .doOnTerminate(view::hideLoading)
                .compose(RxUtils.async())
                .subscribe(getViewState()::showItems, getViewState()::handleError);
    }

    @Override
    public void loadNextElements(int page) {
        ApiFactory.getCreatorsService()
                .creators(page * PAGE_SIZE, PAGE_SIZE, DEFAULT_CREATOR_SORT)
                .map(CreatorResponse::getData)
                .map(CreatorResponseData::getResults)
                .doOnTerminate(getViewState()::setNotLoading)
                .compose(RxUtils.async())
                .subscribe(getViewState()::addMoreItems, getViewState()::handleError);
    }

    @Override
    public void onItemClick(Creator creator) {
        getViewState().showDetails(creator);
    }
}
