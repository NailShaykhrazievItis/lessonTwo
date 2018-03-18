package com.itis.android.lessontwo.ui.storieslist;

import com.itis.android.lessontwo.model.story.Story;
import com.itis.android.lessontwo.repository.RepositoryProvider;

import static com.itis.android.lessontwo.utils.Constants.PAGE_SIZE;
import static com.itis.android.lessontwo.utils.Constants.ZERO_OFFSET;

/**
 * Created by User on 16.03.2018.
 */

public class StoriesListPresenter implements StoriesListContract.Presenter {

    private final StoriesListContract.View view;

    public StoriesListPresenter(StoriesListContract.View view) {
        this.view = view;
        this.view.setPresenter(this);
    }

    @Override
    public void loadStories() {
        RepositoryProvider.provideStoriesRepostitory()
                .stories(ZERO_OFFSET, PAGE_SIZE)
                .subscribe(view::showItems, view::handleError);
    }

    @Override
    public void loadNextElements(int page) {
        RepositoryProvider.provideStoriesRepostitory()
                .stories(page * PAGE_SIZE, PAGE_SIZE)
                .subscribe(view::addMoreItems, view::handleError);
    }

    @Override
    public void onItemClick(Story story) {
        view.showDetails(story);
    }
}
