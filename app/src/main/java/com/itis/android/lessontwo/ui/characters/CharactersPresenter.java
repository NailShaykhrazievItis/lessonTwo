package com.itis.android.lessontwo.ui.characters;

import com.itis.android.lessontwo.api.ApiFactory;
import com.itis.android.lessontwo.model.character.Character;
import com.itis.android.lessontwo.model.character.CharactersResponse;
import com.itis.android.lessontwo.model.character.CharactersResponseData;
import com.itis.android.lessontwo.ui.base.BaseContract;
import com.itis.android.lessontwo.utils.RxUtils;

/**
 * Created by Ruslan on 02.03.2018.
 */

public class CharactersPresenter implements BaseContract.Presenter {

    private final BaseContract.View<Character> view;

    public CharactersPresenter(BaseContract.View<Character> view) {
        this.view = view;
        this.view.setPresenter(this);
    }

    @Override
    public void load(long id) {
        ApiFactory.getCharactersService()
                .character(id)
                .map(CharactersResponse::getData)
                .map(CharactersResponseData::getResults)
                .map(list -> list.get(0))
                .compose(RxUtils.async())
                .subscribe(this.view::show, this.view::handleError);
    }
}
