package com.itis.android.lessontwo.ui.comics;

import com.itis.android.lessontwo.api.ApiFactory;
import com.itis.android.lessontwo.model.comics.ComicsResponse;
import com.itis.android.lessontwo.model.comics.ComicsResponseData;
import com.itis.android.lessontwo.utils.RxUtils;

/**
 * Created by a9 on 02.03.18.
 */

public class ComicsPresenter implements ComicsContract.Presenter {

    private final ComicsContract.View view;

    public ComicsPresenter(ComicsContract.View view) {
        this.view = view;
        this.view.setPresenter(this);
    }

    @Override
    public void loadComics(long id) {
        ApiFactory.getComicsService()
            .comics(id)
            .map(ComicsResponse::getData)
            .map(ComicsResponseData::getResults)
            .map(list -> list.get(0))
            .compose(RxUtils.async())
            .subscribe(view::showComics, view::handleError);
    }
}
