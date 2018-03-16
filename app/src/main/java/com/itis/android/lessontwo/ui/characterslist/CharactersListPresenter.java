package com.itis.android.lessontwo.ui.characterslist;

import com.itis.android.lessontwo.model.entity.character.Character;
import com.itis.android.lessontwo.repository.RepositoryProvider;

import static com.itis.android.lessontwo.utils.Constants.DEFAULT_CHARACTER_SORT;
import static com.itis.android.lessontwo.utils.Constants.PAGE_SIZE;
import static com.itis.android.lessontwo.utils.Constants.ZERO_OFFSET;

/**
 * Created by Ruslan on 02.03.2018.
 */

public class CharactersListPresenter implements BaseListContract.Presenter<Character> {

    private final BaseListContract.View<Character> view;

    public CharactersListPresenter(BaseListContract.View<Character> view) {
        this.view = view;
        this.view.setPresenter(this);
    }

    @Override
    public void load() {
        RepositoryProvider.provideCharacterRepository()
                .characters(ZERO_OFFSET, PAGE_SIZE, DEFAULT_CHARACTER_SORT)
                .doOnSubscribe(view::showLoading)
                .doAfterTerminate(view::hideLoading)
                .subscribe(view::showItems, view::handleError);
    }

    @Override
    public void loadNextElements(int page) {
        RepositoryProvider.provideCharacterRepository()
                .characters(page * PAGE_SIZE, PAGE_SIZE, DEFAULT_CHARACTER_SORT)
                .doOnSubscribe(view::showLoading)
                .doAfterTerminate(view::hideLoading)
                .doAfterTerminate(view::setNotLoading)
                .subscribe(view::addMoreItems, view::handleError);
    }

    @Override
    public void onItemClick(Character character) {
        view.showDetails(character);
    }
}
