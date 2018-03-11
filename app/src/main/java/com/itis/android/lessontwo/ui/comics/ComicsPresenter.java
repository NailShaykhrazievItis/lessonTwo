package com.itis.android.lessontwo.ui.comics;

import com.itis.android.lessontwo.model.entity.comics.Comics;
import com.itis.android.lessontwo.repository.RepositoryProvider;
import com.itis.android.lessontwo.ui.base.BaseContract;

/**
 * Created by Ruslan on 02.03.2018.
 */

public class ComicsPresenter implements BaseContract.Presenter {

    private final BaseContract.View<Comics> view;

    public ComicsPresenter(BaseContract.View<Comics> view) {
        this.view = view;
        this.view.setPresenter(this);
    }

    @Override
    public void load(long id) {
        RepositoryProvider.provideComicsRepository()
                .comics(id)
                .subscribe(this.view::show, this.view::handleError);
    }
}
