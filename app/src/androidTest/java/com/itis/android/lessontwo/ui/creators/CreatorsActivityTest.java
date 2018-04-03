package com.itis.android.lessontwo.ui.creators;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.core.IsNot.not;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import com.itis.android.lessontwo.R;
import org.junit.*;
import org.junit.runner.*;

@RunWith(AndroidJUnit4.class)
public class CreatorsActivityTest {

    @Rule
    public final ActivityTestRule<CreatorsActivity> rule =
            new ActivityTestRule<>(CreatorsActivity.class, false);

    private CreatorsActivity creatorsActivity;

    @Before
    public void setUp() throws Exception {
        creatorsActivity = rule.getActivity();
    }

    @Test
    public void displayTextViews() {
        onView(withId(R.id.ct_comics)).check(matches(isDisplayed()));
        onView(withId(R.id.tb_comics)).check(matches(isDisplayed()));
        onView(withId(R.id.iv_comics)).check(matches(isDisplayed()));
        onView(withId(R.id.tv_name)).check(matches(not((isDisplayed()))));
        onView(withId(R.id.tv_stories)).check(matches(not(isDisplayed())));
    }
}
