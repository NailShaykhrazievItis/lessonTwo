package com.itis.android.lessontwo.ui.characterlist;

import android.content.Context;
import android.content.Intent;
import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.itis.android.lessontwo.R;
import com.itis.android.lessontwo.ui.characterslist.CharactersListActivity;

import org.junit.*;
import org.junit.runner.*;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

import static org.hamcrest.core.IsNot.not;


@RunWith(AndroidJUnit4.class)
public class CharacterListEmptyActivity {

    @Rule
    public final ActivityTestRule<CharactersListActivity> rule
            = new ActivityTestRule<>(CharactersListActivity.class,false,true);

    @Test
    public void testErrorDisplayed() throws Exception{
        //launchActivity();

        onView(withId(R.id.tv_empty_characters)).check(matches(isDisplayed()));
        onView(withId(R.id.rv_characters_list)).check(matches(not(isDisplayed())));
        onView(withId(R.id.tv_empty_characters)).check(matches(withText(R.string.there_are_no_characters_yet)));
    }

    private void launchActivity() {
        Context context = InstrumentationRegistry.getContext();
        Intent intent = new Intent(context,CharactersListActivity.class);
        rule.launchActivity(intent);
    }
}