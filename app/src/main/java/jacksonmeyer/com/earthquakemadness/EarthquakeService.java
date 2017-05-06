package jacksonmeyer.com.earthquakemadness;

import android.util.Log;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;

/**
 * Created by jacksonmeyer on 5/6/17.
 */

public class EarthquakeService {
    public static void getEarthquakeData(Callback callback) {
        OkHttpClient client = new OkHttpClient.Builder().build();

        Request request = new Request.Builder()
                .url(Constants.API_EARTHQUAKE_URL)
                .get()
                .build();
        Log.d("yo", "getEarthquakeData: " + request);

        Call call = client.newCall(request);
        call.enqueue(callback);
    }
}