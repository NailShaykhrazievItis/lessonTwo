package com.itis.android.lessontwo.ui.character;

import com.itis.android.lessontwo.api.ApiFactory;
import com.itis.android.lessontwo.model.character.CharactersResponse;
import com.itis.android.lessontwo.model.character.CharactersResponseData;
import com.itis.android.lessontwo.utils.RxUtils;

/**
 * Created by a9 on 03.03.18.
 */

public class CharacterPresenter implements CharacterContract.Presenter {

    private final CharacterContract.View view;

    public CharacterPresenter(CharacterContract.View view) {
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
                .subscribe(view::showCharacter, view::handleError);
    }
}
