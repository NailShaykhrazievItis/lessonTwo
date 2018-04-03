package com.itis.android.lessontwo.ui.series;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import com.itis.android.lessontwo.R;
import org.junit.*;
import org.junit.runner.*;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.core.IsNot.not;

/**
 * Created by Home on 03.04.2018.
 */
@RunWith(AndroidJUnit4.class)
public class SeriesActivityTest {

    @Rule
    public final ActivityTestRule<SeriesActivity> rule =
            new ActivityTestRule<>(SeriesActivity.class,false);

    private SeriesActivity seriesActivity;

    @Before
    public void setUp() throws Exception{
        seriesActivity = rule.getActivity();
    }

    @Test
    public void displayTextViews(){
        onView(withId(R.id.ct_series)).check(matches(isDisplayed()));
        onView(withId(R.id.tb_series)).check(matches(isDisplayed()));
        onView(withId(R.id.iv_series)).check(matches(isDisplayed()));
        onView(withId(R.id.tv_description_series)).check(matches(not(isDisplayed())));
    }
}

