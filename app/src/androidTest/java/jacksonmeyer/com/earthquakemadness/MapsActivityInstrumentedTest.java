package jacksonmeyer.com.earthquakemadness;

import android.content.Intent;
import android.support.test.filters.SmallTest;
import android.test.ActivityInstrumentationTestCase2;

/**
 * Created by jacksonmeyer on 5/8/17.
 */
public class MapsActivityInstrumentedTest extends ActivityInstrumentationTestCase2<MapsActivity> {
    public MapsActivityInstrumentedTest() {
        super(MapsActivity.class);
    }

    //just testing intent extras are sent, and activity starts
    @Override
    public void setUp() throws Exception {
        super.setUp();
        Intent i = new Intent();
        i.putExtra("lat", "45.45");
        i.putExtra("lng", "45.45");
        setActivityIntent(i);
    }

    @SmallTest
    public void testActivityIntents() {
        // checks that the activity will even start and grab intents
        getActivity();
    }


}


