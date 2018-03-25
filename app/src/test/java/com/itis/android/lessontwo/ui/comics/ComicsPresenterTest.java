package com.itis.android.lessontwo.ui.comics;

import static org.powermock.api.mockito.PowerMockito.mockStatic;

import android.support.annotation.NonNull;
import com.itis.android.lessontwo.api.ApiFactory;
import com.itis.android.lessontwo.model.comics.Comics;
import com.itis.android.lessontwo.repository.ComicsRepository;
import com.itis.android.lessontwo.repository.RepositoryProvider;
import com.itis.android.lessontwo.utils.RxUtils;
import io.reactivex.Single;
import java.util.List;
import org.junit.*;
import org.junit.runner.*;
import org.mockito.*;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import static org.mockito.Mockito.*;

/**
 * Created by Nail Shaykhraziev on 18.03.2018.
 */

@RunWith(PowerMockRunner.class)
@PrepareForTest({ApiFactory.class, RxUtils.class})
public class ComicsPresenterTest {

    @SuppressWarnings("WeakerAccess")
    @Mock
    ComicsView$$State viewState;

    @SuppressWarnings("WeakerAccess")
    @Mock
    ComicsRepository repository;

    private ComicsPresenter presenter;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        presenter = spy(ComicsPresenter.class);
        presenter.setViewState(viewState);
        repository = mock(ComicsRepository.class);
        mockStatic(ApiFactory.class);
        mockStatic(RxUtils.class);
    }
    @Test
    public void onFirstViewAttach() throws Exception {
        //Arrange
        doNothing().when(presenter).init(anyLong());
        //Act
        presenter.onFirstViewAttach();
        //Assert
        verify(viewState).getComicsId();
    }

    @Test
    public void initMockError() throws Exception {
        //Arrange
        when(repository.comics(anyLong()))
                .thenReturn(Single.error(new Throwable()));
        //Act
        presenter.init(anyLong());
        //Assert
        verify(viewState).handleError(any(Throwable.class));
    }

    @Test
    public void initMockSuccess() throws Exception {
        //Arrange
        when(repository.comics(anyLong()))
                .thenReturn(Single.just(new Comics()));
        //Act
        presenter.init(anyLong());
        //Assert
        verify(viewState).setImage(any());
        verify(viewState).setDescription(any());
        verify(viewState).setPrice(any());
        verify(viewState).setPageCount(any());
    }

    @Test
    public void initError() throws Exception {
        //Arrange
        Comics comics = mock(Comics.class);
        RepositoryProvider.setComicsRepository(new TestRepo(comics, true));
        //Act
        presenter.init(anyLong());
        //Assert
        verify(viewState).handleError(any(Throwable.class));
    }

    @Test
    public void initSuccess() throws Exception {
        //Arrange
        Comics comics = mock(Comics.class);
        RepositoryProvider.setComicsRepository(new TestRepo(comics, false));
        //Act
        presenter.init(anyLong());
        //Assert
        verify(viewState).setImage(any());
        verify(viewState).setDescription(any());
        verify(viewState).setPrice(any());
        verify(viewState).setPageCount(any());
    }

    @Test
    public void doActionInView() throws Exception {
        verifyNoMoreInteractions(viewState);
    }

    private class TestRepo implements ComicsRepository{

        private Comics comics;

        private boolean error;

        public TestRepo(Comics comics, boolean error) {
            this.comics = comics;
            this.error = error;
        }

        @NonNull
        @Override
        public Single<List<Comics>> comics(Long offset, Long limit, String sort) {
            return null;
        }

        @Override
        public Single<Comics> comics(Long id) {
            return error ? Single.error(new Throwable()) : Single.just(comics);
        }
    }
}
