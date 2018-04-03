package com.itis.android.lessontwo.ui.creatorslist;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.core.IsNot.not;

import android.content.Context;
import android.content.Intent;
import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import com.itis.android.lessontwo.R;
import org.junit.*;
import org.junit.runner.*;

@RunWith(AndroidJUnit4.class)
public class CreatorListEmptyActivity {

    @Rule
    public final ActivityTestRule<CreatorListActivity> rule
            = new ActivityTestRule<>(CreatorListActivity.class, false, true);

    @Test
    public void testErrorDisplayed() throws Exception {
        // need generate error, i haven't fantasy

        //launchActivity();

        onView(withId(R.id.tv_creator_empty)).check(matches(isDisplayed()));
        onView(withId(R.id.rv_creator_list)).check(matches(not(isDisplayed())));

        onView(withId(R.id.tv_creator_empty)).check(matches(withText(R.string.text_empty_creators)));
    }

    private void launchActivity() {
        Context context = InstrumentationRegistry.getContext();
        Intent intent = new Intent(context, CreatorListActivity.class);
        rule.launchActivity(intent);
    }
}
