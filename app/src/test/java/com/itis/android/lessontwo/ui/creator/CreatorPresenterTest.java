package com.itis.android.lessontwo.ui.creator;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.powermock.api.mockito.PowerMockito.mockStatic;

import android.support.annotation.NonNull;
import com.itis.android.lessontwo.api.ApiFactory;
import com.itis.android.lessontwo.model.creator.Creator;
import com.itis.android.lessontwo.repository.CreatorRepository;
import com.itis.android.lessontwo.repository.RepositoryProvider;
import com.itis.android.lessontwo.ui.creators.CreatorPresenter;
import com.itis.android.lessontwo.ui.creators.CreatorView$$State;
import com.itis.android.lessontwo.utils.RxUtils;
import io.reactivex.Single;
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
@PrepareForTest({ApiFactory.class, RxUtils.class})
public class CreatorPresenterTest {

    @SuppressWarnings("WeakerAccess")
    @Mock
    CreatorView$$State viewState;

    @SuppressWarnings("WeakerAccess")
    @Mock
    CreatorRepository repository;

    private CreatorPresenter presenter;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        presenter = spy(CreatorPresenter.class);
        presenter.setViewState(viewState);
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
        verify(viewState).getCreatorId();
    }

    @Test
    public void initMockError() throws Exception {
        //Arrange
        when(repository.creator(anyLong()))
                .thenReturn(Single.error(new Throwable()));
        //Act
        presenter.init(anyLong());
        //Assert
        verify(viewState).handleError(any(Throwable.class));
    }

    @Test
    public void initMockSuccess() throws Exception {
        //Arrange
        when(repository.creator(anyLong()))
                .thenReturn(Single.just(new Creator()));
        //Act
        presenter.init(anyLong());
        //Assert
        verify(viewState).setImage(any());
        verify(viewState).setStories(any());
        verify(viewState).setName(any());
    }

    @Test
    public void initError() throws Exception {
        //Arrange
        Creator creator = mock(Creator.class);
        RepositoryProvider
                .setCreatorRepository(new TestRepo(creator, true));
        //Act
        presenter.init(anyLong());
        //Assert
        verify(viewState).handleError(any(Throwable.class));
    }

    @Test
    public void initSuccess() throws Exception {
        //Arrange
        Creator creator = mock(Creator.class);
        RepositoryProvider.setCreatorRepository(new TestRepo(creator, false));
        //Act
        presenter.init(anyLong());
        //Assert
        verify(viewState).setImage(any());
        verify(viewState).setStories(any());
        verify(viewState).setName(any());
    }

    @Test
    public void doActionInView() throws Exception {
        verifyNoMoreInteractions(viewState);
    }

    private class TestRepo implements CreatorRepository{

        private Creator creator;

        private boolean error;

        public TestRepo(Creator creator, boolean error) {
            this.creator = creator;
            this.error = error;
        }

        @NonNull
        @Override
        public Single<List<Creator>> creators(Long offset, Long limit) {
            return null;
        }

        @Override
        public Single<Creator> creator(Long id) {
            return error ? Single.error(new Throwable()) : Single.just(creator);
        }
    }
}
