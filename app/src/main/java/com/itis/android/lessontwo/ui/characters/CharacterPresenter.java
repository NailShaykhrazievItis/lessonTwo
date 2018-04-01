package com.itis.android.lessontwo.ui.characters;

import android.support.annotation.VisibleForTesting;
import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.itis.android.lessontwo.repository.RepositoryProvider;

/**
 * Created by User on 04.03.2018.
 */
@InjectViewState
public class CharacterPresenter extends MvpPresenter<CharacterView> {

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
        getViewState().getCharacterId();
    }

    @VisibleForTesting
    void init(Long id) {
        RepositoryProvider.provideCharactersRepository()
                .characters(id)
                .subscribe(character -> {
                    getViewState().setImage(character);
                    getViewState().setDescription(character);
                }, getViewState()::handleError);
    }
}
