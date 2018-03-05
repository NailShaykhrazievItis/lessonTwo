package com.itis.android.lessontwo.ui.characterslist;

import com.itis.android.lessontwo.api.ApiFactory;
import com.itis.android.lessontwo.model.character.Character;
import com.itis.android.lessontwo.model.character.CharactersResponse;
import com.itis.android.lessontwo.model.character.CharactersResponseData;
import com.itis.android.lessontwo.utils.RxUtils;

import static com.itis.android.lessontwo.utils.Constants.PAGE_SIZE;
import static com.itis.android.lessontwo.utils.Constants.ZERO_OFFSET;

/**
 * Created by User on 04.03.2018.
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
                .characters(ZERO_OFFSET, PAGE_SIZE)
                .map(CharactersResponse::getData)
                .map(CharactersResponseData::getResults)
//                .doOnSubscribe(view::showLoading)
//                .doOnTerminate(view::hideLoading)
                .compose(RxUtils.async())
                .subscribe(view::showItems, view::handleError);
    }

    @Override
    public void loadNextElements(int page) {
        ApiFactory.getCharactersService()
                .characters(page * PAGE_SIZE, PAGE_SIZE)
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
