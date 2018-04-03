package com.itis.android.lessontwo.ui.characterlist;

import android.content.Context;
import android.content.Intent;
import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.itis.android.lessontwo.R;
import com.itis.android.lessontwo.ui.comicslist.ComicsListActivity;

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
 * Created by Nail Shaykhraziev on 26.03.2018.
 */
@RunWith(AndroidJUnit4.class)
public class CharacterListEmptyActivity {

    @Rule
    public final ActivityTestRule<CharacterListActivity> rule
            = new ActivityTestRule<>(CharacterListActivity.class, false, true);

    @Test
    public void testErrorDisplayed() throws Exception {
        // need generate error, i haven't fantasy

//        launchActivity();

        onView(withId(R.id.tv_character_empty)).check(matches(not(isDisplayed())));
        onView(withId(R.id.rv_character_list)).check(matches(isDisplayed()));

    }

    private void launchActivity() {
        Context context = InstrumentationRegistry.getContext();
        Intent intent = new Intent(context, CharacterListActivity.class);
        rule.launchActivity(intent);
    }
}
