package com.itis.android.lessontwo.repository;

import android.support.test.runner.AndroidJUnit4;

import com.itis.android.lessontwo.api.ApiFactory;
import com.itis.android.lessontwo.api.services.CharactersService;
import com.itis.android.lessontwo.model.character.Character;
import com.itis.android.lessontwo.model.character.CharactersResponse;
import com.itis.android.lessontwo.model.character.CharactersResponseData;

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

import static com.itis.android.lessontwo.utils.Constants.PAGE_SIZE;
import static com.itis.android.lessontwo.utils.Constants.ZERO_OFFSET;
import static org.junit.Assert.*;

@RunWith(AndroidJUnit4.class)
public class CharactersRepositoryTest {

    private static final Long ID = 1011334L;

    private CharactersRepository repository;

    @Before
    public void setUp() {
        repository = new CharactersRepositoryImpl();
    }

    @After
    public void tearDown() {
        Realm.getDefaultInstance()
                .executeTransaction(realm -> realm.delete(Character.class));
    }

    @Test
    public void characterIdSuccess() {
        ApiFactory.setCharactersService(new TestService());
        Character character = repository.characters(1011334L).blockingGet();
        assertEquals(ID, character.getId());
    }

    @Test
    public void characterIdError() throws Exception {
        ApiFactory.setCharactersService(new TestService());
        TestObserver<Character> testObserver = new TestObserver<>();
        repository.characters(123L).toObservable().subscribe(testObserver);
        testObserver.await().assertFailure(Throwable.class);
    }

    @Test
    public void comicsIdMockSuccess() throws Exception {
        TestObserver<Character> testObserver = new TestObserver<>();

        repository.characters(1011334L).toObservable().subscribe(testObserver);

        testObserver.await().assertNoErrors();
        testObserver.await().assertValueCount(1);
        assertEquals(ID, testObserver.await().values().get(0).getId());
    }

    @Test
    public void comicsIdMockError() throws Exception {
        TestObserver<Character> testObserver = new TestObserver<>();

        repository.characters(6455621L).toObservable().subscribe(testObserver);

        testObserver.await().assertFailure(Throwable.class);
    }

    @Test
    public void testComicsNotSaved() {
        repository.characters(1011334L).subscribe();

        int savedCount = Realm.getDefaultInstance()
                .where(Character.class)
                .findAll()
                .size();
        assertEquals(0, savedCount);
    }

    @Test
    public void testComicsListSaved() {
        repository.charactersTest(ZERO_OFFSET, PAGE_SIZE).subscribe();

        int savedCount = Realm.getDefaultInstance()
                .where(Character.class)
                .findAll()
                .size();
        assertEquals(20, savedCount);
    }

    @Test
    public void comicsListMockSuccess() throws Exception {
        TestObserver<List<Character>> testObserver = new TestObserver<>();
        repository.charactersTest(ZERO_OFFSET, PAGE_SIZE)
                .toObservable()
                .subscribe(testObserver);

        testObserver.await().assertNoErrors();
        testObserver.await().assertValueCount(20);
    }

    @Test
    public void testRepositoriesRestoredFromCache() {
        repository.characters(ZERO_OFFSET, PAGE_SIZE).subscribe();

        //force error for loading

        TestObserver<List<Character>> testObserver = new TestObserver<>();
        repository.characters(ZERO_OFFSET, PAGE_SIZE)
                .toObservable()
                .subscribe(testObserver);

        testObserver.assertNoErrors();
        testObserver.assertValueCount(20);
    }

    private class TestService implements CharactersService {

        @Override
        public Single<CharactersResponse> characters(Long offset, Long limit) {
            return null;
        }

        @Override
        public Single<CharactersResponse> characters(Long id) {
            if (Objects.equals(id, ID)) {
                CharactersResponse response = new CharactersResponse();
                CharactersResponseData data = new CharactersResponseData();
                Character character = new Character();
                character.setId(1011334L);
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
        public Single<CharactersResponse> charactersTest(Long offset, Long limit) {
            return null;
        }
    }
}