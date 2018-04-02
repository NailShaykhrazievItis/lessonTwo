package com.itis.android.lessontwo.repository;

import android.support.test.runner.AndroidJUnit4;
import android.util.Log;

import com.itis.android.lessontwo.api.ApiFactory;
import com.itis.android.lessontwo.api.ComicsService;
import com.itis.android.lessontwo.model.entity.character.CharactersResponse;
import com.itis.android.lessontwo.model.entity.comics.Comics;
import com.itis.android.lessontwo.model.entity.comics.ComicsResponse;
import com.itis.android.lessontwo.model.entity.comics.ComicsResponseData;

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
import static org.junit.Assert.assertEquals;

/**
 * Created by Nail Shaykhraziev on 25.03.2018.
 */
@RunWith(AndroidJUnit4.class)
public class ComicsRepositoryTest {

    private static final Long ID = 59539L;

    private ComicsRepository repository;

    @Before
    public void setUp() throws Exception {
        repository = new ComicsRepositoryImpl();
//        ApiFactory.setComicsService(new TestService());
    }

    @After
    public void tearDown() throws Exception {
        Realm.getDefaultInstance()
                .executeTransaction(realm -> realm.delete(Comics.class));
    }

    @Test
    public void comicsIdSuccess() throws Exception {
        ApiFactory.setComicsService(new TestService());
        Comics comics = repository.comics(59539L).blockingGet();
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

        repository.comics(59539L).toObservable().subscribe(testObserver);

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
        repository.comics(59539L).subscribe();

        int savedCount = Realm.getDefaultInstance()
                .where(Comics.class)
                .findAll()
                .size();
        assertEquals(0, savedCount);
    }

    @Test
    public void comicsListMockSuccess() throws Exception {
        Log.d("TAG","mockSuccess");

        List<Comics> list = repository.comicsTest(ZERO_OFFSET, PAGE_SIZE, DEFAULT_COMICS_SORT).blockingGet();
        Log.d("TAG","list size = " + list.size());

        repository.comicsTest(ZERO_OFFSET, PAGE_SIZE, DEFAULT_COMICS_SORT)
                .test()
                .assertNoErrors()
                .assertValueCount(20);

       /* Observable.just(1)
                .test()
                .assertSubscribed()
                .assertValues(1)
                .assertComplete()
                .assertNoErrors();*/

       /* testObserver.await().assertNoErrors();
        Log.d("TAG","findAll");
        testObserver.await().assertValueCount(20);
        Log.d("TAG","findAfter");*/
    }

    @Test
    public void testComicsListSaved() throws Exception {
        Log.d("TAG","listSaved");

        repository.comicsTest(ZERO_OFFSET, PAGE_SIZE, DEFAULT_COMICS_SORT).subscribe(comics -> {

            int savedCount = Realm.getDefaultInstance()
                    .where(Comics.class)
                    .findAll()
                    .size();

            assertEquals(20, savedCount);
        });

    }

    @Test
    public void testRepositoriesRestoredFromCache() throws Exception {
        Log.d("TAG","restore");
        repository.comics(ZERO_OFFSET, PAGE_SIZE, DEFAULT_COMICS_SORT).subscribe();


        //force error for loading


        TestObserver<List<Comics>> testObserver = new TestObserver<>();
        repository.comics(ZERO_OFFSET, PAGE_SIZE, DEFAULT_COMICS_SORT)
                .toObservable()
                .subscribe(testObserver);

        testObserver.assertNoErrors();
        testObserver.assertValueCount(20);
    }

    private class TestService implements ComicsService {

        @Override
        public Single<ComicsResponse> comics(@Query("offset") Long offset, @Query("limit") Long limit,
                                             @Query("orderBy") String orderBy) {
            ComicsResponse response = new ComicsResponse();
            ComicsResponseData data = new ComicsResponseData();
            List<Comics> res = new ArrayList<>();
            for(int i = 0; i < 20; i++){
                Comics comics = new Comics();
                comics.setId((long) i);
                res.add(comics);
            }
            data.setResults(res);
            response.setData(data);
            return Single.just(response);
        }

        @Override
        public Single<ComicsResponse> comicsTest(Long offset, Long limit, String orderBy) {
            ComicsResponse response = new ComicsResponse();
            ComicsResponseData data = new ComicsResponseData();
            List<Comics> res = new ArrayList<>();
            for(int i = 0; i < 20; i++){
                Comics comics = new Comics();
                comics.setId((long) i);
                res.add(comics);
            }
            Log.d("TAG","res size = " + res.size());
            data.setResults(res);
            response.setData(data);
            return Single.just(response);
        }

        @Override
        public Single<ComicsResponse> comics(@Path("comicsId") Long id) {
            if (Objects.equals(id, ID)) {
                ComicsResponse response = new ComicsResponse();
                ComicsResponseData data = new ComicsResponseData();
                Comics comics = new Comics();
                comics.setId(59539L);
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