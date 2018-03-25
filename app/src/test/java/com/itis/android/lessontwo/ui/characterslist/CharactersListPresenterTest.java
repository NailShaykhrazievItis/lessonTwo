package com.itis.android.lessontwo.ui.characterslist;

import android.support.annotation.NonNull;

import com.itis.android.lessontwo.api.ApiFactory;
import com.itis.android.lessontwo.model.character.Character;
import com.itis.android.lessontwo.repository.CharactersRepository;
import com.itis.android.lessontwo.repository.CharactersRepositoryImpl;
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

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;

/**
 * Created by User on 25.03.2018.
 */

@RunWith(PowerMockRunner.class)
@PrepareForTest({RxUtils.class, ApiFactory.class})
public class CharactersListPresenterTest {

    @SuppressWarnings("WeakAccess")
    @Mock
    CharactersListView$$State viewState;

    @SuppressWarnings("WeakAccess")
    @Mock
    CharactersRepositoryImpl repository;

    private CharactersListPresenter presenter;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        presenter = Mockito.spy(CharactersListPresenter.class);
        presenter.setViewState(viewState);
        PowerMockito.mockStatic(ApiFactory.class);
        PowerMockito.mockStatic(RxUtils.class);
    }

    @Test
    public void onFirstViewAttach() throws Exception {
        // Arrange
        Mockito.doNothing().when(presenter).loadCharacters();
        // Act
        presenter.onFirstViewAttach();
        // Assert
        Mockito.verify(presenter).loadCharacters();
    }

    @Test
    public void loadNextElementsError() throws Exception {
        // Arrange
        List<Character> charactersList = new ArrayList<>();
        Character character = Mockito.mock(Character.class);
        RepositoryProvider.setCharactersRepository(new TestRepository(true, character, charactersList));
        //Act
        presenter.loadCharacters();
        // Assert
        Mockito.verify(viewState).showLoading(Mockito.any());
        Mockito.verify(viewState).hideLoading();
        Mockito.verify(viewState).handleError(Mockito.any(Throwable.class));
    }

    @Test
    public void loadNextElementsSuccess() {
        // Arrange
        List<Character> charactersList = new ArrayList<>();
        Character character = Mockito.mock(Character.class);
        RepositoryProvider.setCharactersRepository(new TestRepository(false, character, charactersList));
        //Act
        presenter.loadCharacters();
        // Assert
        Mockito.verify(viewState).showLoading(Mockito.any());
        Mockito.verify(viewState).hideLoading();
        Mockito.verify(viewState).showItems(charactersList);
    }

    @Test
    public void loadCharactersMockError() throws Exception {
        // Arrange
        Mockito.when(repository.characters(anyLong(), anyLong()))
                .thenReturn(Single.error(new Throwable()));
        RepositoryProvider.setCharactersRepository(repository);
        // Act
        presenter.loadCharacters();
        // Assert
        Mockito.verify(viewState).showLoading(any());
        Mockito.verify(viewState).hideLoading();
        Mockito.verify(viewState).handleError(Mockito.any(Throwable.class));
    }

    @Test
    public void loadCharactersMockSuccess() {
        // Arrange
        List<Character> charactersList = new ArrayList<>();
        Mockito.when(repository.characters(anyLong(), anyLong()))
                .thenReturn(Single.just(charactersList));
        RepositoryProvider.setCharactersRepository(repository);
        // Act
        presenter.loadCharacters();
        // Assert
        Mockito.verify(viewState).showLoading(any());
        Mockito.verify(viewState).hideLoading();
        Mockito.verify(viewState).showItems(charactersList);
    }

    @Test
    public void onItemClick() throws Exception {
        // Arrange
        Character character = Mockito.mock(Character.class);
        // Act
        presenter.onItemClick(character);
        // Assert
        Mockito.verify(viewState).showDetails(character);
    }

    @Test
    public void doActionInView() throws Exception {
        Mockito.verifyNoMoreInteractions(viewState);
    }

    private class TestRepository implements CharactersRepository {

        private boolean error;
        private Character character;
        private List<Character> characterList;

        public TestRepository(boolean error, Character character, List<Character> characterList) {
            this.error = error;
            this.character = character;
            this.characterList = characterList;
        }

        @NonNull
        @Override
        public Single<List<Character>> characters(Long offset, Long limit) {
            if (this.error) {
                return Single.error(new Throwable());
            } else {
                return Single.just(this.characterList);
            }
        }

        @Override
        public Single<Character> characters(Long id) {
            if (this.error) {
                return Single.error(new Throwable());
            } else {
                return Single.just(this.character);
            }
        }
    }
}