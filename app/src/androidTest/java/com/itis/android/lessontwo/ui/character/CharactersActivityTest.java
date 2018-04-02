package com.itis.android.lessontwo.ui.character;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.itis.android.lessontwo.R;
import com.itis.android.lessontwo.ui.characters.CharactersActivity;

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
 * Created by Ruslan on 31.03.2018.
 */

@RunWith(AndroidJUnit4.class)
public class CharactersActivityTest {

    @Rule
    public final ActivityTestRule<CharactersActivity> rule =
            new ActivityTestRule<>(CharactersActivity.class, false);

    private CharactersActivity charactersActivity;

    @Before
    public void setUp() throws Exception {
        charactersActivity = rule.getActivity();
    }

    @Test
    public void displayTextViews() {
        onView(withId(R.id.ct_character)).check(matches(isDisplayed()));
        onView(withId(R.id.tb_character)).check(matches(isDisplayed()));
        onView(withId(R.id.iv_character)).check(matches(isDisplayed()));
        onView(withId(R.id.tv_description)).check(matches(not((isDisplayed()))));
        onView(withId(R.id.tv_name)).check(matches(not(isDisplayed())));
    }
}
