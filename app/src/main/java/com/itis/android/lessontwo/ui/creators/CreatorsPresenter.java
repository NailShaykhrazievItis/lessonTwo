package com.itis.android.lessontwo.ui.creators;

import com.arellomobile.mvp.MvpPresenter;
import com.itis.android.lessontwo.api.ApiFactory;
import com.itis.android.lessontwo.model.entity.creators.Creator;
import com.itis.android.lessontwo.model.entity.creators.CreatorsResponse;
import com.itis.android.lessontwo.model.entity.creators.CreatorsResponseData;
import com.itis.android.lessontwo.repository.RepositoryProvider;
import com.itis.android.lessontwo.ui.base.BaseContract;
import com.itis.android.lessontwo.utils.RxUtils;

/**
 * Created by valera071998@gmail.com on 02.03.2018.
 */

public class CreatorsPresenter extends MvpPresenter<CreatorsView> {

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
        getViewState().getCreatorById();
    }

    public void load(long id) {
        RepositoryProvider.provideCreatorRepository()
                .creator(id)
                .subscribe(
                        creator -> {
                            getViewState().setName(creator);
                            getViewState().setImage(creator);
                            getViewState().setDescription(creator);
                        }, getViewState()::handleError);
    }
}
