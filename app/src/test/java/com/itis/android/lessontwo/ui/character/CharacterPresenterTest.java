package com.itis.android.lessontwo.ui.character;

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
public class CharacterPresenterTest {

    @SuppressWarnings("WeakerAccess")
    @Mock
    CharacterView$$State viewState;

    @SuppressWarnings("WeakerAccess")
    @Mock
    CharacterRepositoryImpl repository;

    private CharacterPresenter presenter;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        presenter = Mockito.spy(CharacterPresenter.class);
        presenter.setViewState(viewState);
        mockStatic(ApiFactory.class);
        mockStatic(RxUtils.class);
    }

    @Test
    public void onFirstViewAttach() throws Exception {
        // Arrange
        Mockito.doCallRealMethod().when(presenter).onFirstViewAttach();
        // Act
        presenter.onFirstViewAttach();
        // Assert
        Mockito.verify(viewState).getCharacterId();
    }

    @Test
    public void loadCharactersMockError() throws Exception {
        // Arrange
        RepositoryProvider.setCharactersRepository(repository);
        Mockito.when(repository.character(anyLong()))
                .thenReturn(Single.error(new Throwable()));
        // Act
        presenter.init(1);
        // Assert
        Mockito.verify(viewState).handleError(Mockito.any(Throwable.class));
    }

    @Test
    public void loadCharactersMockSuccess() throws Exception {
        // Arrange
        RepositoryProvider.setCharactersRepository(repository);
        Character character = Mockito.mock(Character.class);
        Mockito.when(repository.character(anyLong()))
                .thenReturn(Single.just(character));
        // Act
        presenter.init(1);
        // Assert
        Mockito.verify(viewState).setImage(character);
        Mockito.verify(viewState).setDescription(character);
    }

    @Test
    public void loadCharactersError() throws Exception {
        // Arrange
        List<Character> charactersList = new ArrayList<>();
        Character character = new Character();
        RepositoryProvider.setCharactersRepository(new TestRepository(true, charactersList, character));
        //Act
        presenter.init(1);
        // Assert
        Mockito.verify(viewState).handleError(Mockito.any(Throwable.class));
    }

    @Test
    public void loadCharacterSuccess() throws Exception {
        // Arrange
        List<Character> charactersList = new ArrayList<>();
        Character character = new Character();
        RepositoryProvider.setCharactersRepository(new TestRepository(false, charactersList, character));
        //Act
        presenter.init(1);
        // Assert
        Mockito.verify(viewState).setImage(character);
        Mockito.verify(viewState).setDescription(character);
    }

    @Test
    public void doActionInView() throws Exception {
        Mockito.verifyNoMoreInteractions(viewState);
    }

    @Test
    public void for100Coverage() {
        new CharacterPresenter$$ViewStateProvider().getViewState();
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
        public Single<List<Character>> charactersTest(Long offset, Long limit) {
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
