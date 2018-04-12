package com.itis.android.lessontwo.ui.character;

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
public class CharacterActivityTest {

    @Rule
    public final ActivityTestRule<CharacterActivity> rule =
            new ActivityTestRule<>(CharacterActivity.class,false);

    private CharacterActivity characterActivity;

    @Before
    public void setUp() throws Exception{
        characterActivity = rule.getActivity();
    }

    @Test
    public void displayTextViews(){
        onView(withId(R.id.ct_character)).check(matches(isDisplayed()));
        onView(withId(R.id.tb_character)).check(matches(isDisplayed()));
        onView(withId(R.id.iv_character)).check(matches(isDisplayed()));
        onView(withId(R.id.tv_description_character)).check(matches(not(isDisplayed())));
    }
}
