package com.itis.android.lessontwo.ui.characterlist;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.powermock.api.mockito.PowerMockito.mockStatic;
import static org.mockito.Mockito.*;
import static org.powermock.api.support.membermodification.MemberMatcher.everythingDeclaredIn;

import android.support.annotation.NonNull;
import com.itis.android.lessontwo.api.ApiFactory;
import com.itis.android.lessontwo.model.character.Character;
import com.itis.android.lessontwo.repository.CharacterRepository;
import com.itis.android.lessontwo.repository.RepositoryProvider;
import com.itis.android.lessontwo.utils.RxUtils;
import io.reactivex.Single;
import java.util.ArrayList;
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
@PrepareForTest({RxUtils.class, ApiFactory.class})
public class CharacterListPresenterTest {

    @SuppressWarnings("WeakerAccess")
    @Mock
    CharacterListView$$State viewState;

    @SuppressWarnings("WeakerAccess")
    @Mock
    CharacterRepository repository;

    private CharacterListPresenter presenter;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        presenter = Mockito.spy(CharacterListPresenter.class);
        presenter.setViewState(viewState);
        mockStatic(RxUtils.class);
        mockStatic(ApiFactory.class);
    }

    @Test
    public void onFirstViewAttach() throws Exception {
        //Arrange
        doNothing().when(presenter).loadCharacters();
        //Act
        presenter.onFirstViewAttach();
        //Assert
        verify(presenter).loadCharacters();
    }

    @Test
    public void loadCharactersMockError() throws Exception {
        //Arrange
        when(repository.characters(anyLong(), anyLong()))
                .thenReturn(Single.error(new Throwable()));
        //Act
        presenter.loadCharacters();
        //Assert
        verify(viewState).showLoading(any());
        verify(viewState).hideLoading();
        verify(viewState).handleError(any(Throwable.class));
    }

    @Test
    public void loadCharactersMockSuccess() throws Exception {
        //Arrange
        List<Character> characters = new ArrayList<>();
        when(repository.characters(anyLong(), anyLong()))
                .thenReturn(Single.just(characters));
        //Act
        presenter.loadCharacters();
        //Assert
        verify(viewState).showLoading(any());
        verify(viewState).hideLoading();
        verify(viewState).showItems(characters);
    }

    @Test
    public void loadCharactersError() throws Exception {
        //Arrange
        List<Character> characters = new ArrayList<>();
        Character character = mock(Character.class);
        RepositoryProvider.setCharacterRepository(new TestRepo(true, characters, character));
        //Act
        presenter.loadCharacters();
        //Assert
        verify(viewState).showLoading(any());
        verify(viewState).hideLoading();
        verify(viewState).handleError(any(Throwable.class));
    }

    @Test
    public void loadCharactersSuccess() throws Exception {
        //Arrange
        List<Character> characters = new ArrayList<>();
        Character character = mock(Character.class);
        RepositoryProvider.setCharacterRepository(new TestRepo(false, characters, character));
        //Act
        presenter.loadCharacters();
        //Assert
        verify(viewState).showLoading(any());
        verify(viewState).hideLoading();
        verify(viewState).showItems(characters);
    }

    @Test
    public void loadNextElementsError() throws Exception {
        //Arrange
        List<Character> characters = new ArrayList<>();
        Character character = mock(Character.class);
        RepositoryProvider.setCharacterRepository(new TestRepo(true, characters, character));
        //Act
        presenter.loadNextElements(anyInt());
        //Assert
        verify(viewState).showLoading(any());
        verify(viewState).hideLoading();
        verify(viewState).setNotLoading();
        verify(viewState).handleError(any(Throwable.class));
    }

    @Test
    public void loadNextElementsSuccess() throws Exception {
        //Arrange
        List<Character> characters = new ArrayList<>();
        Character character = mock(Character.class);
        RepositoryProvider.setCharacterRepository(new TestRepo(false, characters, character));
        //Act
        presenter.loadNextElements(anyInt());
        //Assert
        verify(viewState).showLoading(any());
        verify(viewState).hideLoading();
        verify(viewState).setNotLoading();
        verify(viewState).addMoreItems(characters);
    }

    @Test
    public void onItemClick() throws Exception {
        //Arrange
        Character character = mock(Character.class);
        //Act
        presenter.onItemClick(character);
        //Assert
        verify(viewState).showDetails(character);
    }

    @Test
    public void doActionInView() throws Exception {
        verifyNoMoreInteractions(viewState);
    }

    private class TestRepo implements CharacterRepository {

        private boolean error;
        private List<Character> characterList;
        private Character character;

        public TestRepo(boolean error, List<Character> characterList,
                Character character) {
            this.error = error;
            this.characterList = characterList;
            this.character = character;
        }

        @NonNull
        @Override
        public Single<List<Character>> characters(Long offset, Long limit) {
            return error ? Single.error(new Throwable()) : Single.just(characterList);
        }

        @Override
        public Single<Character> character(Long id) {
            return error ? Single.error(new Throwable()) : Single.just(character);
        }
    }
}
