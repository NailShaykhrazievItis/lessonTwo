package com.itis.android.lessontwo.repository;

import android.support.test.runner.AndroidJUnit4;

import com.itis.android.lessontwo.api.ApiFactory;
import com.itis.android.lessontwo.api.CharactersService;
import com.itis.android.lessontwo.model.character.Character;
import com.itis.android.lessontwo.model.character.CharactersResponse;
import com.itis.android.lessontwo.model.character.CharactersResponseData;
import com.itis.android.lessontwo.model.comics.ComicsResponse;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import io.reactivex.Single;
import io.reactivex.observers.TestObserver;
import io.realm.Realm;
import retrofit2.http.Path;
import retrofit2.http.Query;

import static com.itis.android.lessontwo.utils.Constants.PAGE_SIZE;
import static com.itis.android.lessontwo.utils.Constants.ZERO_OFFSET;
import static org.junit.Assert.assertEquals;

/**
 * Created by a9 on 03.04.18.
 */
@RunWith(AndroidJUnit4.class)
public class CharacterRepositoryTest {

    private static final Long ID_OK = 1011334L;

    private CharacterRepository repository;

    @Before
    public void setUp() throws Exception {
        repository = new CharacterRepositoryImpl();
    }

    @After
    public void tearDown() throws Exception {
        Realm.getDefaultInstance()
                .executeTransaction(realm -> realm.deleteAll());
        ApiFactory.recreate();
    }

    @Test
    public void characterIdSuccess() throws Exception {
        ApiFactory.setCharactersService(new TestService());
        Character character = repository.character(ID_OK).blockingGet();
        assertEquals(ID_OK, character.getId());
    }

    @Test
    public void characterIdError() throws Exception {
        ApiFactory.setCharactersService(new TestService());
        TestObserver<Character> testObserver = new TestObserver<>();
        repository.character(123L).toObservable().subscribe(testObserver);
        testObserver.await().assertFailure(Throwable.class);
    }

    @Test
    public void characterIdMockSuccess() throws Exception {
        TestObserver<Character> testObserver = new TestObserver<>();

        repository.character(ID_OK).toObservable().subscribe(testObserver);

        testObserver.await().assertNoErrors();
        testObserver.await().assertValueCount(1);
        assertEquals(ID_OK, testObserver.await().values().get(0).getId());
    }

    @Test
    public void characterIdMockError() throws Exception {
        TestObserver<Character> testObserver = new TestObserver<>();

        repository.character(6455621L).toObservable().subscribe(testObserver);

        testObserver.await().assertFailure(Throwable.class);
    }

    @Test
    public void testCharacterNotSaved() throws Exception {
        repository.character(ID_OK).subscribe();

        int savedCount = Realm.getDefaultInstance()
                .where(Character.class)
                .findAll()
                .size();
        assertEquals(0, savedCount);
    }

    @Test
    public void characterListMockSuccess() throws Exception {
        TestObserver<List<Character>> testObserver = new TestObserver<>();
        repository.charactersTest(ZERO_OFFSET, PAGE_SIZE)
                .toObservable()
                .subscribe(testObserver);

        testObserver.await().assertNoErrors();
        testObserver.await().assertValueCount(1);
    }

    @Test
    public void testCharacterListSaved() throws Exception {
        repository.charactersTest(ZERO_OFFSET, PAGE_SIZE).blockingGet();

        //без этого не работает, если тест запускается после realm.deleteAll()
        //возможно, проблема внутри Realm
        Realm.getDefaultInstance().executeTransaction(realm -> {
        });

        //пробовал вставить sleep, но без executeTransaction не работало
        //Thread.sleep(5000);

        int savedCount = Realm.getDefaultInstance()
                .where(Character.class)
                .findAll()
                .size();
        assertEquals(20, savedCount);
    }

    @Test
    public void testRepositoriesRestoredFromCache() throws Exception {
        //write to DB
        repository.charactersTest(ZERO_OFFSET, PAGE_SIZE).blockingGet();

        TestObserver<List<Character>> testObserver = new TestObserver<>();

        //force error for loading
        repository.charactersTest(-5L, PAGE_SIZE)
                .toObservable()
                .subscribe(testObserver);

        testObserver.await().assertNoErrors();
        testObserver.await().assertValueCount(1);
    }

    private class TestService implements CharactersService {
        @Override
        public Single<CharactersResponse> characters(@Query("offset") Long offset, @Query("limit") Long limit) {
            return null;
        }

        @Override
        public Single<CharactersResponse> charactersTest(Long offset, Long limit) {
            return null;
        }

        @Override
        public Single<CharactersResponse> character(@Path("characterId") Long id) {
            if (Objects.equals(id, ID_OK)) {
                CharactersResponse response = new CharactersResponse();
                CharactersResponseData data = new CharactersResponseData();
                Character character = new Character();
                character.setId(ID_OK);
                List<Character> res = new ArrayList<>(1);
                res.add(character);
                data.setResults(res);
                response.setData(data);
                return Single.just(response);
            } else {
                return Single.error(new Throwable());
            }
        }

        @Override
        public Single<ComicsResponse> comicsByCharacter(Long id) {
            return null;
        }
    }
}
