package com.itis.android.lessontwo.ui.creators;

import com.itis.android.lessontwo.api.ApiFactory;

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
        ApiFactory.getComicsService()
                .comics(id)
                .map(ComicsResponse::getData)
                .map(ComicsResponseData::getResults)
                .map(list -> list.get(0))
                .compose(RxUtils.async())
                .subscribe(view::showComics, view::handleError);
    }
}
