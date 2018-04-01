package com.itis.android.lessontwo.ui.comics;

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
import static org.powermock.api.mockito.PowerMockito.mockStatic;

/**
 * Created by a9 on 26.03.18.
 */

@RunWith(PowerMockRunner.class)
@PrepareForTest({RxUtils.class, ApiFactory.class})
public class ComicsPresenterTest {

    @SuppressWarnings("WeakerAccess")
    @Mock
    ComicsView$$State viewState;

    @SuppressWarnings("WeakerAccess")
    @Mock
    ComicsRepositoryImpl repository;

    private ComicsPresenter presenter;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        presenter = Mockito.spy(ComicsPresenter.class);
        presenter.setViewState(viewState);
        mockStatic(ApiFactory.class);
        mockStatic(RxUtils.class);
    }

    @Test
    public void onFirstViewAttach() throws Exception {
        // Arrange
        Mockito.doCallRealMethod().when(presenter).onFirstViewAttach();
        // Act
        presenter.onFirstViewAttach();
        // Assert
        Mockito.verify(viewState).getComicsId();
    }

    @Test
    public void loadComicsMockError() throws Exception {
        // Arrange
        RepositoryProvider.setComicsRepository(repository);
        Mockito.when(repository.comics(anyLong()))
                .thenReturn(Single.error(new Throwable()));
        // Act
        presenter.init(1);
        // Assert
        Mockito.verify(viewState).handleError(Mockito.any(Throwable.class));
    }

    @Test
    public void loadComicsMockSuccess() throws Exception {
        // Arrange
        RepositoryProvider.setComicsRepository(repository);
        Comics comics = Mockito.mock(Comics.class);
        Mockito.when(repository.comics(anyLong()))
                .thenReturn(Single.just(comics));
        // Act
        presenter.init(1);
        // Assert
        Mockito.verify(viewState).setImage(comics);
        Mockito.verify(viewState).setDescription(comics);
    }

    @Test
    public void loadComicsError() throws Exception {
        // Arrange
        List<Comics> comicsList = new ArrayList<>();
        Comics comics = new Comics();
        RepositoryProvider.setComicsRepository(new TestRepository(true, comicsList, comics));
        //Act
        presenter.init(1);
        // Assert
        Mockito.verify(viewState).handleError(Mockito.any(Throwable.class));
    }

    @Test
    public void loadComicsuccess() throws Exception {
        // Arrange
        List<Comics> comicsList = new ArrayList<>();
        Comics comics = new Comics();
        RepositoryProvider.setComicsRepository(new TestRepository(false, comicsList, comics));
        //Act
        presenter.init(1);
        // Assert
        Mockito.verify(viewState).setImage(comics);
        Mockito.verify(viewState).setDescription(comics);
    }

    @Test
    public void doActionInView() throws Exception {
        Mockito.verifyNoMoreInteractions(viewState);
    }

    @Test
    public void for100Coverage() {
        new ComicsPresenter$$ViewStateProvider().getViewState();
    }

    private class TestRepository implements ComicsRepository {

        private boolean error;
        private List<Comics> comicsList;
        private Comics comics;

        public TestRepository(boolean error, List<Comics> comicsList, Comics comics) {
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
