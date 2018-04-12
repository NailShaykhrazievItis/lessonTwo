package com.itis.android.lessontwo.ui.character;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import com.itis.android.lessontwo.repository.RepositoryProvider;

/**
 * Created by a9 on 03.03.18.
 */
@InjectViewState
public class CharacterPresenter extends MvpPresenter<CharacterView> {

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
        getViewState().getCharacterId();
    }

    public void init(long id) {
        RepositoryProvider.provideCharacterRepository()
                .character(id)
                .subscribe(character -> {
                    getViewState().setDescription(character);
                    getViewState().setImage(character);
                }, getViewState()::handleError);
    }
}
