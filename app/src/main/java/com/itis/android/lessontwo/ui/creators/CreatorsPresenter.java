package com.itis.android.lessontwo.ui.creators;

import com.itis.android.lessontwo.api.ApiFactory;
import com.itis.android.lessontwo.model.creators.Creator;
import com.itis.android.lessontwo.model.creators.CreatorsResponse;
import com.itis.android.lessontwo.model.creators.CreatorsResponseData;
import com.itis.android.lessontwo.ui.base.BaseContract;
import com.itis.android.lessontwo.utils.RxUtils;

/**
 * Created by valera071998@gmail.com on 02.03.2018.
 */

public class CreatorsPresenter implements BaseContract.Presenter {

    private final BaseContract.View<Creator> view;

    public CreatorsPresenter(BaseContract.View<Creator> view) {
        this.view = view;
        this.view.setPresenter(this);
    }

    @Override
    public void load(long id) {
        ApiFactory.getCreatorsService()
                .creator(id)
                .map(CreatorsResponse::getData)
                .map(CreatorsResponseData::getResults)
                .map(list -> list.get(0))
                .compose(RxUtils.async())
                .subscribe(this.view::show, this.view::handleError);
    }
}
