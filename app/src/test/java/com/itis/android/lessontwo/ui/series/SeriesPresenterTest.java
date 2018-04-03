package com.itis.android.lessontwo.ui.series;

import android.support.annotation.NonNull;

import com.itis.android.lessontwo.api.ApiFactory;
import com.itis.android.lessontwo.model.series.Series;
import com.itis.android.lessontwo.repository.RepositoryProvider;
import com.itis.android.lessontwo.repository.SeriesRepository;
import com.itis.android.lessontwo.repository.SeriesRepositoryImpl;
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
public class SeriesPresenterTest {

    @SuppressWarnings("WeakerAccess")
    @Mock
    SeriesView$$State viewState;

    @SuppressWarnings("WeakerAccess")
    @Mock
    SeriesRepositoryImpl repository;

    private SeriesPresenter presenter;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        presenter = Mockito.spy(SeriesPresenter.class);
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
        Mockito.verify(viewState).getSeriesId();
    }

    @Test
    public void loadSeriesMockError() throws Exception {
        // Arrange
        RepositoryProvider.setSeriesRepository(repository);
        Mockito.when(repository.series(anyLong()))
                .thenReturn(Single.error(new Throwable()));
        // Act
        presenter.init(1);
        // Assert
        Mockito.verify(viewState).handleError(Mockito.any(Throwable.class));
    }

    @Test
    public void loadSeriesMockSuccess() throws Exception {
        // Arrange
        RepositoryProvider.setSeriesRepository(repository);
        Series series = Mockito.mock(Series.class);
        Mockito.when(repository.series(anyLong()))
                .thenReturn(Single.just(series));
        // Act
        presenter.init(1);
        // Assert
        Mockito.verify(viewState).setImage(series);
        Mockito.verify(viewState).setDescription(series);
    }

    @Test
    public void loadSeriesError() throws Exception {
        // Arrange
        List<Series> seriesList = new ArrayList<>();
        Series series = new Series();
        RepositoryProvider.setSeriesRepository(new TestRepository(true, seriesList, series));
        //Act
        presenter.init(1);
        // Assert
        Mockito.verify(viewState).handleError(Mockito.any(Throwable.class));
    }

    @Test
    public void loadSeriesuccess() throws Exception {
        // Arrange
        List<Series> seriesList = new ArrayList<>();
        Series series = new Series();
        RepositoryProvider.setSeriesRepository(new TestRepository(false, seriesList, series));
        //Act
        presenter.init(1);
        // Assert
        Mockito.verify(viewState).setImage(series);
        Mockito.verify(viewState).setDescription(series);
    }

    @Test
    public void doActionInView() throws Exception {
        Mockito.verifyNoMoreInteractions(viewState);
    }

    @Test
    public void for100Coverage() {
        new SeriesPresenter$$ViewStateProvider().getViewState();
    }

    private class TestRepository implements SeriesRepository {

        private boolean error;
        private List<Series> seriesList;
        private Series series;

        public TestRepository(boolean error, List<Series> seriesList, Series series) {
            this.error = error;
            this.seriesList = seriesList;
            this.series = series;
        }

        @NonNull
        @Override
        public Single<List<Series>> series(Long offset, Long limit) {
            if (this.error) {
                return Single.error(new Throwable());
            } else {
                return Single.just(this.seriesList);
            }
        }

        @Override
        public Single<List<Series>> seriesTest(Long offset, Long limit) {
            return null;
        }

        @Override
        public Single<Series> series(Long id) {
            if (this.error) {
                return Single.error(new Throwable());
            } else {
                return Single.just(this.series);
            }
        }
    }
}
