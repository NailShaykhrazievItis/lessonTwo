package com.itis.android.lessontwo.ui.characters;

import android.support.annotation.VisibleForTesting;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.itis.android.lessontwo.repository.RepositoryProvider;

/**
 * Created by Tony on 3/18/2018.
 */

@InjectViewState
public class CharacterPresenter extends MvpPresenter<CharacterView> {

    @Override
    public void onFirstViewAttach() {
        super.onFirstViewAttach();
        getViewState().getCharacterId();
    }

    public void init(Long id) {
        RepositoryProvider.provideCharactersRepostitory()
                .characters(id)
                .subscribe(character-> {
                    getViewState().setImage(character);
                    }, getViewState()::handleError);
        }
}
