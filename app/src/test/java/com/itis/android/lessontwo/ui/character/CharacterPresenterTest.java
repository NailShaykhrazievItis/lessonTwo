package com.itis.android.lessontwo.ui.character;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.powermock.api.mockito.PowerMockito.mockStatic;

import android.support.annotation.NonNull;
import com.itis.android.lessontwo.api.ApiFactory;
import com.itis.android.lessontwo.model.character.Character;
import com.itis.android.lessontwo.repository.CharacterRepository;
import com.itis.android.lessontwo.repository.RepositoryProvider;
import com.itis.android.lessontwo.utils.RxUtils;
import io.reactivex.Single;
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
@PrepareForTest({ApiFactory.class, RxUtils.class})
public class CharacterPresenterTest {

    @SuppressWarnings("WeakerAccess")
    @Mock
    CharacterView$$State viewState;

    @SuppressWarnings("WeakerAccess")
    @Mock
    CharacterRepository repository;

    private CharacterPresenter presenter;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        presenter = spy(CharacterPresenter.class);
        presenter.setViewState(viewState);
        mockStatic(ApiFactory.class);
        mockStatic(RxUtils.class);
    }

    @Test
    public void onFirstViewAttach() throws Exception {
        //Arrange
        doNothing().when(presenter).init(anyLong());
        //Act
        presenter.onFirstViewAttach();
        //Assert
        verify(viewState).getCharacterId();
    }

    @Test
    public void initMockError() throws Exception {
        //Arrange
        when(repository.character(anyLong()))
                .thenReturn(Single.error(new Throwable()));
        //Act
        presenter.init(anyLong());
        //Assert
        verify(viewState).handleError(any(Throwable.class));
    }

    @Test
    public void initMockSuccess() throws Exception {
        //Arrange
        when(repository.character(anyLong()))
                .thenReturn(Single.just(new Character()));
        //Act
        presenter.init(anyLong());
        //Assert
        verify(viewState).setCharacterImage(any());
        verify(viewState).setCharacterDescription(any());
        verify(viewState).setCharacterName(any());
    }

    @Test
    public void initError() throws Exception {
        //Arrange
        Character character = mock(Character.class);
        RepositoryProvider.setCharacterRepository(new TestRepo(character, true));
        //Act
        presenter.init(anyLong());
        //Assert
        verify(viewState).handleError(any(Throwable.class));
    }

    @Test
    public void initSuccess() throws Exception {
        //Arrange
        Character character = mock(Character.class);
        RepositoryProvider.setCharacterRepository(new TestRepo(character, false));
        //Act
        presenter.init(anyLong());
        //Assert
        verify(viewState).setCharacterImage(any());
        verify(viewState).setCharacterDescription(any());
        verify(viewState).setCharacterName(any());
    }

    @Test
    public void doActionInView() throws Exception {
        verifyNoMoreInteractions(viewState);
    }

    private class TestRepo implements CharacterRepository{

        private Character character;

        private boolean error;

        public TestRepo(Character character, boolean error) {
            this.character = character;
            this.error = error;
        }

        @NonNull
        @Override
        public Single<List<Character>> characters(Long offset, Long limit) {
            return null;
        }

        @Override
        public Single<Character> character(Long id) {
            return error ? Single.error(new Throwable()) : Single.just(character);
        }
    }
}
