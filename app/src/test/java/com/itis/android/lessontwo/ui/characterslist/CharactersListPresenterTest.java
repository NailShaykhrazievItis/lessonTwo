package com.itis.android.lessontwo.ui.characterslist;

import android.support.annotation.NonNull;

import com.itis.android.lessontwo.api.ApiFactory;
import com.itis.android.lessontwo.model.character.Character;
import com.itis.android.lessontwo.repository.CharacterRepository;
import com.itis.android.lessontwo.repository.CharacterRepositoryImpl;
import com.itis.android.lessontwo.repository.RepositoryProvider;
import com.itis.android.lessontwo.utils.RxUtils;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Single;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.powermock.api.mockito.PowerMockito.mockStatic;

/**
 * Created by a9 on 26.03.18.
 */

@RunWith(PowerMockRunner.class)
@PrepareForTest({RxUtils.class, ApiFactory.class})
public class CharactersListPresenterTest {

    @SuppressWarnings("WeakerAccess")
    @Mock
    CharactersListView$$State viewState;

    @SuppressWarnings("WeakerAccess")
    @Mock
    CharacterRepositoryImpl repository;

    private CharactersListPresenter presenter;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        presenter = Mockito.spy(CharactersListPresenter.class);
        presenter.setViewState(viewState);
        mockStatic(ApiFactory.class);
        mockStatic(RxUtils.class);
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
    public void loadCharactersMockError() throws Exception {
        // Arrange
        RepositoryProvider.setCharactersRepository(repository);
        Mockito.when(repository.characters(anyLong(), anyLong()))
                .thenReturn(Single.error(new Throwable()));
        // Act
        presenter.loadCharacters();
        // Assert
        Mockito.verify(viewState).showLoading(Mockito.any());
        Mockito.verify(viewState).hideLoading();
        Mockito.verify(viewState).handleError(Mockito.any(Throwable.class));
    }

    @Test
    public void loadCharactersMockSuccess() throws Exception {
        // Arrange
        RepositoryProvider.setCharactersRepository(repository);
        List<Character> charactersList = new ArrayList<>();
        Mockito.when(repository.characters(anyLong(), anyLong()))
                .thenReturn(Single.just(charactersList));
        // Act
        presenter.loadCharacters();
        // Assert
        Mockito.verify(viewState).showLoading(Mockito.any());
        Mockito.verify(viewState).hideLoading();
        Mockito.verify(viewState).showItems(charactersList);
    }

    @Test
    public void loadCharactersError() throws Exception {
        // Arrange
        List<Character> charactersList = new ArrayList<>();
        Character character = Mockito.mock(Character.class);
        RepositoryProvider.setCharactersRepository(new TestRepository(true, charactersList, character));
        //Act
        presenter.loadCharacters();
        // Assert
        Mockito.verify(viewState).showLoading(Mockito.any());
        Mockito.verify(viewState).hideLoading();
        Mockito.verify(viewState).handleError(Mockito.any(Throwable.class));
    }

    @Test
    public void loadCharacterSuccess() throws Exception {
        // Arrange
        List<Character> charactersList = new ArrayList<>();
        Character character = Mockito.mock(Character.class);
        RepositoryProvider.setCharactersRepository(new TestRepository(false, charactersList, character));
        //Act
        presenter.loadCharacters();
        // Assert
        Mockito.verify(viewState).showLoading(Mockito.any());
        Mockito.verify(viewState).hideLoading();
        Mockito.verify(viewState).showItems(charactersList);
    }

    @Test
    public void loadNextElementsError() throws Exception {
        // Arrange
        List<Character> characterList = new ArrayList<>();
        Character character = Mockito.mock(Character.class);
        RepositoryProvider.setCharactersRepository(new TestRepository(true, characterList, character));
        // Act
        presenter.loadNextElements(Mockito.anyInt());
        // Assert
        Mockito.verify(viewState).showLoading(Mockito.any());
        Mockito.verify(viewState).hideLoading();
        Mockito.verify(viewState).setNotLoading();
        Mockito.verify(viewState).handleError(Mockito.any(Throwable.class));
    }

    @Test
    public void loadNextElementsSuccess() throws Exception {
        // Arrange
        List<Character> charactersList = new ArrayList<>();
        Character character = Mockito.mock(Character.class);
        RepositoryProvider.setCharactersRepository(new TestRepository(false, charactersList, character));
        // Act
        presenter.loadNextElements(Mockito.anyInt());
        // Assert
        Mockito.verify(viewState).showLoading(Mockito.any());
        Mockito.verify(viewState).hideLoading();
        Mockito.verify(viewState).setNotLoading();
        Mockito.verify(viewState).addMoreItems(charactersList);
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

    @Test
    public void for100Coverage() {
        new CharactersListPresenter$$ViewStateProvider().getViewState();
    }

    private class TestRepository implements CharacterRepository {

        private boolean error;
        private List<Character> characterList;
        private Character character;

        public TestRepository(boolean error, List<Character> characterList, Character character) {
            this.error = error;
            this.characterList = characterList;
            this.character = character;
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
        public Single<Character> character(Long id) {
            if (this.error) {
                return Single.error(new Throwable());
            } else {
                return Single.just(this.character);
            }
        }
    }
}
