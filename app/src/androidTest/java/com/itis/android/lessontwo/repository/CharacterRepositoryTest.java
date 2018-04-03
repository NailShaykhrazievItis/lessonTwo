package com.itis.android.lessontwo.repository;

import android.support.test.runner.AndroidJUnit4;

import com.itis.android.lessontwo.api.ApiFactory;
import com.itis.android.lessontwo.api.CharactersService;
import com.itis.android.lessontwo.model.entity.character.Character;
import com.itis.android.lessontwo.model.entity.character.CharactersResponse;
import com.itis.android.lessontwo.model.entity.character.CharactersResponseData;
import com.itis.android.lessontwo.model.entity.comics.ComicsResponse;

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

import static com.itis.android.lessontwo.utils.Constants.DEFAULT_CHARACTER_SORT;
import static com.itis.android.lessontwo.utils.Constants.PAGE_SIZE;
import static com.itis.android.lessontwo.utils.Constants.ZERO_OFFSET;
import static org.junit.Assert.assertEquals;

/**
 * Created by Ruslan on 31.03.2018.
 */
@RunWith(AndroidJUnit4.class)
public class CharacterRepositoryTest {

    private static final Long ID = 59539L;

    private CharacterRepository repository;

    @Before
    public void setUp() throws Exception {
        repository = new CharacterRepositoryImpl();
    }

    @After
    public void tearDown() throws Exception {
        Realm.getDefaultInstance()
                .executeTransaction(realm -> realm.delete(Character.class));
    }

    @Test
    public void charactersIdSuccess() throws Exception {
        ApiFactory.setCharactersService(new TestService());
        Character character = repository.character(59539L).blockingGet();
        assertEquals(ID, character.getId());
    }

    @Test
    public void charactersIdError() throws Exception {
        ApiFactory.setCharactersService(new TestService());
        TestObserver<Character> testObserver = new TestObserver<>();
        repository.character(123L).toObservable().subscribe(testObserver);
        testObserver.await().assertFailure(Throwable.class);
    }

    @Test
    public void charactersIdMockSuccess() throws Exception {
        TestObserver<Character> testObserver = new TestObserver<>();

        repository.character(59539L).toObservable().subscribe(testObserver);

        testObserver.await().assertNoErrors();
        testObserver.await().assertValueCount(1);
        assertEquals(ID, testObserver.await().values().get(0).getId());
    }

    @Test
    public void charactersIdMockError() throws Exception {
        TestObserver<Character> testObserver = new TestObserver<>();

        repository.character(6455621L).toObservable().subscribe(testObserver);

        testObserver.await().assertFailure(Throwable.class);
    }

    @Test
    public void testCharactersNotSaved() throws Exception {
        repository.character(59539L).subscribe();

        int savedCount = Realm.getDefaultInstance()
                .where(Character.class)
                .findAll()
                .size();
        assertEquals(0, savedCount);
    }

    @Test
    public void charactersListMockSuccess() throws Exception {
        TestObserver<List<Character>> testObserver = new TestObserver<>();
        repository.charactersTest(ZERO_OFFSET, PAGE_SIZE, DEFAULT_CHARACTER_SORT)
                .toObservable()
                .subscribe(testObserver);

        testObserver.await().assertNoErrors();
        testObserver.await().assertValueCount(20);
    }

    @Test
    public void testCharactersListSaved() throws Exception {
        repository.charactersTest(ZERO_OFFSET, PAGE_SIZE, DEFAULT_CHARACTER_SORT).subscribe(comics -> {

            int savedCount = Realm.getDefaultInstance()
                    .where(Character.class)
                    .findAll()
                    .size();
            assertEquals(20, savedCount);
        });
    }

    @Test
    public void testRepositoriesRestoredFromCache() throws Exception {
        repository.characters(ZERO_OFFSET, PAGE_SIZE, DEFAULT_CHARACTER_SORT).subscribe();

        //force error for loading

        TestObserver<List<Character>> testObserver = new TestObserver<>();
        repository.characters(ZERO_OFFSET, PAGE_SIZE, DEFAULT_CHARACTER_SORT)
                .toObservable()
                .subscribe(testObserver);

        testObserver.assertNoErrors();
        testObserver.assertValueCount(20);
    }

    private class TestService implements CharactersService {

        @Override
        public Single<CharactersResponse> characters(@Query("offset") Long offset, @Query("limit") Long limit,
                                             @Query("orderBy") String orderBy) {
            CharactersResponse response = new CharactersResponse();
            CharactersResponseData data = new CharactersResponseData();
            List<Character> res = new ArrayList<>();
            for(int i = 0; i < 20; i++){
                Character character = new Character();
                character.setId((long) i);
                res.add(character);
            }
            data.setResults(res);
            response.setData(data);
            return Single.just(response);
        }

        @Override
        public Single<CharactersResponse> charactersTest(Long offset, Long limit, String orderBy) {
            CharactersResponse response = new CharactersResponse();
            CharactersResponseData data = new CharactersResponseData();
            List<Character> res = new ArrayList<>();
            for(int i = 0; i < 20; i++){
                Character character = new Character();
                character.setId((long) i);
                res.add(character);
            }
            data.setResults(res);
            response.setData(data);
            return Single.just(response);
        }

        @Override
        public Single<CharactersResponse> character(@Path("characterId") Long id) {
            if (Objects.equals(id, ID)) {
                CharactersResponse response = new CharactersResponse();
                CharactersResponseData data = new CharactersResponseData();
                Character character = new Character();
                character.setId(59539L);
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
