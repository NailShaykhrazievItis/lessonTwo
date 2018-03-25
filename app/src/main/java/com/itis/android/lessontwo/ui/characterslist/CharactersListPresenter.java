package com.itis.android.lessontwo.ui.characterslist;

import android.support.annotation.VisibleForTesting;

import static com.itis.android.lessontwo.utils.Constants.DEFAULT_COMICS_SORT;
import static com.itis.android.lessontwo.utils.Constants.PAGE_SIZE;
import static com.itis.android.lessontwo.utils.Constants.ZERO_OFFSET;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.itis.android.lessontwo.model.character.Character;
import com.itis.android.lessontwo.repository.RepositoryProvider;

/**
 * Created by User on 04.03.2018.
 */
@InjectViewState
public class CharactersListPresenter extends MvpPresenter<CharactersListView> {

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
        loadCharacters();
    }

    void loadNextElements(int page) {
        RepositoryProvider.provideCharactersRepostitory()
                .characters(page * PAGE_SIZE, PAGE_SIZE)
                .doOnSubscribe(getViewState()::showLoading)
                .doAfterTerminate(getViewState()::hideLoading)
                .doAfterTerminate(getViewState()::setNotLoading)
                .subscribe(getViewState()::addMoreItems, getViewState()::handleError);

    }

    void onItemClick(Character character) {
        getViewState().showDetails(character);
    }

    @VisibleForTesting
    void loadCharacters() {
        RepositoryProvider.provideCharactersRepostitory()
                .characters(ZERO_OFFSET, PAGE_SIZE)
                .doOnSubscribe(getViewState()::showLoading)
                .doAfterTerminate(getViewState()::hideLoading)
                .subscribe(getViewState()::showItems, getViewState()::handleError);
    }
}
