package com.itis.android.lessontwo.ui.creators;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.itis.android.lessontwo.repository.RepositoryProvider;

/**
 * Created by Blaheart on 05.03.2018.
 */

@InjectViewState
public class CreatorPresenter extends MvpPresenter<CreatorView> {

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
        getViewState().getCreatorId();
    }

    public void init(long id) {
        RepositoryProvider.provideCreatorRepository()
                .creator(id)
                .subscribe(creator -> {
                    getViewState().setImage(creator);
                    getViewState().setName(creator);
                    getViewState().setStories(creator);
                }, getViewState()::handleError);
    }
}
