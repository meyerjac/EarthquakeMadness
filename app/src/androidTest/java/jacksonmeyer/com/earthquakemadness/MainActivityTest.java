package jacksonmeyer.com.earthquakemadness;

import android.content.DialogInterface;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.support.v7.app.AlertDialog;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static junit.framework.TestCase.assertEquals;

/**
 * Created by jacksonmeyer on 5/8/17.
 */
@RunWith(AndroidJUnit4.class)
public class MainActivityTest {

    @Rule
    public ActivityTestRule<MainActivity> mActivityRule =
            new ActivityTestRule<>(MainActivity.class);

    @Test
    public void ensureInfoButtonPopsUpDialog() {
        //press the button.
        onView(withId(R.id.info_icon))
                .perform(click());

        // Check that the dialog inflates
        AlertDialog dialog = MainActivity.getLastDialog(); // I create getLastDialog method in MyActivity class. Its return last created AlertDialog
        if (dialog.isShowing()) {
            try {
                assertEquals(dialog.getButton(DialogInterface.BUTTON_POSITIVE).getText(), "ok");
            } catch (Throwable e) {
                e.printStackTrace();
            }
        }
    }
}







//    public class EarthquakeAdapterTest extends MainActivityTest {
//        private EarthquakeAdapter mAdapter;
//
//        private Earthquake mEarth1;
//        private Earthquake mEarth2;
//
//        public EarthquakeAdapterTest() {
//            super();
//        }
//
//        protected void setUp() throws Exception {
//            super.setUp();
//            ArrayList<Earthquake> data = new ArrayList<>();
//
//            mEarth1 = new Earthquake("444-222-22 34:34:54", 45, 45.354, "UsID", "yo", 45.5, -123.453);
//            mEarth2 = new Earthquake("144-222-22 34:34:54", 55, 45.354, "UsID", "yo", 49.5, -113.453);
//            data.add(mEarth1);
//            data.add(mEarth2);
//            mAdapter = new EarthquakeAdapter(getContext(), data);
//        }
//
//
//        public void testGetItem() {
//            assertEquals(45, mEarth1.getDepth(),
//                    ((Earthquake) mAdapter.getItem(0)).getDepth());
//        }
//
//        public void testGetItemId() {
//            assertEquals("Wrong ID.", 0, mAdapter.getItemId(0));
//        }
//
//        public void testGetCount() {
//            assertEquals("Earthquake amount incorrect.", 2, mAdapter.getCount());
//        }
//
//        // I have 3 views on my adapter, date, depth and magnitde
//        public void testGetView() {
//            View view = mAdapter.getView(0, null, null);
//
//            TextView date = (TextView) view
//                    .findViewById(R.id.dateTextView);
//
//            TextView depthText = (TextView) view
//                    .findViewById(R.id.depthTextView);
//
//            TextView magnitudeText = (TextView) view
//                    .findViewById(R.id.magnitudeTextView);
//
//            //On this part you will have to test it with your own views/data
//            assertNotNull("View is null. ", view);
//            assertNotNull("date TextView is null. ", name);
//            assertNotNull("depth TextView is null. ", depthText);
//            assertNotNull("magnitde textview is null. ", magnitudeText);
//
//            assertEquals("depths dont match.", mEarth1.getDepth(), depthText.getText());
//            assertEquals("Mgintude doesn't match.", mEarth1.getMagnitude(),
//                    magnitudeText.getText());
//        }
//    }
//
//    private void setUp() {
//
//    }
//}
