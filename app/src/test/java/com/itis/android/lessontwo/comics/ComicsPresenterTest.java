package com.itis.android.lessontwo.comics;

import com.itis.android.lessontwo.api.ApiFactory;
import com.itis.android.lessontwo.model.character.Character;
import com.itis.android.lessontwo.model.comics.Comics;
import com.itis.android.lessontwo.repository.RepositoryProvider;
import com.itis.android.lessontwo.repository.characters.CharactersRepositoryImpl;
import com.itis.android.lessontwo.repository.comics.ComicsRepositoryImpl;
import com.itis.android.lessontwo.ui.characters.CharacterPresenter;
import com.itis.android.lessontwo.ui.characters.CharacterView$$State;
import com.itis.android.lessontwo.ui.comics.ComicsPresenter;
import com.itis.android.lessontwo.ui.comics.ComicsView$$State;
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
public class ComicsPresenterTest {

    @SuppressWarnings("WeakAccess")
    @Mock
    ComicsView$$State viewState;

    @SuppressWarnings("WeakAccess")
    @Mock
    ComicsRepositoryImpl repository;

    private ComicsPresenter presenter;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        presenter = Mockito.spy(ComicsPresenter.class);
        presenter.setViewState(viewState);
        PowerMockito.mockStatic(ApiFactory.class);
        PowerMockito.mockStatic(RxUtils.class);
    }

    @Test
    public void onFirstViewAttach() throws Exception {
        Mockito.doNothing().when(presenter).init(anyLong());
        presenter.onFirstViewAttach();
        Mockito.verify(viewState).getComicsId();
    }

    @Test
    public void loadError() throws Exception {
        Mockito.when(repository.comics(anyLong()))
                .thenReturn(Single.error(new Throwable()));
        RepositoryProvider.setComicsRepository(repository);
        presenter.init(anyLong());
        Mockito.verify(viewState).handleError(any(Throwable.class));
    }

    @Test
    public void loadSuccess() throws Exception {
        Comics comics = Mockito.mock(Comics.class);
        Mockito.when(repository.comics(anyLong()))
                .thenReturn(Single.just(comics));
        RepositoryProvider.setComicsRepository(repository);
        presenter.init(anyLong());
        Mockito.verify(viewState).setImage(comics);
    }
}
