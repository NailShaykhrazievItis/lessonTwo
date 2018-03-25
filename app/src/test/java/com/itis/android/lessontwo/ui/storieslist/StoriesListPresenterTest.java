package com.itis.android.lessontwo.ui.storieslist;

import static org.mockito.ArgumentMatchers.*;

import android.support.annotation.NonNull;
import com.itis.android.lessontwo.api.ApiFactory;
import com.itis.android.lessontwo.model.story.Story;
import com.itis.android.lessontwo.repository.RepositoryProvider;
import com.itis.android.lessontwo.repository.StoriesRepository;
import com.itis.android.lessontwo.repository.StoriesRepositoryImpl;
import com.itis.android.lessontwo.utils.RxUtils;
import io.reactivex.Single;
import java.util.ArrayList;
import java.util.List;
import org.junit.*;
import org.junit.runner.*;
import org.mockito.*;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

/**
 * Created by Admin on 25.03.2018.
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest({RxUtils.class, ApiFactory.class})
public class StoriesListPresenterTest {

    @SuppressWarnings("WeakAccess")
    @Mock
    StoriesListView$$State viewState;

    @SuppressWarnings("WeakAccess")
    @Mock
    StoriesRepositoryImpl repository;

    private StoriesListPresenter presenter;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        presenter = Mockito.spy(StoriesListPresenter.class);
        presenter.setViewState(viewState);
        PowerMockito.mockStatic(ApiFactory.class);
        PowerMockito.mockStatic(RxUtils.class);
    }

    @Test
    public void onFirstViewAttach() throws Exception {
        // Arrange
        Mockito.doNothing().when(presenter).loadStories();
        // Act
        presenter.onFirstViewAttach();
        // Assert
        Mockito.verify(presenter).loadStories();
    }

    @Test
    public void loadNextElementsError() throws Exception {
        // Arrange
        List<Story> storyList = new ArrayList<>();
        Story story = Mockito.mock(Story.class);
        RepositoryProvider.setStoriesRepository(new TestRepository(true, story, storyList));
        //Act
        presenter.loadStories();
        // Assert
        Mockito.verify(viewState).showLoading(any());
        Mockito.verify(viewState).hideLoading();
        Mockito.verify(viewState).handleError(any(Throwable.class));
    }

    @Test
    public void loadNextElementsSuccess() throws Exception {
        // Arrange
        List<Story> storyList = new ArrayList<>();
        Story story = Mockito.mock(Story.class);
        RepositoryProvider.setStoriesRepository(new TestRepository(false, story, storyList));
        //Act
        presenter.loadStories();
        // Assert
        Mockito.verify(viewState).showLoading(any());
        Mockito.verify(viewState).hideLoading();
        Mockito.verify(viewState).showItems(storyList);
    }

    @Test
    public void loadCharactersMockError() throws Exception {
        // Arrange
        Mockito.when(repository.stories(anyLong(), anyLong()))
                .thenReturn(Single.error(new Throwable()));
        RepositoryProvider.setStoriesRepository(repository);
        // Act
        presenter.loadStories();
        // Assert
        Mockito.verify(viewState).showLoading(any());
        Mockito.verify(viewState).hideLoading();
        Mockito.verify(viewState).handleError(any(Throwable.class));
    }

    @Test
    public void loadCharactersMockSuccess() {
        // Arrange
        List<Story> storyList = new ArrayList<>();
        Mockito.when(repository.stories(anyLong(), anyLong()))
                .thenReturn(Single.just(storyList));
        RepositoryProvider.setStoriesRepository(repository);
        // Act
        presenter.loadStories();
        // Assert
        Mockito.verify(viewState).showLoading(any());
        Mockito.verify(viewState).hideLoading();
        Mockito.verify(viewState).showItems(storyList);
    }

    @Test
    public void doActionInView() throws Exception {
        Mockito.verifyNoMoreInteractions(viewState);
    }

    private class TestRepository implements StoriesRepository {

        private boolean error;
        private Story story;
        private List<Story> storyList;

        public TestRepository(boolean error, Story story, List<Story> storyList) {
            this.error = error;
            this.story = story;
            this.storyList = storyList;
        }

        @NonNull
        @Override
        public Single<List<Story>> stories(Long offset, Long limit) {
            if (this.error) {
                return Single.error(new Throwable());
            } else {
                return Single.just(this.storyList);
            }
        }

        @Override
        public Single<Story> stories(Long id) {
            if (this.error) {
                return Single.error(new Throwable());
            } else {
                return Single.just(this.story);
            }
        }
    }
}