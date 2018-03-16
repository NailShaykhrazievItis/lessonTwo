package com.itis.android.lessontwo.ui.characters;

import com.itis.android.lessontwo.model.entity.character.Character;
import com.itis.android.lessontwo.repository.RepositoryProvider;

/**
 * Created by Ruslan on 02.03.2018.
 */

public class CharactersPresenter implements BaseContract.Presenter {

    private final BaseContract.View<Character> view;

    public CharactersPresenter(BaseContract.View<Character> view) {
        this.view = view;
        this.view.setPresenter(this);
    }

    @Override
    public void load(long id) {
        RepositoryProvider.provideCharacterRepository()
                .character(id)
                .subscribe(this.view::show, this.view::handleError);
    }
}
