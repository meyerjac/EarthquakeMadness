package jacksonmeyer.com.earthquakemadness;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

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

        Call call = client.newCall(request);
        call.enqueue(callback);
    }

    /*when processResults is called from the main activity, it filters and assembles them into object
    and puts them in an Earthquake Array
     */

    public static ArrayList<Earthquake> processResults(Response response) throws IOException {
        ArrayList<Earthquake> Results = new ArrayList<>();

        try {
            if (response.isSuccessful()) {
                String jsonData = response.body().string();
                JSONObject results = new JSONObject(jsonData);
                JSONArray earthquakes = results.getJSONArray("earthquakes");
                for (int i = 0; i < earthquakes.length(); i++) {
                    JSONObject item = earthquakes.getJSONObject(i);
                    String datetime = item.getString("datetime");
                    Integer depth = item.getInt("depth");
                    Double lng = item.getDouble("lng");
                    String src = item.getString("src");
                    String eqid = item.getString("eqid");
                    Double magnitude = item.getDouble("magnitude");
                    Double lat = item.getDouble("lat");

                    Earthquake earthquakeObject = new Earthquake(datetime, depth, lng, src, eqid, magnitude, lat);
                    Results.add(earthquakeObject);
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return Results;
    }
}