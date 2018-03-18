package com.itis.android.lessontwo.ui.creators;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.itis.android.lessontwo.repository.RepositoryProvider;

/**
 * Created by valera071998@gmail.com on 02.03.2018.
 */
@InjectViewState
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
