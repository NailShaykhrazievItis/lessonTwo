package com.itis.android.lessontwo.ui.creators;

import com.itis.android.lessontwo.api.ApiFactory;
import com.itis.android.lessontwo.model.entity.creators.Creator;
import com.itis.android.lessontwo.model.entity.creators.CreatorsResponse;
import com.itis.android.lessontwo.model.entity.creators.CreatorsResponseData;
import com.itis.android.lessontwo.repository.RepositoryProvider;
import com.itis.android.lessontwo.ui.base.BaseContract;
import com.itis.android.lessontwo.utils.RxUtils;

/**
 * Created by valera071998@gmail.com on 02.03.2018.
 */

public class CreatorsPresenter implements BaseContract.Presenter {

    private final BaseContract.View<Creator> view;

    public CreatorsPresenter(BaseContract.View<Creator> view) {
        this.view = view;
        this.view.setPresenter(this);
    }

    @Override
    public void load(long id) {
        RepositoryProvider.provideCreatorRepository()
                .creator(id)
                .subscribe(this.view::show, this.view::handleError);
    }
}
