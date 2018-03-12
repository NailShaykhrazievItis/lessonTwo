package com.itis.android.lessontwo.ui.character;

import com.itis.android.lessontwo.repository.RepositoryProvider;

/**
 * Created by a9 on 03.03.18.
 */

public class CharacterPresenter implements CharacterContract.Presenter {

    private final CharacterContract.View view;

    public CharacterPresenter(CharacterContract.View view) {
        this.view = view;
        this.view.setPresenter(this);
    }

    @Override
    public void loadCharacter(long id) {
        RepositoryProvider.provideCharacterRepository()
                .character(id)
                .subscribe(view::showCharacter, view::handleError);
    }
}
