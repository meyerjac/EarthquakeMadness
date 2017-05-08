package jacksonmeyer.com.earthquakemadness;

import android.app.ProgressDialog;
import android.content.Context;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import jacksonmeyer.com.earthquakemadness.models.Earthquake;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

import static jacksonmeyer.com.earthquakemadness.R.id.earthquakeListView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    public static final String TAG = MainActivity.class.getSimpleName();

    public ArrayList<Earthquake> earthquakeResults = new ArrayList<Earthquake>();
    private EarthquakeAdapter mAdapter;
    private ProgressDialog EarthquakeDialog;

    @Bind(earthquakeListView)
    ListView EarthquakeListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        createEarthquakeProgressDialog();
        showDialogBox();

        if (internetIsAvailable()) {
            getEarthquakeData();
        } else {
            Toast.makeText(this, R.string.no_internet, Toast.LENGTH_SHORT).show();
            delaydialog();
        }
    }

    private void delaydialog() {
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                EarthquakeDialog.dismiss();
            }
        }, 2000);
    }

    public void showDialogBox() {
        createEarthquakeProgressDialog();
        EarthquakeDialog.show();
    }

    private boolean internetIsAvailable() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        return cm.getActiveNetworkInfo() != null;
    }

    private void createEarthquakeProgressDialog() {
        EarthquakeDialog = new ProgressDialog(this, R.style.dialog_box);
        EarthquakeDialog.setTitle("Finding earthquakes");
        EarthquakeDialog.setMessage("this is groundbreaking work...");
        EarthquakeDialog.setCancelable(false);
    }

    private void getEarthquakeData() {
        final EarthquakeService earthquakeService = new EarthquakeService();
        earthquakeService.getEarthquakeData(new Callback() {

            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
                delaydialog();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                earthquakeResults = EarthquakeService.processResults(response);
                mAdapter = new EarthquakeAdapter(getApplicationContext(), earthquakeResults);

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        EarthquakeListView.setAdapter(mAdapter);
                        delaydialog();
                    }
                });
            }
        });
    }

    @Override
    public void onClick(View view) {
    }
}