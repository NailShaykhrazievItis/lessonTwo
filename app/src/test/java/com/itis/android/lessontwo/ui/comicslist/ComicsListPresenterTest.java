package com.itis.android.lessontwo.ui.comicslist;

import android.support.annotation.NonNull;

import com.itis.android.lessontwo.api.ApiFactory;
import com.itis.android.lessontwo.model.comics.Comics;
import com.itis.android.lessontwo.repository.ComicsRepository;
import com.itis.android.lessontwo.repository.ComicsRepositoryImpl;
import com.itis.android.lessontwo.repository.RepositoryProvider;
import com.itis.android.lessontwo.utils.RxUtils;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Single;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.powermock.api.mockito.PowerMockito.mockStatic;


@RunWith(PowerMockRunner.class)
@PrepareForTest({RxUtils.class, ApiFactory.class})
public class ComicsListPresenterTest {

    @SuppressWarnings("WeakerAccess")
    @Mock
    ComicsListView$$State viewState;

    @SuppressWarnings("WeakerAccess")
    @Mock
    ComicsRepositoryImpl repository;

    private ComicsListPresenter presenter;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        presenter = Mockito.spy(ComicsListPresenter.class);
        presenter.setViewState(viewState);
        mockStatic(ApiFactory.class);
        mockStatic(RxUtils.class);
    }

    @Test
    public void onFirstViewAttach() {
        // Arrange
        Mockito.doNothing().when(presenter).loadComics();
        // Act
        presenter.onFirstViewAttach();
        // Assert
        Mockito.verify(presenter).loadComics();
    }

    @Test
    public void loadComicsMockError() {
        // Arrange
        Mockito.when(repository.comics(anyLong(), anyLong(), anyString()))
                        .thenReturn(Single.error(new Throwable()));
        // Act
        presenter.loadComics();
        // Assert
        Mockito.verify(viewState).showLoading(Mockito.any());
        Mockito.verify(viewState).hideLoading();
        Mockito.verify(viewState).handleError(Mockito.any(Throwable.class));
    }

    @Test
    public void loadComicsMockSuccess() {
        // Arrange
        List<Comics> comicsList = new ArrayList<>();
        Mockito.when(repository.comics(anyLong(), anyLong(), anyString()))
                .thenReturn(Single.just(comicsList));
        // Act
        presenter.loadComics();
        // Assert
        Mockito.verify(viewState).showLoading(Mockito.any());
        Mockito.verify(viewState).hideLoading();
        Mockito.verify(viewState).showItems(comicsList);
    }

    @Test
    public void loadComicsError() {
        // Arrange
        List<Comics> comicsList = new ArrayList<>();
        Comics comics = Mockito.mock(Comics.class);
        RepositoryProvider.setComicsRepository(new TestRepository(true, comicsList,comics));
        //Act
        presenter.loadComics();
        // Assert
        Mockito.verify(viewState).showLoading(Mockito.any());
        Mockito.verify(viewState).hideLoading();
        Mockito.verify(viewState).handleError(Mockito.any(Throwable.class));
    }
    @Test
    public void loadComicsSuccess() {
        // Arrange
        List<Comics> comicsList = new ArrayList<>();
        Comics comics = Mockito.mock(Comics.class);
        RepositoryProvider.setComicsRepository(new TestRepository(false, comicsList,comics));
        //Act
        presenter.loadComics();
        // Assert
        Mockito.verify(viewState).showLoading(Mockito.any());
        Mockito.verify(viewState).hideLoading();
        Mockito.verify(viewState).showItems(comicsList);
    }

    @Test
    public void loadNextElementsError() {
        // Arrange
        List<Comics> comicsList = new ArrayList<>();
        Comics comics = Mockito.mock(Comics.class);
        RepositoryProvider.setComicsRepository(new TestRepository(true, comicsList,comics));
        // Act
        presenter.loadNextElements(Mockito.anyInt());
        // Assert
        Mockito.verify(viewState).showLoading(Mockito.any());
        Mockito.verify(viewState).hideLoading();
        Mockito.verify(viewState).setNotLoading();
        Mockito.verify(viewState).handleError(Mockito.any(Throwable.class));
    }

    @Test
    public void loadNextElementsSuccess() {
        // Arrange
        List<Comics> comicsList = new ArrayList<>();
        Comics comics = Mockito.mock(Comics.class);
        RepositoryProvider.setComicsRepository(new TestRepository(false, comicsList,comics));
        // Act
        presenter.loadNextElements(Mockito.anyInt());
        // Assert
        Mockito.verify(viewState).showLoading(Mockito.any());
        Mockito.verify(viewState).hideLoading();
        Mockito.verify(viewState).setNotLoading();
        Mockito.verify(viewState).addMoreItems(comicsList);
    }

    @Test
    public void onItemClick() {
        // Arrange
        Comics comics = Mockito.mock(Comics.class);
        // Act
        presenter.onItemClick(comics);
        // Assert
        Mockito.verify(viewState).showDetails(comics);
    }

    @Test
    public void doActionInView() {
        Mockito.verifyNoMoreInteractions(viewState);
    }

    private class TestRepository implements ComicsRepository {

        private boolean error;
        private List<Comics> comicsList;
        private Comics comics;

        TestRepository(boolean error, List<Comics> comicsList, Comics comics) {
            this.error = error;
            this.comicsList = comicsList;
            this.comics = comics;
        }

        @NonNull
        @Override
        public Single<List<Comics>> comics(Long offset, Long limit, String sort) {
            if (this.error) {
                return Single.error(new Throwable());
            } else {
                return Single.just(this.comicsList);
            }
        }

        @Override
        public Single<Comics> comics(Long id) {
            if (this.error) {
                return Single.error(new Throwable());
            } else {
                return Single.just(this.comics);
            }
        }
    }
}
