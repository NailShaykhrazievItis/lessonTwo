package com.itis.android.lessontwo.ui.character;

import static org.mockito.ArgumentMatchers.*;
import static org.powermock.api.mockito.PowerMockito.mockStatic;

import com.itis.android.lessontwo.api.ApiFactory;
import com.itis.android.lessontwo.model.character.Character;
import com.itis.android.lessontwo.repository.CharactersRepositoryImpl;
import com.itis.android.lessontwo.repository.RepositoryProvider;
import com.itis.android.lessontwo.utils.RxUtils;
import io.reactivex.Single;
import org.junit.*;
import org.junit.runner.*;
import org.mockito.*;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

/**
 * Created by Admin on 25.03.2018.
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest({RxUtils.class, ApiFactory.class})
public class CharacterPresenterTest {

    @SuppressWarnings("WeakerAccess")
    @Mock
    CharacterView$$State viewState;

    @SuppressWarnings("WeakerAccess")
    @Mock
    CharactersRepositoryImpl repository;

    private CharacterPresenter presenter;


    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        presenter = Mockito.spy(CharacterPresenter.class);
        presenter.setViewState(viewState);
        mockStatic(ApiFactory.class);
        mockStatic(RxUtils.class);
    }

    @Test
    public void onFirstViewAttach() throws Exception {
        // Arrange
        Mockito.doNothing().when(presenter).init(anyLong());
        // Act
        presenter.onFirstViewAttach();
        // Assert
        Mockito.verify(viewState).getCharacterId();
    }

    @Test
    public void initMockError() throws Exception {
        // Arrange
        Mockito.when(repository.characters(anyLong()))
                .thenReturn(Single.error(new Throwable()));
        RepositoryProvider.setCharactersRepository(repository);
        // Act
        presenter.init(anyLong());
        // Assert
        Mockito.verify(viewState).handleError(Mockito.any(Throwable.class));
    }

    @Test
    public void initMockSuccess() throws Exception {
        // Arrange
        Character character = Mockito.mock(Character.class);
        Mockito.when(repository.characters(anyLong()))
                .thenReturn(Single.just(character));
        RepositoryProvider.setCharactersRepository(repository);
        // Act
        presenter.init(anyLong());
        // Assert
        Mockito.verify(viewState).setImage(character);
        Mockito.verify(viewState).setDescription(character);
    }
}