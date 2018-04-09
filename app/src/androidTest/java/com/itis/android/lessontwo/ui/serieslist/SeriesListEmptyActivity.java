package com.itis.android.lessontwo.ui.serieslist;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.itis.android.lessontwo.R;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.core.IsNot.not;

/**
 * Created by Home on 03.04.2018.
 */
@RunWith(AndroidJUnit4.class)
public class SeriesListEmptyActivity {
    @Rule
    public final ActivityTestRule<SeriesListActivity> rule
            = new ActivityTestRule<>(SeriesListActivity.class, false);

    @Test
    public void testErrorDisplayed() throws Exception{

        onView(withId(R.id.tv_empty)).check(matches(isDisplayed()));
        onView(withId(R.id.rv_comics_list)).check(matches(not(isDisplayed())));
        onView(withId(R.id.tv_empty)).check(matches(withText(R.string.text_empty_elements)));
    }
}
