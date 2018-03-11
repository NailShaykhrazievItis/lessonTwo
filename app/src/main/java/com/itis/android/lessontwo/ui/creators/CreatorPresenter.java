package com.itis.android.lessontwo.ui.creators;

import com.itis.android.lessontwo.api.ApiFactory;
import com.itis.android.lessontwo.model.creator.CreatorResponse;
import com.itis.android.lessontwo.model.creator.CreatorResponseData;
import com.itis.android.lessontwo.repository.RepositoryProvider;
import com.itis.android.lessontwo.ui.creators.CreatorContract.View;
import com.itis.android.lessontwo.utils.RxUtils;

/**
 * Created by Blaheart on 05.03.2018.
 */

public class CreatorPresenter implements CreatorContract.Presenter {

    private final View view;

    public CreatorPresenter(View view) {
        this.view = view;
        this.view.setPresenter(this);
    }

    @Override
    public void loadCreator(long id) {
        RepositoryProvider.provideCreatorRepository()
                .creator(id)
                .subscribe(view::showCreator, view::handleError);
    }
}
