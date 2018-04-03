package com.itis.android.lessontwo.repository;

import android.support.test.runner.AndroidJUnit4;

import com.itis.android.lessontwo.api.ApiFactory;
import com.itis.android.lessontwo.api.CharactersService;
import com.itis.android.lessontwo.api.CreatorsService;
import com.itis.android.lessontwo.model.entity.character.Character;
import com.itis.android.lessontwo.model.entity.character.CharactersResponse;
import com.itis.android.lessontwo.model.entity.character.CharactersResponseData;
import com.itis.android.lessontwo.model.entity.comics.ComicsResponse;
import com.itis.android.lessontwo.model.entity.creators.Creator;
import com.itis.android.lessontwo.model.entity.creators.CreatorsResponse;
import com.itis.android.lessontwo.model.entity.creators.CreatorsResponseData;

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
 * Created by valera071998@gmail.com on 31.03.2018.
 */
@RunWith(AndroidJUnit4.class)
public class CreatorRepositoryTest {

    private static final Long ID = 59539L;

    private CreatorRepository repository;

    @Before
    public void setUp() throws Exception {
        repository = new CreatorRepositoryImpl();
    }

    @After
    public void tearDown() throws Exception {
        Realm.getDefaultInstance()
                .executeTransaction(realm -> realm.delete(Creator.class));
    }

    @Test
    public void creatorIdSuccess() throws Exception {
        ApiFactory.setCreatorsService(new TestService());
        Creator creator = repository.creator(59539L).blockingGet();
        assertEquals(ID, creator.getId());
    }

    @Test
    public void creatorIdError() throws Exception {
        ApiFactory.setCreatorsService(new TestService());
        TestObserver<Creator> testObserver = new TestObserver<>();
        repository.creator(123L).toObservable().subscribe(testObserver);
        testObserver.await().assertFailure(Throwable.class);
    }

    @Test
    public void creatorIdMockSuccess() throws Exception {
        TestObserver<Creator> testObserver = new TestObserver<>();

        repository.creator(59539L).toObservable().subscribe(testObserver);

        testObserver.await().assertNoErrors();
        testObserver.await().assertValueCount(1);
        assertEquals(ID, testObserver.await().values().get(0).getId());
    }

    @Test
    public void creatorIdMockError() throws Exception {
        TestObserver<Creator> testObserver = new TestObserver<>();

        repository.creator(6455621L).toObservable().subscribe(testObserver);

        testObserver.await().assertFailure(Throwable.class);
    }

    @Test
    public void testCreatorNotSaved() throws Exception {
        repository.creator(59539L).subscribe();

        int savedCount = Realm.getDefaultInstance()
                .where(Creator.class)
                .findAll()
                .size();
        assertEquals(0, savedCount);
    }

    @Test
    public void creatorsListMockSuccess() throws Exception {
        TestObserver<List<Creator>> testObserver = new TestObserver<>();
        repository.creatorsTest(ZERO_OFFSET, PAGE_SIZE, DEFAULT_CHARACTER_SORT)
                .toObservable()
                .subscribe(testObserver);

        testObserver.await().assertNoErrors();
        testObserver.await().assertValueCount(20);
    }

    @Test
    public void testCreatorsListSaved() throws Exception {
        repository.creatorsTest(ZERO_OFFSET, PAGE_SIZE, DEFAULT_CHARACTER_SORT).subscribe(creator -> {

            int savedCount = Realm.getDefaultInstance()
                    .where(Creator.class)
                    .findAll()
                    .size();
            assertEquals(20, savedCount);
        });
    }

    @Test
    public void testRepositoriesRestoredFromCache() throws Exception {
        repository.creators(ZERO_OFFSET, PAGE_SIZE, DEFAULT_CHARACTER_SORT).subscribe();

        //force error for loading

        TestObserver<List<Creator>> testObserver = new TestObserver<>();
        repository.creators(ZERO_OFFSET, PAGE_SIZE, DEFAULT_CHARACTER_SORT)
                .toObservable()
                .subscribe(testObserver);

        testObserver.assertNoErrors();
        testObserver.assertValueCount(20);
    }

    private class TestService implements CreatorsService {

        @Override
        public Single<CreatorsResponse> creators(@Query("offset") Long offset, @Query("limit") Long limit,
                                                   @Query("orderBy") String orderBy) {
            CreatorsResponse response = new CreatorsResponse();
            CreatorsResponseData data = new CreatorsResponseData();
            List<Creator> res = new ArrayList<>();
            for(int i = 0; i < 20; i++){
                Creator creator = new Creator();
                creator.setId((long) i);
                res.add(creator);
            }
            data.setResults(res);
            response.setData(data);
            return Single.just(response);
        }

        @Override
        public Single<CreatorsResponse> creatorsTest(Long offset, Long limit, String orderBy) {
            CreatorsResponse response = new CreatorsResponse();
            CreatorsResponseData data = new CreatorsResponseData();
            List<Creator> res = new ArrayList<>();
            for(int i = 0; i < 20; i++){
                Creator creator = new Creator();
                creator.setId((long) i);
                res.add(creator);
            }
            data.setResults(res);
            response.setData(data);
            return Single.just(response);
        }

        @Override
        public Single<CreatorsResponse> creator(@Path("creatorId") Long id) {
            if (Objects.equals(id, ID)) {
                CreatorsResponse response = new CreatorsResponse();
                CreatorsResponseData data = new CreatorsResponseData();
                Creator creator = new Creator();
                creator.setId(59539L);
                List<Creator> res = new ArrayList<>(1);
                res.add(creator);
                data.setResults(res);
                response.setData(data);
                return Single.just(response);
            } else {
                return Single.error(new Throwable());
            }
        }

        @Override
        public Single<ComicsResponse> comics(@Path("creatorId") Long id) {
            return null;
        }
    }
}