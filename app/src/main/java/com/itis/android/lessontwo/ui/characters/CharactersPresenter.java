package com.itis.android.lessontwo.ui.characters;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.itis.android.lessontwo.repository.RepositoryProvider;

/**
 * Created by Ruslan on 02.03.2018.
 */
@InjectViewState
public class CharactersPresenter extends MvpPresenter<CharactersView> {

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
        getViewState().getCharacterId();
    }

    public void load(long id) {
        RepositoryProvider.provideCharacterRepository()
                .character(id)
                .subscribe(
                        character -> {
                            getViewState().setName(character);
                            getViewState().setImage(character);
                            getViewState().setDescription(character);
                        }, getViewState()::handleError);
    }
}
