package com.itis.android.lessontwo.ui.creators;

import android.support.test.rule.ActivityTestRule;

import com.itis.android.lessontwo.R;
import com.itis.android.lessontwo.ui.characters.CharacterActivity;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.core.IsNot.not;

public class CreatorsActivityTest {
    @Rule
    public final ActivityTestRule<CreatorsActivity> rule =
            new ActivityTestRule<>(CreatorsActivity.class,false);

    private CreatorsActivity creatorsActivity;

    @Before
    public void setUp() throws Exception{
        creatorsActivity = rule.getActivity();
    }

    @Test
    public void displayTextViews(){
        onView(withId(R.id.ct_creators)).check(matches(isDisplayed()));
        onView(withId(R.id.tb_creators)).check(matches(isDisplayed()));
        onView(withId(R.id.iv_creators)).check(matches(isDisplayed()));
        onView(withId(R.id.tv_name)).check(matches(not(isDisplayed())));
    }
}
