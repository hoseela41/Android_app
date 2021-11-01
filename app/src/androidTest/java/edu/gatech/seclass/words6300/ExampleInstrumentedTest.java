package edu.gatech.seclass.words6300;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.provider.ContactsContract;
import android.view.View;

import androidx.test.espresso.Espresso;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.runner.AndroidJUnit4;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.clearText;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.hasErrorText;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

// Import android ActivityTestRule
import androidx.test.rule.ActivityTestRule;
///////////////////////////////////

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {


    //call the actual activity running the test
    @Rule
    public ActivityTestRule myActivityTestRule = new ActivityTestRule<>(game.class, true, false);
    private Activity mActivity = null;
    //public ActivityTestRule myActivityTestRule2 = new ActivityTestRule<>(game.class, true, false);
    @Before
    public void setUp() throws Exception{
        mActivity = myActivityTestRule.getActivity();
    }

    @After
    public void tearDown() throws Exception{
        mActivity = null;
    }
    @Test
    public void testLaunch() { // make sure the activity launches properly
        View view = mActivity.findViewById(R.id.inputWord);
        assertNotNull(view);
    }

    @Test
    public void useAppContext() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();

        assertEquals("edu.gatech.seclass.words6300", appContext.getPackageName());
    }

    @Test
    public void testBoardError1() { // test if the error display as selecting two letters from Board
        /// getActivity should be applied here
    //    Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        //@Rule
        DataHolder.getInstance().setGameStatus(false);
        Intent intent = new Intent();
        intent.putExtra("EXTRA", "Test");
        myActivityTestRule.launchActivity(intent);
        onView(withId(R.id.Board1)).perform(click());
        onView(withId(R.id.Board2)).perform(click());
        onView(withId(R.id.Enter)).perform(click());
        onView(withId(R.id.inputWord)).check(matches(hasErrorText("Only one letter from Board")));
        myActivityTestRule.finishActivity();
    }
    @Test
    public void testBoardError2() { // test if the error display as selecting no letters from Board
        /// getActivity should be applied here
        DataHolder.getInstance().setGameStatus(false);
        Intent intent = new Intent();
        intent.putExtra("EXTRA1", "Test2");
        myActivityTestRule.launchActivity(intent);
        onView(withId(R.id.Rack1)).perform(click());
        onView(withId(R.id.Rack2)).perform(click());
        onView(withId(R.id.Rack3)).perform(click());
        onView(withId(R.id.Rack4)).perform(click());
        onView(withId(R.id.Rack5)).perform(click());
        onView(withId(R.id.Enter)).perform(click());
        onView(withId(R.id.inputWord)).check(matches(hasErrorText("One letter from Board")));
    }
    @Test
    public void testBoardError3() { // test if the error display as selecting no letters from Board
        /// getActivity should be applied here
        DataHolder.getInstance().setGameStatus(false);
        Intent intent = new Intent();
        intent.putExtra("EXTRA1", "Test2");
        myActivityTestRule.launchActivity(intent);
        onView(withId(R.id.Board1)).perform(click());
        onView(withId(R.id.Enter)).perform(click());
        onView(withId(R.id.inputWord)).check(matches(hasErrorText("No letter from Rack")));
    }
}
