package jacksonmeyer.com.earthquakemadness;

import android.content.DialogInterface;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.support.v7.app.AlertDialog;
import android.util.Log;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;

import static android.support.test.InstrumentationRegistry.getContext;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static junit.framework.Assert.assertEquals;

/**
 * Created by jacksonmeyer on 5/8/17.
 */
@RunWith(AndroidJUnit4.class)
public class MainActivityTest {
    //every Test in this testClass has a rule that sets the activity to MainActivity
    @Rule
    public ActivityTestRule<MainActivity> mActivityRule =
            new ActivityTestRule<>(MainActivity.class);

    //tests infoIcon onClick
    @Test
    public void ensureInfoButtonPopsUpDialog() {
        //press the button.
        onView(withId(R.id.info_icon))
                .perform(click());

        // Check that the dialog inflates upon clicking of the info icon, and is the right dialog, then clicks "ok"
        AlertDialog dialog = MainActivity.getLastDialog();
        if (dialog.isShowing()) {
            try {
                assertEquals(dialog.getButton(DialogInterface.BUTTON_POSITIVE).getText().toString(),
                        "ok");
                dialog.getButton(DialogInterface.BUTTON_POSITIVE).performClick();
            } catch (Throwable e) {
                e.printStackTrace();
            }
        }
    }

    @Test
    public void onCreateOptionsMenu() throws Exception {
        onView(withId(R.id.info_icon));
    }

    //This adapter Test...isnt working yet
    @Test
    public void makeSureAdapterWorks() {
        class EarthquakeAdapterTest extends MainActivityTest {
            private EarthquakeAdapter mAdapter;

            private Earthquake mEarth1;
            private Earthquake mEarth2;

            public EarthquakeAdapterTest() {
                super();
            }

            public void setUp() throws Exception {
                super.setUp();
                ArrayList<Earthquake> data = new ArrayList<>();

                mEarth1 = new Earthquake("444-222-22 34:34:54", 45, 45.354, "UsID", "yo", 45.5, -123.453);
                mEarth2 = new Earthquake("144-222-22 34:34:54", 55, 45.354, "UsID", "yo", 49.5, -113.453);
                data.add(mEarth1);
                data.add(mEarth2);
                mAdapter = new EarthquakeAdapter(getContext(), data);
            }


            public void testGetItem() {
                assertEquals(45, mEarth1.getDepth(),
                        ((Earthquake) mAdapter.getItem(0)).getDepth());
                Log.d("testGetItem: ", "in here");
            }

            public void testGetItemId() {
                assertEquals("Wrong ID.", 0, mAdapter.getItemId(0));
            }

            public void testGetCount() {
                assertEquals("Earthquake amount incorrect.", 2, mAdapter.getCount());
            }

            // I have 3 views on my adapter listItem: date, depth and magnitude
            public void testGetView() {

            }
        }
    }

    private void setUp() {
    }
}






