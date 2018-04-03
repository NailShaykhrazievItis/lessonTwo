package com.itis.android.lessontwo.ui.creator;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.itis.android.lessontwo.R;
import com.itis.android.lessontwo.ui.characters.CharactersActivity;
import com.itis.android.lessontwo.ui.creators.CreatorsActivity;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.core.IsNot.not;

/**
 * Created by valera071998@gmail.com on 31.03.2018.
 */

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
        onView(withId(R.id.ct_creator)).check(matches(isDisplayed()));
        onView(withId(R.id.tb_creator)).check(matches(isDisplayed()));
        onView(withId(R.id.iv_creator)).check(matches(isDisplayed()));
        onView(withId(R.id.tv_description)).check(matches(not((isDisplayed()))));
        onView(withId(R.id.tv_name)).check(matches(not(isDisplayed())));
    }
}