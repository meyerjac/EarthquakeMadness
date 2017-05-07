package jacksonmeyer.com.earthquakemadness;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import java.io.IOException;
import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

import static jacksonmeyer.com.earthquakemadness.R.id.earthquakeListView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    public static final String TAG = MainActivity.class.getSimpleName();

    public ArrayList<Earthquake> earthquakeResults = new ArrayList<Earthquake>();
    private EarthquakeAdapter mAdapter;


    @Bind(R.id.getEarthquakeButton)
    Button GetEarth;
    @Bind(earthquakeListView)
    ListView EarthquakeListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        GetEarth.setOnClickListener(this);
    }



    private void getEarthquakeData() {
        final EarthquakeService earthquakeService = new EarthquakeService();
        earthquakeService.getEarthquakeData(new Callback() {

            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                earthquakeResults = EarthquakeService.processResults(response);
                mAdapter = new EarthquakeAdapter(getApplicationContext(), earthquakeResults);

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        EarthquakeListView.setAdapter(mAdapter);
                    }
                });
            }
        });
    }

    @Override
    public void onClick(View view) {
        if (view == GetEarth) {
            getEarthquakeData();
        }
    }
}