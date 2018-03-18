package com.itis.android.lessontwo.ui.creators;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.itis.android.lessontwo.api.ApiFactory;
import com.itis.android.lessontwo.model.creator.CreatorResponse;
import com.itis.android.lessontwo.model.creator.CreatorResponseData;
import com.itis.android.lessontwo.utils.RxUtils;

/**
 * Created by Blaheart on 05.03.2018.
 */

@InjectViewState
public class CreatorPresenter extends MvpPresenter<CreatorContract.View> implements CreatorContract.Presenter {

    public CreatorPresenter() {

    }

    @Override
    public void loadCreator(long id) {
        ApiFactory.getCreatorsService()
                .creators(id)
                .map(CreatorResponse::getData)
                .map(CreatorResponseData::getResults)
                .map(list -> list.get(0))
                .compose(RxUtils.async())
                .subscribe(getViewState()::showCreator, getViewState()::handleError);
    }
}
