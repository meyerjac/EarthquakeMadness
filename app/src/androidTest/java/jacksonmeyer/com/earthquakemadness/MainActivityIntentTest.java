package jacksonmeyer.com.earthquakemadness;

import android.support.test.espresso.Espresso;
import android.support.test.espresso.intent.Intents;
import android.support.test.espresso.intent.rule.IntentsTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.view.View;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.intent.Intents.intended;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.CoreMatchers.anything;

/**
 * Created by jacksonmeyer on 5/9/17.
 */

@RunWith(AndroidJUnit4.class)
public class MainActivityIntentTest {

    @Rule
    public IntentsTestRule<MainActivity> mActivityIntentRule =
            new IntentsTestRule<MainActivity>(MainActivity.class);

    @Test
    public void allListItemsOpenMapActivity() {
        View activityDecorView = mActivityIntentRule.getActivity().getWindow().getDecorView();
        Espresso.onData(anything())
                .inAdapterView(withId(R.id.earthquakeListView))
                .atPosition(0)
                .perform(click());

        intended(hasComponent(MapsActivity.class.getName()));
    }

    public void setUp() throws Exception {
        Intents.init();
    }

    public void tearDown() {
        Intents.release();
    }
}
