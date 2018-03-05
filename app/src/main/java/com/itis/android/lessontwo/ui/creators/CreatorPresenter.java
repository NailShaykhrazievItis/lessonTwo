package com.itis.android.lessontwo.ui.creators;

import com.itis.android.lessontwo.api.ApiFactory;
import com.itis.android.lessontwo.model.comics.ComicsResponse;
import com.itis.android.lessontwo.model.comics.ComicsResponseData;
import com.itis.android.lessontwo.model.creators.CreatorsResponse;
import com.itis.android.lessontwo.model.creators.CreatorsResponseData;
import com.itis.android.lessontwo.utils.RxUtils;

/**
 * Created by Tony on 3/5/2018.
 */

public class CreatorPresenter implements CreatorContract.Presenter {
    private final CreatorContract.View view;

    public CreatorPresenter(CreatorContract.View view) {
        this.view = view;
        this.view.setPresenter(this);
    }

    @Override
    public void loadCreators(long id) {
        ApiFactory.getCreatorService()
                .creators(id)
                .map(CreatorsResponse::getData)
                .map(CreatorsResponseData::getResults)
                .map(list -> list.get(0))
                .compose(RxUtils.async())
                .subscribe(view::showCreators, view::handleError);
    }
}
