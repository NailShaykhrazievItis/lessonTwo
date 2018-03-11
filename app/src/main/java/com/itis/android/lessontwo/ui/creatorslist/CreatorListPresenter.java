package com.itis.android.lessontwo.ui.creatorslist;

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

public class CreatorListPresenter implements CreatorListContract.Presenter {

    private final CreatorListContract.View view;

    public CreatorListPresenter(CreatorListContract.View view) {
        this.view = view;
        this.view.setPresenter(this);
    }

    @Override
    public void loadCreators() {
        ApiFactory.getCreatorsService()
                .creators(ZERO_OFFSET, PAGE_SIZE, DEFAULT_CREATOR_SORT)
                .map(CreatorResponse::getData)
                .map(CreatorResponseData::getResults)
//                .doOnSubscribe(view::showLoading)
//                .doOnTerminate(view::hideLoading)
                .compose(RxUtils.async())
                .subscribe(view::showItems, view::handleError);
    }

    @Override
    public void loadNextElements(int page) {
        ApiFactory.getCreatorsService()
                .creators(page * PAGE_SIZE, PAGE_SIZE, DEFAULT_CREATOR_SORT)
                .map(CreatorResponse::getData)
                .map(CreatorResponseData::getResults)
                .doOnTerminate(view::setNotLoading)
                .compose(RxUtils.async())
                .subscribe(view::addMoreItems, view::handleError);
    }

    @Override
    public void onItemClick(Creator creator) {
        view.showDetails(creator);
    }
}
