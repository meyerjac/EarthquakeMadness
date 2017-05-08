package jacksonmeyer.com.earthquakemadness;

import android.os.Build;
import android.widget.ListView;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;

import static junit.framework.Assert.assertNotNull;

@Config(constants = BuildConfig.class, sdk = Build.VERSION_CODES.LOLLIPOP)
@RunWith(RobolectricGradleTestRunner.class)

public class ExampleUnitTest {
    private MainActivity activity;
    private ListView EarthquakeListView;

    @Before
    public void setup() {
        activity = Robolectric.setupActivity(MainActivity.class);
        EarthquakeListView = (ListView) activity.findViewById(R.id.earthquakeListView);


    }

    @Test
    public void restaurantListViewPopulates() {
        assertNotNull(EarthquakeListView.getAdapter());

    }
}

