package com.itis.android.lessontwo.ui.comics;

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


@RunWith(AndroidJUnit4.class)
public class ComicsActivityTest {

    @Rule
    public final ActivityTestRule<ComicsActivity> rule =
            new ActivityTestRule<>(ComicsActivity.class,false);

    private ComicsActivity comicsActivity;

    @Before
    public void setUp() throws Exception{
        comicsActivity = rule.getActivity();
    }

    @Test
    public void displayTextViews(){
        onView(withId(R.id.ct_comics)).check(matches(isDisplayed()));
        onView(withId(R.id.tb_comics)).check(matches(isDisplayed()));
        onView(withId(R.id.iv_comics)).check(matches(isDisplayed()));
        onView(withId(R.id.tv_description)).check(matches(not(isDisplayed())));
        onView(withId(R.id.tv_price)).check(matches(not(isDisplayed())));
        onView(withId(R.id.tv_pages)).check(matches(not(isDisplayed())));
    }
}
