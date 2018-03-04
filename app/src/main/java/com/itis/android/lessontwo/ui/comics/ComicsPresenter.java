package com.itis.android.lessontwo.ui.comics;

import com.itis.android.lessontwo.api.ApiFactory;
import com.itis.android.lessontwo.model.comics.Comics;
import com.itis.android.lessontwo.model.comics.ComicsResponse;
import com.itis.android.lessontwo.model.comics.ComicsResponseData;
import com.itis.android.lessontwo.ui.base.BaseContract;
import com.itis.android.lessontwo.utils.RxUtils;

/**
 * Created by Ruslan on 02.03.2018.
 */

public class ComicsPresenter implements BaseContract.Presenter {

    private final BaseContract.View<Comics> view;

    public ComicsPresenter(BaseContract.View<Comics> view) {
        this.view = view;
        this.view.setPresenter(this);
    }

    @Override
    public void load(long id) {
        ApiFactory.getComicsService()
                .comics(id)
                .map(ComicsResponse::getData)
                .map(ComicsResponseData::getResults)
                .map(list -> list.get(0))
                .compose(RxUtils.async())
                .subscribe(this.view::show, this.view::handleError);
    }
}
