package com.itis.android.lessontwo.ui.creators;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.itis.android.lessontwo.api.ApiFactory;
import com.itis.android.lessontwo.model.creator.CreatorResponse;
import com.itis.android.lessontwo.model.creator.CreatorResponseData;
import com.itis.android.lessontwo.repository.RepositoryProvider;
import com.itis.android.lessontwo.ui.creators.CreatorContract.View;
import com.itis.android.lessontwo.utils.RxUtils;

/**
 * Created by Blaheart on 05.03.2018.
 */

@InjectViewState
public class CreatorPresenter extends MvpPresenter<CreatorContract.View> implements CreatorContract.Presenter {

    @Override
    public void loadCreator(long id) {
        RepositoryProvider.provideCreatorRepository()
                .creator(id)
                .subscribe(getViewState()::showCreator, getViewState()::handleError);
    }
}
