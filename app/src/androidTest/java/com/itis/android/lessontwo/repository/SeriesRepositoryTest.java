package com.itis.android.lessontwo.repository;

import android.support.test.runner.AndroidJUnit4;

import com.itis.android.lessontwo.api.ApiFactory;
import com.itis.android.lessontwo.api.SeriesService;
import com.itis.android.lessontwo.model.series.Series;
import com.itis.android.lessontwo.model.series.SeriesResponse;
import com.itis.android.lessontwo.model.series.SeriesResponseData;

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
public class SeriesRepositoryTest {

    private static final Long ID_OK = 18454L;

    private SeriesRepository repository;

    @Before
    public void setUp() throws Exception {
        repository = new SeriesRepositoryImpl();
    }

    @After
    public void tearDown() throws Exception {
        Realm.getDefaultInstance()
                .executeTransaction(realm -> realm.deleteAll());
        ApiFactory.recreate();
    }

    @Test
    public void seriesIdSuccess() throws Exception {
        ApiFactory.setSeriesService(new TestService());
        Series series = repository.series(ID_OK).blockingGet();
        assertEquals(ID_OK, series.getId());
    }

    @Test
    public void seriesIdError() throws Exception {
        ApiFactory.setSeriesService(new TestService());
        TestObserver<Series> testObserver = new TestObserver<>();
        repository.series(123L).toObservable().subscribe(testObserver);
        testObserver.await().assertFailure(Throwable.class);
    }

    @Test
    public void seriesIdMockSuccess() throws Exception {
        TestObserver<Series> testObserver = new TestObserver<>();

        repository.series(ID_OK).toObservable().subscribe(testObserver);

        testObserver.await().assertNoErrors();
        testObserver.await().assertValueCount(1);
        assertEquals(ID_OK, testObserver.await().values().get(0).getId());
    }

    @Test
    public void seriesIdMockError() throws Exception {
        TestObserver<Series> testObserver = new TestObserver<>();

        repository.series(6455621L).toObservable().subscribe(testObserver);

        testObserver.await().assertFailure(Throwable.class);
    }

    @Test
    public void testSeriesNotSaved() throws Exception {
        repository.series(ID_OK).subscribe();

        int savedCount = Realm.getDefaultInstance()
                .where(Series.class)
                .findAll()
                .size();
        assertEquals(0, savedCount);
    }

    @Test
    public void seriesListMockSuccess() throws Exception {
        TestObserver<List<Series>> testObserver = new TestObserver<>();
        repository.seriesTest(ZERO_OFFSET, PAGE_SIZE)
                .toObservable()
                .subscribe(testObserver);

        testObserver.await().assertNoErrors();
        testObserver.await().assertValueCount(1);
    }

    @Test
    public void testSeriesListSaved() throws Exception {
        repository.seriesTest(ZERO_OFFSET, PAGE_SIZE).blockingGet();

        //без этого не работает, если тест запускается после realm.deleteAll()
        //возможно, проблема внутри Realm
        Realm.getDefaultInstance().executeTransaction(realm -> {
        });

        //пробовал вставить sleep, но без executeTransaction не работало
        //Thread.sleep(5000);

        int savedCount = Realm.getDefaultInstance()
                .where(Series.class)
                .findAll()
                .size();
        assertEquals(20, savedCount);
    }

    @Test
    public void testRepositoriesRestoredFromCache() throws Exception {
        //write to DB
        repository.seriesTest(ZERO_OFFSET, PAGE_SIZE).blockingGet();

        TestObserver<List<Series>> testObserver = new TestObserver<>();

        //force error for loading
        repository.seriesTest(-5L, PAGE_SIZE)
                .toObservable()
                .subscribe(testObserver);

        testObserver.await().assertNoErrors();
        testObserver.await().assertValueCount(1);
    }

    private class TestService implements SeriesService {

        @Override
        public Single<SeriesResponse> series(@Query("offset") Long offset, @Query("limit") Long limit) {
            return null;
        }

        @Override
        public Single<SeriesResponse> seriesTest(Long offset, Long limit) {
            return null;
        }

        @Override
        public Single<SeriesResponse> series(@Path("seriesId") Long id) {
            if (Objects.equals(id, ID_OK)) {
                SeriesResponse response = new SeriesResponse();
                SeriesResponseData data = new SeriesResponseData();
                Series series = new Series();
                series.setId(ID_OK);
                List<Series> res = new ArrayList<>(1);
                res.add(series);
                data.setResults(res);
                response.setData(data);
                return Single.just(response);
            } else {
                return Single.error(new Throwable());
            }
        }
    }
}
