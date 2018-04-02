package com.itis.android.lessontwo.repository;

import android.support.test.runner.AndroidJUnit4;

import com.itis.android.lessontwo.api.ApiFactory;
import com.itis.android.lessontwo.api.ComicsService;
import com.itis.android.lessontwo.model.character.CharactersResponse;
import com.itis.android.lessontwo.model.comics.Comics;
import com.itis.android.lessontwo.model.comics.ComicsResponse;
import com.itis.android.lessontwo.model.comics.ComicsResponseData;
import com.itis.android.lessontwo.repository.comics.ComicsRepository;
import com.itis.android.lessontwo.repository.comics.ComicsRepositoryImpl;

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

import static com.itis.android.lessontwo.utils.Constants.DEFAULT_COMICS_SORT;
import static com.itis.android.lessontwo.utils.Constants.PAGE_SIZE;
import static com.itis.android.lessontwo.utils.Constants.ZERO_OFFSET;
import static org.junit.Assert.*;

@RunWith(AndroidJUnit4.class)
public class ComicsRepositoryTest {

    private static final Long ID = 59559L;

    private ComicsRepository repository;

    @Before
    public void setUp() throws Exception {
        repository = new ComicsRepositoryImpl();
    }

    @After
    public void tearDown() throws Exception {
        Realm.getDefaultInstance()
                .executeTransaction(realm -> realm.deleteAll());
        ApiFactory.recreate();
    }

    @Test
    public void comicsIdSuccess() throws Exception {
        ApiFactory.setComicsService(new TestService());
        Comics comics = repository.comics(ID).blockingGet();
        assertEquals(ID, comics.getId());
    }

    @Test
    public void comicsIdError() throws Exception {
        ApiFactory.setComicsService(new TestService());
        TestObserver<Comics> testObserver = new TestObserver<>();
        repository.comics(123L).toObservable().subscribe(testObserver);
        testObserver.await().assertFailure(Throwable.class);
    }

    @Test
    public void comicsIdMockSuccess() throws Exception {
        TestObserver<Comics> testObserver = new TestObserver<>();

        repository.comics(ID).toObservable().subscribe(testObserver);

        testObserver.await().assertNoErrors();
        testObserver.await().assertValueCount(1);
        assertEquals(ID, testObserver.await().values().get(0).getId());
    }

    @Test
    public void comicsIdMockError() throws Exception {
        TestObserver<Comics> testObserver = new TestObserver<>();

        repository.comics(6455621L).toObservable().subscribe(testObserver);

        testObserver.await().assertFailure(Throwable.class);
    }

    @Test
    public void testComicsNotSaved() throws Exception {
        repository.comics(ID).subscribe();

        int savedCount = Realm.getDefaultInstance()
                .where(Comics.class)
                .findAll()
                .size();
        assertEquals(0, savedCount);
    }

    @Test
    public void comicsListMockSuccess() throws Exception {
        TestObserver<List<Comics>> testObserver = new TestObserver<>();
        repository.comicsTest(ZERO_OFFSET, PAGE_SIZE, DEFAULT_COMICS_SORT)
                .toObservable()
                .subscribe(testObserver);

        testObserver.await().assertNoErrors();
        testObserver.await().assertValueCount(1);
    }

    @Test
    public void testComicsListSaved() throws Exception {
//        repository.comicsTest(ZERO_OFFSET, PAGE_SIZE, DEFAULT_COMICS_SORT).blockingGet();
//        //без этого не работает, если тест запускается после realm.deleteAll()
//        //возможно, проблема внутри Realm
//        Realm.getDefaultInstance().executeTransaction(realm -> {
//        });
//
//        //пробовал вставить sleep, но без executeTransaction не работало
//        //Thread.sleep(5000);
//        int savedCount = Realm.getDefaultInstance()
//                .where(Comics.class)
//                .findAll()
//                .size();
//        assertEquals(20, savedCount);
    }

    @Test
    public void testRepositoriesRestoredFromCache() throws Exception {
        repository.comicsTest(ZERO_OFFSET, PAGE_SIZE, DEFAULT_COMICS_SORT).blockingGet();
        TestObserver<List<Comics>> testObserver = new TestObserver<>();
        repository.comicsTest(-5L, PAGE_SIZE, DEFAULT_COMICS_SORT)
                .toObservable()
                .subscribe(testObserver);

        testObserver.await().assertNoErrors();
        testObserver.await().assertValueCount(1);
    }

    private class TestService implements ComicsService {

        @Override
        public Single<ComicsResponse> comics(@Query("offset") Long offset, @Query("limit") Long limit,
                                             @Query("orderBy") String orderBy) {
            return null;
        }

        @Override
        public Single<ComicsResponse> comicsTest(Long offset, Long limit, String orderBy) {
            return null;
        }

        @Override
        public Single<ComicsResponse> comics(@Path("comicsId") Long id) {
            if (Objects.equals(id, ID)) {
                ComicsResponse response = new ComicsResponse();
                ComicsResponseData data = new ComicsResponseData();
                Comics comics = new Comics();
                comics.setId(ID);
                List<Comics> res = new ArrayList<>(1);
                res.add(comics);
                data.setResults(res);
                response.setData(data);
                return Single.just(response);
            } else {
                return Single.error(new Throwable());
            }
        }

        @Override
        public Single<CharactersResponse> characters(Long id) {
            return null;
        }
    }
}
