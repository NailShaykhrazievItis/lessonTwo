package com.itis.android.lessontwo.ui.creatorslist;

import com.itis.android.lessontwo.api.ApiFactory;
import com.itis.android.lessontwo.model.entity.creators.Creator;
import com.itis.android.lessontwo.repository.CreatorRepository;
import com.itis.android.lessontwo.repository.CreatorRepositoryImpl;
import com.itis.android.lessontwo.repository.RepositoryProvider;
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

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.anyLong;
import static org.mockito.Mockito.anyString;

/**
 * Created by valera071998@gmail.com on 23.03.2018.
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest({ApiFactory.class, RxUtils.class})
public class CreatorsListPresenterTest {

    @SuppressWarnings("WeakAccess")
    @Mock
    CreatorsListView$$State viewState;

    @SuppressWarnings("WeakAccess")
    @Mock
    CreatorRepositoryImpl repository;

    private CreatorsListPresenter presenter;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        presenter = Mockito.spy(CreatorsListPresenter.class);
        presenter.setViewState(viewState);
        PowerMockito.mockStatic(ApiFactory.class);
        PowerMockito.mockStatic(RxUtils.class);
    }

    @Test
    public void onFirstViewAttach() throws Exception {
        // Arrange
        Mockito.doNothing().when(presenter).load();
        // Act
        presenter.onFirstViewAttach();
        // Assert
        Mockito.verify(presenter).load();
    }

    @Test
    public void loadCreatorsMockError() throws Exception {
        // Arrange
        Mockito.when(repository.creators(anyLong(), anyLong(), anyString()))
                .thenReturn(Single.error(new Throwable()));
        RepositoryProvider.setCreatorRepository(repository);
        // Act
        presenter.load();
        // Assert
        Mockito.verify(viewState).showLoading(any());
        Mockito.verify(viewState).hideLoading();
        Mockito.verify(viewState).handleError(any(Throwable.class));
    }

    @Test
    public void loadCreatorsMockSuccess() throws Exception {
        // Arrange
        List<Creator> creatorList = new ArrayList<>();
        Mockito.when(repository.creators(anyLong(), anyLong(), anyString()))
                .thenReturn(Single.just(creatorList));
        RepositoryProvider.setCreatorRepository(repository);
        // Act
        presenter.load();
        // Assert
        Mockito.verify(viewState).showLoading(any());
        Mockito.verify(viewState).hideLoading();
        Mockito.verify(viewState).showItems(creatorList);
    }

    @Test
    public void loadCreatorsError() throws Exception {
        // Arrange
        List<Creator> creatorList = new ArrayList<>();
        Creator creator = Mockito.mock(Creator.class);
        RepositoryProvider.setCreatorRepository(new TestRepository(true, creator, creatorList));
        // Act
        presenter.load();
        // Assert
        Mockito.verify(viewState).showLoading(any());
        Mockito.verify(viewState).hideLoading();
        Mockito.verify(viewState).handleError(any(Throwable.class));
    }

    @Test
    public void loadCreatorsSuccess() throws Exception {
        // Arrange
        List<Creator> creatorList = new ArrayList<>();
        Creator creator = Mockito.mock(Creator.class);
        RepositoryProvider.setCreatorRepository(new TestRepository(false, creator, creatorList));
        // Act
        presenter.load();
        // Assert
        Mockito.verify(viewState).showLoading(any());
        Mockito.verify(viewState).hideLoading();
        Mockito.verify(viewState).showItems(creatorList);
    }

    @Test
    public void loadNextElementsError() throws Exception {
        // Arrange
        List<Creator> creatorList = new ArrayList<>();
        Creator creator = Mockito.mock(Creator.class);
        RepositoryProvider.setCreatorRepository(new TestRepository(true, creator, creatorList));
        // Act
        presenter.loadNextElements(anyInt());
        // Assert
        Mockito.verify(viewState).showLoading(any());
        Mockito.verify(viewState).hideLoading();
        Mockito.verify(viewState).setNotLoading();
        Mockito.verify(viewState).handleError(any(Throwable.class));
    }

    @Test
    public void loadNextElementsSuccess() throws Exception {
        // Arrange
        List<Creator> creatorList = new ArrayList<>();
        Creator creator = Mockito.mock(Creator.class);
        RepositoryProvider.setCreatorRepository(new TestRepository(false, creator, creatorList));
        // Act
        presenter.loadNextElements(anyInt());
        // Assert
        Mockito.verify(viewState).showLoading(any());
        Mockito.verify(viewState).hideLoading();
        Mockito.verify(viewState).setNotLoading();
        Mockito.verify(viewState).addMoreItems(creatorList);
    }

    @Test
    public void onItemClick() throws Exception {
        // Arrange
        Creator creator = Mockito.mock(Creator.class);
        // Act
        presenter.onItemClick(creator);
        // Assert
        Mockito.verify(viewState).showDetails(creator);
    }

    @Test
    public void doActionInView() throws Exception {
        Mockito.verifyNoMoreInteractions(viewState);
    }

    private class TestRepository implements CreatorRepository {

        private boolean error;
        private Creator creator;
        private List<Creator> creatorList;

        public TestRepository(boolean error, Creator creator, List<Creator> creatorList) {
            this.error = error;
            this.creator = creator;
            this.creatorList = creatorList;
        }

        @Override
        public Single<List<Creator>> creators(Long offset, Long limit, String sort) {
            if (this.error) {
                return Single.error(new Throwable());
            } else {
                return Single.just(this.creatorList);
            }
        }

        @Override
        public Single<Creator> creator(Long id) {
            if (this.error) {
                return Single.error(new Throwable());
            } else {
                return Single.just(this.creator);
            }
        }
    }
}