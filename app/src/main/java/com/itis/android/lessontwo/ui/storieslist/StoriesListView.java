package com.itis.android.lessontwo.ui.storieslist;

import android.support.annotation.NonNull;
import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;
import com.itis.android.lessontwo.model.story.Story;
import io.reactivex.disposables.Disposable;
import java.util.List;

/**
 * Created by User on 16.03.2018.
 */
@StateStrategyType(AddToEndSingleStrategy.class)
public interface StoriesListView extends MvpView{

    void showItems(@NonNull List<Story> items);

    void addMoreItems(List<Story> items);

    void setNotLoading();

    void showLoading(Disposable disposable);

    void hideLoading();

    @StateStrategyType(OneExecutionStateStrategy.class)
    void handleError(Throwable error);
}
