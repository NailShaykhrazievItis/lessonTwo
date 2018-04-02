package com.itis.android.lessontwo.ui.storylist;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.contrib.RecyclerViewActions.scrollToPosition;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.core.IsNot.*;

import android.support.test.espresso.intent.Intents;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import com.itis.android.lessontwo.R;
import com.itis.android.lessontwo.ui.storieslist.StoriesListActivity;
import io.realm.Realm;
import org.junit.*;
import org.junit.runner.*;

@RunWith(AndroidJUnit4.class)
public class StoriesListActivityTest {
    @Rule
    public final ActivityTestRule<StoriesListActivity> rule = new ActivityTestRule<>(StoriesListActivity.class);

    @Before
    public void setUp() {
        Intents.init();
    }

    @Test
    public void testRecyclerViewDisplayed() {
        onView(withId(R.id.tv_empty_stories)).check(matches(not(isDisplayed())));
        onView(withId(R.id.rv_stories_list)).check(matches(isDisplayed()));
    }

    @Test
    public void testScrollRecyclerView() {
        onView(withId(R.id.rv_stories_list))
                .perform(scrollToPosition(4))
                .perform(scrollToPosition(3))
                .perform(scrollToPosition(1))
                .perform(scrollToPosition(2));
    }

    @After
    public void tearDown() {
        Intents.release();
        Realm.getDefaultInstance().executeTransaction(realm -> realm.deleteAll());
    }
}
