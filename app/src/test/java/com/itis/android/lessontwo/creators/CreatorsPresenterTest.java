package com.itis.android.lessontwo.creators;

import com.itis.android.lessontwo.api.ApiFactory;
import com.itis.android.lessontwo.model.creators.Creators;
import com.itis.android.lessontwo.repository.RepositoryProvider;
import com.itis.android.lessontwo.repository.creators.CreatorsRepositoryImpl;
import com.itis.android.lessontwo.ui.creators.CreatorPresenter;
import com.itis.android.lessontwo.ui.creators.CreatorsView$$State;
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

import io.reactivex.Single;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;

/**
 * Created by Aizat on 26.03.2018.
 */

@RunWith(PowerMockRunner.class)
@PrepareForTest({ApiFactory.class, RxUtils.class})
public class CreatorsPresenterTest {

    @SuppressWarnings("WeakAccess")
    @Mock
    CreatorsView$$State viewState;

    @SuppressWarnings("WeakAccess")
    @Mock
    CreatorsRepositoryImpl repository;

    private CreatorPresenter presenter;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        presenter = Mockito.spy(CreatorPresenter.class);
        presenter.setViewState(viewState);
        PowerMockito.mockStatic(ApiFactory.class);
        PowerMockito.mockStatic(RxUtils.class);
    }

    @Test
    public void onFirstViewAttach() throws Exception {
        Mockito.doNothing().when(presenter).init(anyLong());
        presenter.onFirstViewAttach();
        Mockito.verify(viewState).getCreatorId();
    }

    @Test
    public void loadError() throws Exception {
        Mockito.when(repository.creators(anyLong()))
                .thenReturn(Single.error(new Throwable()));
        RepositoryProvider.setCreatorRepository(repository);
        presenter.init(anyLong());
        Mockito.verify(viewState).handleError(any(Throwable.class));
    }

    @Test
    public void loadSuccess() throws Exception {
        Creators creator = Mockito.mock(Creators.class);
        Mockito.when(repository.creators(anyLong()))
                .thenReturn(Single.just(creator));
        RepositoryProvider.setCreatorRepository(repository);
        presenter.init(anyLong());
        Mockito.verify(viewState).setName(creator);
        Mockito.verify(viewState).setImage(creator);
    }
}