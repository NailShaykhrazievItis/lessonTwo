package com.itis.android.lessontwo.ui.characterslist;

import android.support.annotation.NonNull;

import com.itis.android.lessontwo.api.ApiFactory;
import com.itis.android.lessontwo.model.entity.character.Character;
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
 * Created by Ruslan on 23.03.2018.
 */

@RunWith(PowerMockRunner.class)
@PrepareForTest({ApiFactory.class, RxUtils.class})
public class CharactersListPresenterTest {

    @SuppressWarnings("WeakAccess")
    @Mock
    CharactersListView$$State viewState;

    @SuppressWarnings("WeakAccess")
    @Mock
    CharacterRepositoryImpl repository;

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
        Mockito.doNothing().when(presenter).load();
        // Act
        presenter.onFirstViewAttach();
        // Assert
        Mockito.verify(presenter).load();
    }

    @Test
    public void loadCreatorsMockError() throws Exception {
        // Arrange
        Mockito.when(repository.characters(anyLong(), anyLong(), anyString()))
                .thenReturn(Single.error(new Throwable()));
        RepositoryProvider.setCharacterRepository(repository);
        // Act
        presenter.load();
        // Assert
        Mockito.verify(viewState).showLoading(any());
        Mockito.verify(viewState).hideLoading();
        Mockito.verify(viewState).handleError(any(Throwable.class));
    }

    @Test
    public void loadCreatorsMockSuccess() throws Exception {
        // Arrange
        List<Character> characterList = new ArrayList<>();
        Mockito.when(repository.characters(anyLong(), anyLong(), anyString()))
                .thenReturn(Single.just(characterList));
        RepositoryProvider.setCharacterRepository(repository);
        // Act
        presenter.load();
        // Assert
        Mockito.verify(viewState).showLoading(any());
        Mockito.verify(viewState).hideLoading();
        Mockito.verify(viewState).showItems(characterList);
    }

    @Test
    public void loadCreatorsError() throws Exception {
        // Arrange
        List<Character> characterList = new ArrayList<>();
        Character character = Mockito.mock(Character.class);
        RepositoryProvider.setCharacterRepository(new TestRepository(true, character, characterList));
        // Act
        presenter.load();
        // Assert
        Mockito.verify(viewState).showLoading(any());
        Mockito.verify(viewState).hideLoading();
        Mockito.verify(viewState).handleError(Mockito.any(Throwable.class));
    }

    @Test
    public void loadCreatorsSuccess() throws Exception {
        // Arrange
        List<Character> characterList = new ArrayList<>();
        Character character = Mockito.mock(Character.class);
        RepositoryProvider.setCharacterRepository(new TestRepository(false, character, characterList));
        // Act
        presenter.load();
        // Assert
        Mockito.verify(viewState).showLoading(any());
        Mockito.verify(viewState).hideLoading();
        Mockito.verify(viewState).showItems(characterList);
    }

    @Test
    public void loadNextElementsError() throws Exception {
        // Arrange
        List<Character> characterList = new ArrayList<>();
        Character character = Mockito.mock(Character.class);
        RepositoryProvider.setCharacterRepository(new TestRepository(true, character, characterList));
        // Act
        presenter.loadNextElements(anyInt());
        // Assert
        Mockito.verify(viewState).showLoading(any());
        Mockito.verify(viewState).hideLoading();
        Mockito.verify(viewState).setNotLoading();
        Mockito.verify(viewState).handleError(Mockito.any(Throwable.class));
    }

    @Test
    public void loadNextElementsSuccess() throws Exception {
        // Arrange
        List<Character> characterList = new ArrayList<>();
        Character character = Mockito.mock(Character.class);
        RepositoryProvider.setCharacterRepository(new TestRepository(false, character, characterList));
        // Act
        presenter.loadNextElements(anyInt());
        // Assert
        Mockito.verify(viewState).showLoading(any());
        Mockito.verify(viewState).hideLoading();
        Mockito.verify(viewState).setNotLoading();
        Mockito.verify(viewState).addMoreItems(characterList);
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

    private class TestRepository implements CharacterRepository {

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
        public Single<List<Character>> characters(Long offset, Long limit, String sort) {
            if (this.error) {
                return Single.error(new Throwable());
            } else {
                return Single.just(this.characterList);
            }
        }

        @Override
        public Single<List<Character>> charactersTest(Long offset, Long limit, String sort) {
            return null;
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
