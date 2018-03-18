package com.itis.android.lessontwo.ui.characterlist;

import static com.itis.android.lessontwo.utils.Constants.PAGE_SIZE;
import static com.itis.android.lessontwo.utils.Constants.ZERO_OFFSET;

import com.itis.android.lessontwo.model.character.Character;
import com.itis.android.lessontwo.repository.RepositoryProvider;
import com.itis.android.lessontwo.ui.characterlist.CharacterListContract.View;

public class CharacterListPresenter implements CharacterListContract.Presenter {

    private final CharacterListContract.View view;

    public CharacterListPresenter(final View view) {
        this.view = view;
        this.view.setPresenter(this);
    }

    public void loadCharacters() {
        RepositoryProvider.provideCharacterRepository()
                .characters(ZERO_OFFSET, PAGE_SIZE)
                .doOnSubscribe(view::showLoading)
                .doAfterTerminate(view::hideLoading)
                .subscribe(view::showItems, view::handleError);
    }

    @Override
    public void loadNextElements(final int page) {
        RepositoryProvider.provideCharacterRepository()
                .characters(page * PAGE_SIZE, PAGE_SIZE)
                .doOnSubscribe(view::showLoading)
                .doAfterTerminate(view::hideLoading)
                .doAfterTerminate(view::setNotLoading)
                .subscribe(view::addMoreItems, view::handleError);
    }

    @Override
    public void onItemClick(final Character character) {
        view.showDetails(character);
    }
}
