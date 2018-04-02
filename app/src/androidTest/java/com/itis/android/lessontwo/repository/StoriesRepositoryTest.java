package com.itis.android.lessontwo.repository;

import static com.itis.android.lessontwo.utils.Constants.PAGE_SIZE;
import static com.itis.android.lessontwo.utils.Constants.ZERO_OFFSET;
import static org.junit.Assert.*;

import android.support.test.runner.AndroidJUnit4;
import com.itis.android.lessontwo.api.ApiFactory;
import com.itis.android.lessontwo.api.services.StoriesService;
import com.itis.android.lessontwo.model.story.Story;
import com.itis.android.lessontwo.model.story.StoryResponse;
import com.itis.android.lessontwo.model.story.StoryResponseData;
import io.reactivex.Single;
import io.reactivex.observers.TestObserver;
import io.realm.Realm;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import org.junit.*;
import org.junit.runner.*;

@RunWith(AndroidJUnit4.class)
public class StoriesRepositoryTest {

    private static final Long ID = 7L;

    private StoriesRepository repository;

    @Before
    public void setUp() {
        repository = new StoriesRepositoryImpl();
    }

    @After
    public void tearDown() {
        Realm.getDefaultInstance()
                .executeTransaction(realm -> realm.delete(Story.class));
    }

    @Test
    public void comicsIdSuccess() {
        ApiFactory.setStoriesService(new TestService());
        Story story = repository.stories(7L).blockingGet();
        assertEquals(ID, story.getId());
    }

    @Test
    public void comicsIdError() throws Exception {
        ApiFactory.setStoriesService(new TestService());
        TestObserver<Story> testObserver = new TestObserver<>();
        repository.stories(131L).toObservable().subscribe(testObserver);
        testObserver.await().assertFailure(Throwable.class);
    }

    @Test
    public void comicsIdMockSuccess() throws Exception {
        TestObserver<Story> testObserver = new TestObserver<>();

        repository.stories(7L).toObservable().subscribe(testObserver);

        testObserver.await().assertNoErrors();
        testObserver.await().assertValueCount(1);
        assertEquals(ID, testObserver.await().values().get(0).getId());
    }

    @Test
    public void comicsIdMockError() throws Exception {
        TestObserver<Story> testObserver = new TestObserver<>();

        repository.stories(621L).toObservable().subscribe(testObserver);

        testObserver.await().assertFailure(Throwable.class);
    }

    @Test
    public void testComicsNotSaved() {
        repository.stories(7L).subscribe();

        int savedCount = Realm.getDefaultInstance()
                .where(Story.class)
                .findAll()
                .size();
        assertEquals(0, savedCount);
    }

    @Test
    public void comicsListMockSuccess() throws Exception {
        TestObserver<List<Story>> testObserver = new TestObserver<>();
        repository.storiesTest(ZERO_OFFSET, PAGE_SIZE)
                .toObservable()
                .subscribe(testObserver);

        testObserver.await().assertNoErrors();
        testObserver.await().assertValueCount(20);
    }

    @Test
    public void testComicsListSaved() {
        repository.storiesTest(ZERO_OFFSET, PAGE_SIZE).subscribe();

        int savedCount = Realm.getDefaultInstance()
                .where(Story.class)
                .findAll()
                .size();
        assertEquals(20, savedCount);
    }

    @Test
    public void testRepositoriesRestoredFromCache() {
        repository.stories(ZERO_OFFSET, PAGE_SIZE).subscribe();

        //force error for loading

        TestObserver<List<Story>> testObserver = new TestObserver<>();
        repository.stories(ZERO_OFFSET, PAGE_SIZE)
                .toObservable()
                .subscribe(testObserver);

        testObserver.assertNoErrors();
        testObserver.assertValueCount(20);
    }

    private class TestService implements StoriesService {

        @Override
        public Single<StoryResponse> stories(final Long offset, final Long limit) {
            return null;
        }

        @Override
        public Single<StoryResponse> stories(final Long id) {
            if (Objects.equals(id, ID)) {
                StoryResponse response = new StoryResponse();
                StoryResponseData data = new StoryResponseData();
                Story story = new Story();
                story.setId(1334L);
                List<Story> res = new ArrayList<>(1);
                res.add(story);
                data.setResults(res);
                response.setData(data);
                return Single.just(response);
            } else {
                return Single.error(new Throwable());
            }
        }

        @Override
        public Single<StoryResponse> storiesTest(final Long offset, final Long limit) {
            return null;
        }
    }
}
