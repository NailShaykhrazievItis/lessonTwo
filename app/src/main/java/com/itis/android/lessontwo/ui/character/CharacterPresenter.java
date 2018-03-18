package com.itis.android.lessontwo.ui.character;

import com.itis.android.lessontwo.repository.RepositoryProvider;
import com.itis.android.lessontwo.ui.character.CharacterContract.View;

public class CharacterPresenter implements CharacterContract.Presenter {

    private final View view;

    public CharacterPresenter(View view) {
        this.view = view;
        this.view.setPresenter(this);
    }

    @Override
    public void loadCharacter(final long id) {
        RepositoryProvider.provideCharacterRepository()
                .character(id)
                .subscribe(view::showCharacter, view::handleError);
    }
}
