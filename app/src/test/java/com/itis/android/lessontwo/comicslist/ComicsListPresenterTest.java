package com.itis.android.lessontwo.comicslist;

import android.support.annotation.NonNull;

import com.itis.android.lessontwo.api.ApiFactory;
import com.itis.android.lessontwo.model.comics.Comics;
import com.itis.android.lessontwo.repository.RepositoryProvider;
import com.itis.android.lessontwo.repository.comics.ComicsRepository;
import com.itis.android.lessontwo.repository.comics.ComicsRepositoryImpl;
import com.itis.android.lessontwo.ui.comicslist.ComicsListPresenter;
import com.itis.android.lessontwo.ui.comicslist.ComicsListView$$State;
import com.itis.android.lessontwo.utils.RxUtils;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Single;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;

/**
 * Created by Aizat on 26.03.2018.
 */

@RunWith(PowerMockRunner.class)
@PrepareForTest({ApiFactory.class, RxUtils.class})
public class ComicsListPresenterTest {

    @SuppressWarnings("WeakAccess")
    @Mock
    ComicsListView$$State viewState;

    @SuppressWarnings("WeakAccess")
    @Mock
    ComicsRepositoryImpl repository;

    private ComicsListPresenter presenter;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        presenter = Mockito.spy(ComicsListPresenter.class);
        presenter.setViewState(viewState);
        PowerMockito.mockStatic(ApiFactory.class);
        PowerMockito.mockStatic(RxUtils.class);
    }

    @Test
    public void onFirstViewAttach() throws Exception {
        Mockito.doNothing().when(presenter).loadComics();
        presenter.onFirstViewAttach();
        Mockito.verify(presenter).loadComics();
    }

    @Test
    public void loadComicsMockError() throws Exception {
        List<Comics> comicsList = new ArrayList<>();
        Comics comics = Mockito.mock(Comics.class);
        RepositoryProvider.setComicsRepository(new TestRepository(true, comics, comicsList));
        presenter.loadComics();
        Mockito.verify(viewState).showLoading(Mockito.any());
        Mockito.verify(viewState).hideLoading();
        Mockito.verify(viewState).handleError(Mockito.any(Throwable.class));
    }

    @Test
    public void loadComicsMockSuccess() throws Exception {
        List<Comics> comicsList = new ArrayList<>();
        Mockito.when(RepositoryProvider.provideComicsRepository()
                .comics(anyLong(), anyLong(), anyString()))
                .thenReturn(Single.just(comicsList));
        RepositoryProvider.setComicsRepository(repository);
        presenter.loadComics();
        Mockito.verify(viewState).showLoading(any());
        Mockito.verify(viewState).hideLoading();
        Mockito.verify(viewState).showItems(comicsList);
    }

    @Test
    public void loadComicsError() throws Exception {
        List<Comics> comicsList = new ArrayList<>();
        Comics comics = Mockito.mock(Comics.class);
        RepositoryProvider.setComicsRepository(new TestRepository(true, comics, comicsList));
        presenter.loadComics();
        Mockito.verify(viewState).showLoading(any());
        Mockito.verify(viewState).hideLoading();
        Mockito.verify(viewState).handleError(any(Throwable.class));
    }

    @Test
    public void loadCharactersSuccess() throws Exception {
        List<Comics> comicsList = new ArrayList<>();
        Comics comics = Mockito.mock(Comics.class);
        RepositoryProvider.setComicsRepository(new TestRepository(false, comics, comicsList));
        presenter.loadComics();
        Mockito.verify(viewState).showLoading(any());
        Mockito.verify(viewState).hideLoading();
        Mockito.verify(viewState).showItems(comicsList);
    }

    @Test
    public void loadNextElementsError() throws Exception {
        List<Comics> comicsList = new ArrayList<>();
        Comics comics = Mockito.mock(Comics.class);
        RepositoryProvider.setComicsRepository(new TestRepository(true, comics, comicsList));
        presenter.loadNextElements(anyInt());
        Mockito.verify(viewState).showLoading(any());
        Mockito.verify(viewState).hideLoading();
        Mockito.verify(viewState).setNotLoading();
        Mockito.verify(viewState).handleError(any(Throwable.class));
    }

    @Test
    public void loadNextElementsSuccess() throws Exception {
        List<Comics> comicsList = new ArrayList<>();
        Comics comics = Mockito.mock(Comics.class);
        RepositoryProvider.setComicsRepository(new TestRepository(false, comics, comicsList));
        presenter.loadNextElements(anyInt());
        Mockito.verify(viewState).showLoading(any());
        Mockito.verify(viewState).hideLoading();
        Mockito.verify(viewState).setNotLoading();
        Mockito.verify(viewState).addMoreItems(comicsList);
    }

    @Test
    public void onItemClick() throws Exception {
        Comics comics = Mockito.mock(Comics.class);
        presenter.onItemClick(comics);
        Mockito.verify(viewState).showDetails(comics);
    }

    @Test
    public void doActionInView() throws Exception {
        Mockito.verifyNoMoreInteractions(viewState);
    }

    private class TestRepository implements ComicsRepository {

        private boolean error;
        private List<Comics> comicsList;
        private Comics comics;

        public TestRepository(boolean error, Comics comics, List<Comics> comicsList) {
            this.error = error;
            this.comics = comics;
            this.comicsList = comicsList;
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

        @Override
        public Single<List<Comics>> comicsTest(Long offset, Long limit, String sort) {
            return null;
        }
    }
}
