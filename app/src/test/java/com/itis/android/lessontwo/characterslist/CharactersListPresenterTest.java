package com.itis.android.lessontwo.characterslist;

import android.support.annotation.NonNull;

import com.itis.android.lessontwo.api.ApiFactory;
import com.itis.android.lessontwo.model.character.Character;
import com.itis.android.lessontwo.repository.RepositoryProvider;
import com.itis.android.lessontwo.repository.characters.CharactersRepository;
import com.itis.android.lessontwo.repository.characters.CharactersRepositoryImpl;
import com.itis.android.lessontwo.ui.characterslist.CharactersListPresenter;
import com.itis.android.lessontwo.ui.characterslist.CharactersListView$$State;
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

/**
 * Created by Aizat on 26.03.2018.
 */

@RunWith(PowerMockRunner.class)
@PrepareForTest({ApiFactory.class, RxUtils.class})
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
        Mockito.doNothing().when(presenter).loadCharacters();
        presenter.onFirstViewAttach();
        Mockito.verify(presenter).loadCharacters();
    }

    @Test
    public void loadCharactersMockError() throws Exception {
        Mockito.when(repository.characters(anyLong(), anyLong()))
                .thenReturn(Single.error(new Throwable()));
        RepositoryProvider.setCharacterRepository(repository);
        presenter.loadCharacters();
        Mockito.verify(viewState).showLoading(any());
        Mockito.verify(viewState).hideLoading();
        Mockito.verify(viewState).handleError(any(Throwable.class));
    }

    @Test
    public void loadCharactersMockSuccess() throws Exception {
        List<Character> characterList = new ArrayList<>();
        Mockito.when(repository.characters(anyLong(), anyLong()))
                .thenReturn(Single.just(characterList));
        RepositoryProvider.setCharacterRepository(repository);
        presenter.loadCharacters();
        Mockito.verify(viewState).showLoading(any());
        Mockito.verify(viewState).hideLoading();
        Mockito.verify(viewState).showItems(characterList);
    }

    @Test
    public void loadCharactersError() throws Exception {
        List<Character> characterList = new ArrayList<>();
        Character character = Mockito.mock(Character.class);
        RepositoryProvider.setCharacterRepository(new TestRepository(true, character, characterList));
        presenter.loadCharacters();
        Mockito.verify(viewState).showLoading(any());
        Mockito.verify(viewState).hideLoading();
        Mockito.verify(viewState).handleError(Mockito.any(Throwable.class));
    }

    @Test
    public void loadCharactersSuccess() throws Exception {
        List<Character> characterList = new ArrayList<>();
        Character character = Mockito.mock(Character.class);
        RepositoryProvider.setCharacterRepository(new TestRepository(false, character, characterList));
        presenter.loadCharacters();
        Mockito.verify(viewState).showLoading(any());
        Mockito.verify(viewState).hideLoading();
        Mockito.verify(viewState).showItems(characterList);
    }

    @Test
    public void loadNextElementsError() throws Exception {
        List<Character> characterList = new ArrayList<>();
        Character character = Mockito.mock(Character.class);
        RepositoryProvider.setCharacterRepository(new TestRepository(true, character, characterList));
        presenter.loadNextElements(anyInt());
        Mockito.verify(viewState).showLoading(any());
        Mockito.verify(viewState).hideLoading();
        Mockito.verify(viewState).setNotLoading();
        Mockito.verify(viewState).handleError(Mockito.any(Throwable.class));
    }

    @Test
    public void loadNextElementsSuccess() throws Exception {
        List<Character> characterList = new ArrayList<>();
        Character character = Mockito.mock(Character.class);
        RepositoryProvider.setCharacterRepository(new TestRepository(false, character, characterList));
        presenter.loadNextElements(anyInt());
        Mockito.verify(viewState).showLoading(any());
        Mockito.verify(viewState).hideLoading();
        Mockito.verify(viewState).setNotLoading();
        Mockito.verify(viewState).addMoreItems(characterList);
    }

    @Test
    public void onItemClick() throws Exception {
        Character character = Mockito.mock(Character.class);
        presenter.onItemClick(character);
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

       TestRepository(boolean error, Character character, List<Character> characterList) {
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
