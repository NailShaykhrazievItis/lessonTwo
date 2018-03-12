package com.itis.android.lessontwo.ui.creators;

import com.itis.android.lessontwo.api.ApiFactory;
import com.itis.android.lessontwo.model.creators.CreatorsResponse;
import com.itis.android.lessontwo.model.creators.CreatorsResponseData;
import com.itis.android.lessontwo.repository.RepositoryProvider;
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
        RepositoryProvider.provideCreatorsRepository()
                .creators(id)
                .subscribe(view::showCreators, view::handleError);
    }
}
