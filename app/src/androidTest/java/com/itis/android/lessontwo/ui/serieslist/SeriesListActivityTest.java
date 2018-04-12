package com.itis.android.lessontwo.ui.serieslist;

import android.support.test.espresso.intent.Intents;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.itis.android.lessontwo.R;
import com.itis.android.lessontwo.ui.series.SeriesActivity;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import io.realm.Realm;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition;
import static android.support.test.espresso.contrib.RecyclerViewActions.scrollToPosition;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.core.IsNot.not;

/**
 * Created by Home on 03.04.2018.
 */
@RunWith(AndroidJUnit4.class)
public class SeriesListActivityTest {

    @Rule
    public final ActivityTestRule<SeriesListActivity> rule =
            new ActivityTestRule<>(SeriesListActivity.class);

    @Before
    public void setUp(){
        Intents.init();
    }

    @Test
    public void testRecyclerViewDisplayed() throws Exception{
        Thread.sleep(2000);

        onView(withId(R.id.tv_empty)).check(matches(not(isDisplayed())));
        onView(withId(R.id.rv_comics_list)).check(matches(isDisplayed()));
    }

    @Test
    public void testScrollRecyclerView() throws Exception{
        Thread.sleep(2000);

        onView(withId(R.id.rv_comics_list))
                .perform(scrollToPosition(4))
                .perform(scrollToPosition(3))
                .perform(scrollToPosition(1))
                .perform(scrollToPosition(2));
    }

    @Test
    public void testClickOnItem() throws Exception{
        Thread.sleep(2000);

        onView(withId(R.id.rv_comics_list))
                .perform(actionOnItemAtPosition(3,click()));
        Intents.intended(hasComponent(SeriesActivity.class.getName()));
    }

    @After
    public void tearDown() throws Exception{
        Intents.release();
        Realm.getDefaultInstance().executeTransaction(realm -> realm.deleteAll());
    }
}

