package com.itis.android.lessontwo.ui.creatorlist;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.*;
import static org.powermock.api.mockito.PowerMockito.mockStatic;

import android.support.annotation.NonNull;
import com.itis.android.lessontwo.api.ApiFactory;
import com.itis.android.lessontwo.model.creator.Creator;
import com.itis.android.lessontwo.repository.CreatorRepository;
import com.itis.android.lessontwo.repository.RepositoryProvider;
import com.itis.android.lessontwo.ui.creatorslist.CreatorListPresenter;
import com.itis.android.lessontwo.ui.creatorslist.CreatorListView$$State;
import com.itis.android.lessontwo.utils.RxUtils;
import io.reactivex.Single;
import java.util.ArrayList;
import java.util.List;
import org.junit.*;
import org.junit.runner.*;
import org.mockito.*;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

/**
 * Created by Blaheart on 23.03.2018.
 */

@RunWith(PowerMockRunner.class)
@PrepareForTest({RxUtils.class, ApiFactory.class})
public class CreatorListPresenterTest {

    private class TestRepo implements CreatorRepository {

        private Creator creator;

        private List<Creator> creatorList;

        private boolean error;

        public TestRepo(boolean error, List<Creator> creatorList,
                Creator creator) {
            this.error = error;
            this.creatorList = creatorList;
            this.creator = creator;
        }

        @Override
        public Single<Creator> creator(Long id) {
            return error ? Single.error(new Throwable()) : Single.just(creator);
        }

        @NonNull
        @Override
        public Single<List<Creator>> creators(Long offset, Long limit) {
            return error ? Single.error(new Throwable()) : Single.just(creatorList);
        }
    }

    @SuppressWarnings("WeakerAccess")
    @Mock
    CreatorRepository repository;

    @SuppressWarnings("WeakerAccess")
    @Mock
    CreatorListView$$State viewState;

    private CreatorListPresenter presenter;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        presenter = spy(CreatorListPresenter.class);
        presenter.setViewState(viewState);
        mockStatic(RxUtils.class);
        mockStatic(ApiFactory.class);
    }

    @Test
    public void onFirstViewAttach() throws Exception {
        //Arrange
        doNothing().when(presenter).loadCreators();
        //Act
        presenter.onFirstViewAttach();
        //Assert
        verify(presenter).loadCreators();
    }

    @Test
    public void loadCreatorsError() throws Exception {
        //Arrange
        List<Creator> creators = new ArrayList<>();
        Creator creator = mock(Creator.class);
        RepositoryProvider.setCreatorRepository(new TestRepo(true, creators, creator));
        //Act
        presenter.loadCreators();
        //Assert
        verify(viewState).showLoading(any());
        verify(viewState).hideLoading();
        verify(viewState).handleError(any(Throwable.class));
    }

    @Test
    public void loadCreatorsMockError() throws Exception {
        //Arrange
        when(repository.creators(anyLong(), anyLong()))
                .thenReturn(Single.error(new Throwable()));
        //Act
        presenter.loadCreators();
        //Assert
        verify(viewState).showLoading(any());
        verify(viewState).hideLoading();
        verify(viewState).handleError(any(Throwable.class));
    }

    @Test
    public void loadCreatorsMockSuccess() throws Exception {
        //Arrange
        List<Creator> creators = new ArrayList<>();
        when(repository.creators(anyLong(), anyLong()))
                .thenReturn(Single.just(creators));
        //Act
        presenter.loadCreators();
        //Assert
        verify(viewState).showLoading(any());
        verify(viewState).hideLoading();
        verify(viewState).showItems(creators);
    }

    @Test
    public void loadCreatorsSuccess() throws Exception {
        //Arrange
        List<Creator> creators = new ArrayList<>();
        Creator creator = mock(Creator.class);
        RepositoryProvider.setCreatorRepository(new TestRepo(false, creators, creator));
        //Act
        presenter.loadCreators();
        //Assert
        verify(viewState).showLoading(any());
        verify(viewState).hideLoading();
        verify(viewState).showItems(creators);
    }

    @Test
    public void loadNextElementsError() throws Exception {
        //Arrange
        List<Creator> creators = new ArrayList<>();
        Creator creator = mock(Creator.class);
        RepositoryProvider.setCreatorRepository(new TestRepo(true, creators, creator));
        //Act
        presenter.loadNextElements(anyInt());
        //Assert
        verify(viewState).showLoading(any());
        verify(viewState).hideLoading();
        verify(viewState).setNotLoading();
        verify(viewState).handleError(any(Throwable.class));
    }

    @Test
    public void loadNextElementsSuccess() throws Exception {
        //Arrange
        List<Creator> creators = new ArrayList<>();
        Creator creator = mock(Creator.class);
        RepositoryProvider.setCreatorRepository(new TestRepo(false, creators, creator));
        //Act
        presenter.loadNextElements(anyInt());
        //Assert
        verify(viewState).showLoading(any());
        verify(viewState).hideLoading();
        verify(viewState).setNotLoading();
        verify(viewState).addMoreItems(creators);
    }

    @Test
    public void onItemClick() throws Exception {
        //Arrange
        Creator creator = mock(Creator.class);
        //Act
        presenter.onItemClick(creator);
        //Assert
        verify(viewState).showDetails(creator);
    }

    @Test
    public void doActionInView() throws Exception {
        verifyNoMoreInteractions(viewState);
    }
}
