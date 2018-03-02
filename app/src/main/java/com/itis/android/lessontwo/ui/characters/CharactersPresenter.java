package com.itis.android.lessontwo.ui.characters;

import com.itis.android.lessontwo.api.ApiFactory;
import com.itis.android.lessontwo.model.character.CharactersResponse;
import com.itis.android.lessontwo.model.character.CharactersResponseData;
import com.itis.android.lessontwo.utils.RxUtils;

/**
 * Created by Ruslan on 02.03.2018.
 */

public class CharactersPresenter implements CharactersContract.Presenter {

    private final CharactersContract.View view;

    public CharactersPresenter(CharactersContract.View view) {
        this.view = view;
        this.view.setPresenter(this);
    }

    @Override
    public void loadCharacter(long id) {
        ApiFactory.getCharactersService()
                .character(id)
                .map(CharactersResponse::getData)
                .map(CharactersResponseData::getResults)
                .map(list -> list.get(0))
                .compose(RxUtils.async())
                .subscribe(this.view::showCharacter, this.view::handleError);
    }
}
