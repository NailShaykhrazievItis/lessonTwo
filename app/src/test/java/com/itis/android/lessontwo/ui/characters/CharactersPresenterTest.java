package com.itis.android.lessontwo.ui.characters;

import com.itis.android.lessontwo.api.ApiFactory;
import com.itis.android.lessontwo.model.entity.character.Character;
import com.itis.android.lessontwo.repository.CharacterRepositoryImpl;
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

import io.reactivex.Single;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;

/**
 * Created by Ruslan on 23.03.2018.
 */

@RunWith(PowerMockRunner.class)
@PrepareForTest({ApiFactory.class, RxUtils.class})
public class CharactersPresenterTest {

    @SuppressWarnings("WeakAccess")
    @Mock
    CharactersView$$State viewState;

    @SuppressWarnings("WeakAccess")
    @Mock
    CharacterRepositoryImpl repository;

    private CharactersPresenter presenter;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        presenter = Mockito.spy(CharactersPresenter.class);
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
        Mockito.verify(viewState).getCharacterId();
    }

    @Test
    public void loadError() throws Exception {
        // Arrange
        Mockito.when(repository.character(anyLong()))
                .thenReturn(Single.error(new Throwable()));
        RepositoryProvider.setCharacterRepository(repository);
        // Act
        presenter.load(anyLong());
        // Assert
        Mockito.verify(viewState).handleError(any(Throwable.class));
    }

    @Test
    public void loadSuccess() throws Exception {
        // Arrange
        Character character = Mockito.mock(Character.class);
        Mockito.when(repository.character(anyLong()))
                .thenReturn(Single.just(character));
        RepositoryProvider.setCharacterRepository(repository);
        // Act
        presenter.load(anyLong());
        // Assert
        Mockito.verify(viewState).setName(character);
        Mockito.verify(viewState).setImage(character);
        Mockito.verify(viewState).setDescription(character);
    }
}
