package com.itis.android.lessontwo.ui.creators;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.itis.android.lessontwo.api.ApiFactory;
import com.itis.android.lessontwo.model.creators.CreatorsResponse;
import com.itis.android.lessontwo.model.creators.CreatorsResponseData;
import com.itis.android.lessontwo.repository.RepositoryProvider;
import com.itis.android.lessontwo.ui.characters.CharacterView;
import com.itis.android.lessontwo.utils.RxUtils;

/**
 * Created by Tony on 3/5/2018.
 */

@InjectViewState
public class CreatorPresenter extends MvpPresenter<CreatorsView> {

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
        getViewState().getCreatorId();
    }

    public void init(Long id) {
        RepositoryProvider.provideCreatorsRepository()
                .creators(id)
                .subscribe(creators-> {
                    getViewState().setImage(creators);
                    getViewState().setName(creators);
                }, getViewState()::handleError);
    }
}
