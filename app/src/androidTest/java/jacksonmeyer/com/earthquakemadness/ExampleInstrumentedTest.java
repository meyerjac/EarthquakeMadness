package jacksonmeyer.com.earthquakemadness;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.view.View;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.RootMatchers.withDecorView;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.CoreMatchers.anything;
import static org.hamcrest.core.IsNot.not;
import static org.junit.Assert.assertEquals;

/**
 * Instrumentation test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    @Test
    public void useAppContext() throws Exception {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();

        assertEquals("jacksonmeyer.com.earthquakemadness", appContext.getPackageName());
    }

    public class MainActivityInstrumentationTest {

        @Rule
        public ActivityTestRule<MainActivity> activityTestRule =
                new ActivityTestRule<>(MainActivity.class);

        @Test
        public void listItemClickhasCorrectEarthquake() {
            View activityDecorView = activityTestRule.getActivity().getWindow().getDecorView();
            Integer depth = 24;
            onData(anything())
                    .inAdapterView(withId(R.id.earthquakeListView))

                    .atPosition(0)
                    .perform(click());
            onView(withText(depth)).inRoot(withDecorView(not(activityDecorView)))
                    .check(matches(withText(depth)));
        }
    }
}
