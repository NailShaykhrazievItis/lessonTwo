package com.itis.android.lessontwo.ui.character;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.itis.android.lessontwo.repository.RepositoryProvider;
import com.itis.android.lessontwo.ui.character.CharacterContract.View;

@InjectViewState
public class CharacterPresenter extends MvpPresenter<CharacterContract.View> implements CharacterContract.Presenter {

    public CharacterPresenter() {
    }

    @Override
    public void loadCharacter(final long id) {
        RepositoryProvider.provideCharacterRepository()
                .character(id)
                .subscribe(getViewState()::showCharacter, getViewState()::handleError);
    }
}
