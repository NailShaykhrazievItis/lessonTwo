package com.itis.android.lessontwo.ui.creatorslist;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.itis.android.lessontwo.model.creator.Creator;
import com.itis.android.lessontwo.repository.RepositoryProvider;

import static com.itis.android.lessontwo.utils.Constants.PAGE_SIZE;
import static com.itis.android.lessontwo.utils.Constants.ZERO_OFFSET;

/**
 * Created by Bulat Murtazin on 05.03.2018.
 * KPFU ITIS 11-601
 */

@InjectViewState
public class CreatorListPresenter extends MvpPresenter<CreatorListView> {

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
        loadCreators();
    }

    public void loadCreators() {
        RepositoryProvider.provideCreatorRepository()
                .creators(ZERO_OFFSET, PAGE_SIZE)
                .doOnSubscribe(getViewState()::showLoading)
                .doAfterTerminate(getViewState()::hideLoading)
                .subscribe(getViewState()::showItems, getViewState()::handleError);
    }

    public void loadNextElements(int page) {
        RepositoryProvider.provideCreatorRepository()
                .creators(page * PAGE_SIZE, PAGE_SIZE)
                .doOnSubscribe(getViewState()::showLoading)
                .doAfterTerminate(getViewState()::hideLoading)
                .doAfterTerminate(getViewState()::setNotLoading)
                .subscribe(getViewState()::addMoreItems, getViewState()::handleError);
    }

    public void onItemClick(Creator creator) {
        getViewState().showDetails(creator);
    }
}
