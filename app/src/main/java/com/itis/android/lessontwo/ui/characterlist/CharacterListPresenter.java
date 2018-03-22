package com.itis.android.lessontwo.ui.characterlist;

import static com.itis.android.lessontwo.utils.Constants.PAGE_SIZE;
import static com.itis.android.lessontwo.utils.Constants.ZERO_OFFSET;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.itis.android.lessontwo.model.character.Character;
import com.itis.android.lessontwo.repository.RepositoryProvider;

@InjectViewState
public class CharacterListPresenter extends MvpPresenter<CharacterListView> {

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
        loadCharacters();
    }

    public void loadCharacters() {
        RepositoryProvider.provideCharacterRepository()
                .characters(ZERO_OFFSET, PAGE_SIZE)
                .doOnSubscribe(getViewState()::showLoading)
                .doAfterTerminate(getViewState()::hideLoading)
                .subscribe(getViewState()::showItems, getViewState()::handleError);
    }

    public void loadNextElements(final int page) {
        RepositoryProvider.provideCharacterRepository()
                .characters(page * PAGE_SIZE, PAGE_SIZE)
                .doOnSubscribe(getViewState()::showLoading)
                .doAfterTerminate(getViewState()::hideLoading)
                .doAfterTerminate(getViewState()::setNotLoading)
                .subscribe(getViewState()::addMoreItems, getViewState()::handleError);
    }

    public void onItemClick(final Character character) {
        getViewState().showDetails(character);
    }
}
