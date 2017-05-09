package jacksonmeyer.com.earthquakemadness;

import android.content.Intent;
import android.support.test.filters.SmallTest;
import android.test.ActivityInstrumentationTestCase2;

/**
 * Created by jacksonmeyer on 5/8/17.
 */
public class MapsActivityTest extends ActivityInstrumentationTestCase2<MapsActivity> {
    public MapsActivityTest() {
        super(MapsActivity.class);
    }


    @Override
    public void setUp() throws Exception {
        super.setUp();
        Intent i = new Intent();
        i.putExtra("lat", "45.45");
        i.putExtra("lng", "45.45");
        setActivityIntent(i);
        getActivity();
    }

    @SmallTest
    public void testActivityIntents() {
    }


    @Override
    public void tearDown() {
    }
}


