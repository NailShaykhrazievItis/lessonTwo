package com.itis.android.lessontwo.ui.characters;

import com.itis.android.lessontwo.api.ApiFactory;
import com.itis.android.lessontwo.model.character.Character;
import com.itis.android.lessontwo.model.character.CharactersResponse;
import com.itis.android.lessontwo.model.character.CharactersResponseData;
import com.itis.android.lessontwo.utils.RxUtils;

/**
 * Created by User on 04.03.2018.
 */

public class CharacterPresenter implements CharacterContract.Presenter {

    private final CharacterContract.View view;

    public CharacterPresenter(CharacterContract.View view) {
        this.view = view;
        this.view.setPresenter(this);
    }

    @Override
    public void initCharacter(long id) {
        ApiFactory.getComicsService()
                .characters(id)
                .map(CharactersResponse::getData)
                .map(CharactersResponseData::getResults)
                .map(list -> list.get(0))
                .compose(RxUtils.async())
                .subscribe(view::showCharacter, view::handleError);
    }
}
