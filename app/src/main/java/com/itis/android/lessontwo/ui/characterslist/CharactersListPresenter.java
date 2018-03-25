package com.itis.android.lessontwo.ui.characterslist;

import android.support.annotation.VisibleForTesting;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.itis.android.lessontwo.model.entity.character.Character;
import com.itis.android.lessontwo.repository.RepositoryProvider;

import static com.itis.android.lessontwo.utils.Constants.DEFAULT_CHARACTER_SORT;
import static com.itis.android.lessontwo.utils.Constants.PAGE_SIZE;
import static com.itis.android.lessontwo.utils.Constants.ZERO_OFFSET;

/**
 * Created by Ruslan on 02.03.2018.
 */
@InjectViewState
public class CharactersListPresenter extends MvpPresenter<CharactersListView> {

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
        load();
    }

    @VisibleForTesting
    public void load() {
        RepositoryProvider.provideCharacterRepository()
                .characters(ZERO_OFFSET, PAGE_SIZE, DEFAULT_CHARACTER_SORT)
                .doOnSubscribe(getViewState()::showLoading)
                .doAfterTerminate(getViewState()::hideLoading)
                .subscribe(getViewState()::showItems, getViewState()::handleError);
    }

    public void loadNextElements(int page) {
        RepositoryProvider.provideCharacterRepository()
                .characters(page * PAGE_SIZE, PAGE_SIZE, DEFAULT_CHARACTER_SORT)
                .doOnSubscribe(getViewState()::showLoading)
                .doAfterTerminate(getViewState()::hideLoading)
                .doAfterTerminate(getViewState()::setNotLoading)
                .subscribe(getViewState()::addMoreItems, getViewState()::handleError);
    }

    public void onItemClick(Character character) {
        getViewState().showDetails(character);
    }
}
