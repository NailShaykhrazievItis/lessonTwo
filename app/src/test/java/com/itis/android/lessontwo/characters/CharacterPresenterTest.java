package com.itis.android.lessontwo.characters;

import com.itis.android.lessontwo.api.ApiFactory;
import com.itis.android.lessontwo.model.character.Character;
import com.itis.android.lessontwo.repository.RepositoryProvider;
import com.itis.android.lessontwo.repository.characters.CharactersRepositoryImpl;
import com.itis.android.lessontwo.ui.characters.CharacterPresenter;
import com.itis.android.lessontwo.ui.characters.CharacterView$$State;
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
public class CharacterPresenterTest {

    @SuppressWarnings("WeakAccess")
    @Mock
    CharacterView$$State viewState;

    @SuppressWarnings("WeakAccess")
    @Mock
    CharactersRepositoryImpl repository;

    private CharacterPresenter presenter;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        presenter = Mockito.spy(CharacterPresenter.class);
        presenter.setViewState(viewState);
        PowerMockito.mockStatic(ApiFactory.class);
        PowerMockito.mockStatic(RxUtils.class);
    }

    @Test
    public void onFirstViewAttach() throws Exception {
        Mockito.doNothing().when(presenter).init(anyLong());
        presenter.onFirstViewAttach();
        Mockito.verify(viewState).getCharacterId();
    }

    @Test
    public void loadError() throws Exception {
        Mockito.when(repository.characters(anyLong()))
                .thenReturn(Single.error(new Throwable()));
        RepositoryProvider.setCharacterRepository(repository);
        presenter.init(anyLong());
        Mockito.verify(viewState).handleError(any(Throwable.class));
    }

    @Test
    public void loadSuccess() throws Exception {
        Character character = Mockito.mock(Character.class);
        Mockito.when(repository.characters(anyLong()))
                .thenReturn(Single.just(character));
        RepositoryProvider.setCharacterRepository(repository);
        presenter.init(anyLong());
        Mockito.verify(viewState).setImage(character);
    }
}
