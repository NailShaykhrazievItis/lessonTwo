package com.itis.android.lessontwo.ui.comics;

import com.itis.android.lessontwo.api.ApiFactory;
import com.itis.android.lessontwo.model.comics.ComicsResponse;
import com.itis.android.lessontwo.model.comics.ComicsResponseData;
import com.itis.android.lessontwo.ui.comics.ComicsContract.View;
import com.itis.android.lessontwo.utils.RxUtils;

public class ComicsPresenter implements ComicsContract.Presenter {

    private final View view;

    public ComicsPresenter(View view) {
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
