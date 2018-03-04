package com.itis.android.lessontwo.ui.characterslist;

import android.support.annotation.NonNull;

import com.itis.android.lessontwo.model.character.Character;
import com.itis.android.lessontwo.ui.base.BaseView;

import java.util.List;

import io.reactivex.disposables.Disposable;

/**
 * Created by User on 04.03.2018.
 */

public interface CharactersListContract {

    interface View extends BaseView<Presenter> {

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

        void onItemClick(Character comics);
    }
}
