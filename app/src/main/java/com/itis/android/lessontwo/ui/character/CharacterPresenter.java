package com.itis.android.lessontwo.ui.character;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.itis.android.lessontwo.repository.RepositoryProvider;

@InjectViewState
public class CharacterPresenter extends MvpPresenter<CharacterView> {

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
        getViewState().getCharacterId();
    }

    public void init(final long id) {
        RepositoryProvider.provideCharacterRepository()
                .character(id)
                .subscribe(character -> {
                    getViewState().setCharacterImage(character);
                    getViewState().setCharacterName(character);
                    getViewState().setCharacterDescription(character);
                        }, getViewState()::handleError);
    }
}
