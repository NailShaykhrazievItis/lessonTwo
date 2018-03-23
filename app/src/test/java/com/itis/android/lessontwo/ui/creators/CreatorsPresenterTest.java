package com.itis.android.lessontwo.ui.creators;

import com.itis.android.lessontwo.api.ApiFactory;
import com.itis.android.lessontwo.model.entity.creators.Creator;
import com.itis.android.lessontwo.repository.CreatorRepositoryImpl;
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

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.anyLong;

/**
 * Created by valera071998@gmail.com on 23.03.2018.
 */

@RunWith(PowerMockRunner.class)
@PrepareForTest({ApiFactory.class, RxUtils.class})
public class CreatorsPresenterTest {

    @SuppressWarnings("WeakAccess")
    @Mock
    CreatorsView$$State viewState;

    @SuppressWarnings("WeakAccess")
    @Mock
    CreatorRepositoryImpl repository;

    private CreatorsPresenter presenter;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        presenter = Mockito.spy(CreatorsPresenter.class);
        presenter.setViewState(viewState);
        PowerMockito.mockStatic(ApiFactory.class);
        PowerMockito.mockStatic(RxUtils.class);
    }

    @Test
    public void onFirstViewAttach() throws Exception {
        // Arrange
        Mockito.doNothing().when(presenter).load(anyLong());
        // Act
        presenter.onFirstViewAttach();
        // Assert
        Mockito.verify(viewState).getCreatorById();
    }

    @Test
    public void loadError() throws Exception {
        // Arrange
        Mockito.when(repository.creator(anyLong()))
                .thenReturn(Single.error(new Throwable()));
        // Act
        presenter.load(anyLong());
        // Assert
        Mockito.verify(viewState).handleError(any(Throwable.class));
    }

    @Test
    public void loadSuccess() throws Exception {
        // Arrange
        Creator creator = Mockito.mock(Creator.class);
        Mockito.when(repository.creator(anyLong()))
                .thenReturn(Single.just(creator));
        // Act
        presenter.load(anyLong());
        // Assert
        Mockito.verify(viewState).setName(creator);
        Mockito.verify(viewState).setImage(creator);
        Mockito.verify(viewState).setDescription(creator);
    }
}