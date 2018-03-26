package com.itis.android.lessontwo.creatorslist;

import com.itis.android.lessontwo.api.ApiFactory;
import com.itis.android.lessontwo.model.creators.Creators;
import com.itis.android.lessontwo.repository.RepositoryProvider;
import com.itis.android.lessontwo.repository.creators.CreatorsRepository;
import com.itis.android.lessontwo.repository.creators.CreatorsRepositoryImpl;
import com.itis.android.lessontwo.ui.creatorslist.CreatorsListPresenter;
import com.itis.android.lessontwo.ui.creatorslist.CreatorsListView$$State;
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
public class CreatorsListPresenterTest {

    @SuppressWarnings("WeakAccess")
    @Mock
    CreatorsListView$$State viewState;

    @SuppressWarnings("WeakAccess")
    @Mock
    CreatorsRepositoryImpl repository;

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
        Mockito.doNothing().when(presenter).loadCreators();
        presenter.onFirstViewAttach();
        Mockito.verify(presenter).loadCreators();
    }

    @Test
    public void loadCreatorsMockError() throws Exception {
        Mockito.when(repository.creators(anyLong(), anyLong(), anyString()))
                .thenReturn(Single.error(new Throwable()));
        RepositoryProvider.setCreatorRepository(repository);
        presenter.loadCreators();
        Mockito.verify(viewState).showLoading(any());
        Mockito.verify(viewState).hideLoading();
        Mockito.verify(viewState).handleError(any(Throwable.class));
    }

    @Test
    public void loadCreatorsMockSuccess() throws Exception {
        List<Creators> creatorList = new ArrayList<>();
        Mockito.when(repository.creators(anyLong(), anyLong(), anyString()))
                .thenReturn(Single.just(creatorList));
        RepositoryProvider.setCreatorRepository(repository);
        presenter.loadCreators();
        Mockito.verify(viewState).showLoading(any());
        Mockito.verify(viewState).hideLoading();
        Mockito.verify(viewState).showItems(creatorList);
    }

    @Test
    public void loadCreatorsError() throws Exception {
        List<Creators> creatorList = new ArrayList<>();
        Creators creators = Mockito.mock(Creators.class);
        RepositoryProvider.setCreatorRepository(new TestRepository(true, creators, creatorList));
        presenter.loadCreators();
        Mockito.verify(viewState).showLoading(any());
        Mockito.verify(viewState).hideLoading();
        Mockito.verify(viewState).handleError(any(Throwable.class));
    }

    @Test
    public void loadCreatorsSuccess() throws Exception {
        List<Creators> creatorList = new ArrayList<>();
        Creators creator = Mockito.mock(Creators.class);
        RepositoryProvider.setCreatorRepository(new TestRepository(false, creator, creatorList));
        presenter.loadCreators();
        Mockito.verify(viewState).showLoading(any());
        Mockito.verify(viewState).hideLoading();
        Mockito.verify(viewState).showItems(creatorList);
    }

    @Test
    public void loadNextElementsError() throws Exception {
        List<Creators> creatorList = new ArrayList<>();
        Creators creator = Mockito.mock(Creators.class);
        RepositoryProvider.setCreatorRepository(new TestRepository(true, creator, creatorList));
        presenter.loadNextElements(anyInt());
        Mockito.verify(viewState).showLoading(any());
        Mockito.verify(viewState).hideLoading();
        Mockito.verify(viewState).setNotLoading();
        Mockito.verify(viewState).handleError(any(Throwable.class));
    }

    @Test
    public void loadNextElementsSuccess() throws Exception {
        List<Creators> creatorList = new ArrayList<>();
        Creators creator = Mockito.mock(Creators.class);
        RepositoryProvider.setCreatorRepository(new TestRepository(false, creator, creatorList));
        presenter.loadNextElements(anyInt());
        Mockito.verify(viewState).showLoading(any());
        Mockito.verify(viewState).hideLoading();
        Mockito.verify(viewState).setNotLoading();
        Mockito.verify(viewState).addMoreItems(creatorList);
    }

    @Test
    public void onItemClick() throws Exception {
        Creators creator = Mockito.mock(Creators.class);
        presenter.onItemClick(creator);
        Mockito.verify(viewState).showDetails(creator);
    }

    @Test
    public void doActionInView() throws Exception {
        Mockito.verifyNoMoreInteractions(viewState);
    }

    private class TestRepository implements CreatorsRepository {

        private boolean error;
        private Creators creator;
        private List<Creators> creatorList;

        public TestRepository(boolean error, Creators creator, List<Creators> creatorList) {
            this.error = error;
            this.creator = creator;
            this.creatorList = creatorList;
        }

        @Override
        public Single<List<Creators>> creators(Long offset, Long limit, String sort) {
            if (this.error) {
                return Single.error(new Throwable());
            } else {
                return Single.just(this.creatorList);
            }
        }

        @Override
        public Single<Creators> creators(Long id) {
            if (this.error) {
                return Single.error(new Throwable());
            } else {
                return Single.just(this.creator);
            }
        }
    }
}