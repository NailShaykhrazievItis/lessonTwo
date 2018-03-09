package com.itis.android.lessontwo.ui.characters_list;

import com.itis.android.lessontwo.api.ApiFactory;
import com.itis.android.lessontwo.model.character.Character;
import com.itis.android.lessontwo.model.character.CharactersResponse;
import com.itis.android.lessontwo.model.character.CharactersResponseData;
import com.itis.android.lessontwo.utils.RxUtils;

import static com.itis.android.lessontwo.utils.Constants.DEFAULT_CHARACTER_SORT;
import static com.itis.android.lessontwo.utils.Constants.PAGE_SIZE;
import static com.itis.android.lessontwo.utils.Constants.ZERO_OFFSET;

/**
 * Created by Ruslan on 02.03.2018.
 */

public class CharactersListPresenter implements CharactersListContract.Presenter {

    private final CharactersListContract.View view;

    public CharactersListPresenter(CharactersListContract.View view) {
        this.view = view;
        this.view.setPresenter(this);
    }

    @Override
    public void loadCharacters() {
        ApiFactory.getCharactersService()
                .characters(ZERO_OFFSET, PAGE_SIZE, DEFAULT_CHARACTER_SORT)
                .map(CharactersResponse::getData)
                .map(CharactersResponseData::getResults)
                .compose(RxUtils.async())
                .subscribe(view::showItems, view::handleError);
    }

    @Override
    public void loadNextElements(int page) {
        ApiFactory.getCharactersService()
                .characters(page * PAGE_SIZE, PAGE_SIZE, DEFAULT_CHARACTER_SORT)
                .map(CharactersResponse::getData)
                .map(CharactersResponseData::getResults)
                .doOnTerminate(view::setNotLoading)
                .compose(RxUtils.async())
                .subscribe(view::addMoreItems, view::handleError);
    }

    @Override
    public void onItemClick(Character character) {
        view.showDetails(character);
    }
}
