package com.itis.android.lessontwo.ui.characters;

import com.itis.android.lessontwo.repository.RepositoryProvider;

/**
 * Created by User on 04.03.2018.
 */

public class CharacterPresenter implements CharacterContract.Presenter {

    private final CharacterContract.View view;

    public CharacterPresenter(CharacterContract.View view) {
        this.view = view;
        this.view.setPresenter(this);
    }

    @Override
    public void initCharacter(long id) {
        RepositoryProvider.provideCharactersRepostitory()
                .characters(id)
                .subscribe(view::showCharacter, view::handleError);
    }
}
