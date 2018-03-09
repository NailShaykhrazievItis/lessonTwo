package com.itis.android.lessontwo.ui.characters_list;

import android.support.annotation.NonNull;

import com.itis.android.lessontwo.model.character.Character;
import com.itis.android.lessontwo.ui.base.BaseView;

import java.util.List;

import io.reactivex.disposables.Disposable;

/**
 * Created by Ruslan on 02.03.2018.
 */

public interface CharactersListContract {

    interface View extends BaseView<CharactersListContract.Presenter> {

        void showItems(@NonNull List<Character> items);

        void showDetails(Character item);

        void addMoreItems(List<Character> items);

        void setNotLoading();

        void showLoading(Disposable disposable);

        void hideLoading();
    }

    interface Presenter {

        void loadCharacters();

        void loadNextElements(int page);

        void onItemClick(Character character);
    }
}
