package com.itis.android.lessontwo.ui.comics;

import com.itis.android.lessontwo.api.ApiFactory;
import com.itis.android.lessontwo.model.comics.Comics;
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

import io.reactivex.Single;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.powermock.api.mockito.PowerMockito.mockStatic;

/**
 * Created by User on 25.03.2018.
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
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        presenter = Mockito.spy(ComicsPresenter.class);
        presenter.setViewState(viewState);
        mockStatic(ApiFactory.class);
        mockStatic(RxUtils.class);
    }

    @Test
    public void onFirstViewAttach() throws Exception {
        // Arrange
        Mockito.doNothing().when(presenter).init(anyLong());
        // Act
        presenter.onFirstViewAttach();
        // Assert
        Mockito.verify(viewState).getComicsId();
    }

    @Test
    public void initMockError() throws Exception {
        // Arrange
        Mockito.when(repository.comics(anyLong()))
                .thenReturn(Single.error(new Throwable()));
        RepositoryProvider.setComicsRepository(repository);
        // Act
        presenter.init(anyLong());
        // Assert
        Mockito.verify(viewState).handleError(Mockito.any(Throwable.class));
    }

    @Test
    public void initMockSuccess() {
        // Arrange
        Comics comics = Mockito.mock(Comics.class);
        Mockito.when(repository.comics(anyLong()))
                .thenReturn(Single.just(comics));
        RepositoryProvider.setComicsRepository(repository);
        // Act
        presenter.init(anyLong());
        // Assert
        Mockito.verify(viewState).setImage(comics);
        Mockito.verify(viewState).setDescription(comics);
        Mockito.verify(viewState).setPrice(comics);
        Mockito.verify(viewState).setPageCount(comics);
    }
}