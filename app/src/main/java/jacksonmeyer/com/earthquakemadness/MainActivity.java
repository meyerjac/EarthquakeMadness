package jacksonmeyer.com.earthquakemadness;

import android.app.Dialog;
import android.app.DialogFragment;
import android.app.FragmentTransaction;
import android.app.ProgressDialog;
import android.content.Context;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
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

public class MainActivity extends ActionBarActivity {
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
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.info_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.info_icon:
              showInfoDialogFragment();
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void delayFetchingApiDataDialog() {
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
                delayFetchingApiDataDialog();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                earthquakeResults = EarthquakeService.processResults(response);
                mAdapter = new EarthquakeAdapter(getApplicationContext(), earthquakeResults);

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        EarthquakeListView.setAdapter(mAdapter);
                        delayFetchingApiDataDialog();

                        EarthquakeListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                                String restaurant = ((TextView)view).getText().toString();
                                Toast.makeText(MainActivity.this, restaurant, Toast.LENGTH_LONG).show();
                            }
                        });
                    }
                });
            }
        });
    }


    void showInfoDialogFragment() {
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        MyDialogFragment frag = new MyDialogFragment();
        frag.show(ft, "txn_tag");
    }

    static public class MyDialogFragment extends DialogFragment {

        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setStyle(DialogFragment.STYLE_NORMAL, R.style.MY_DIALOG);
        }

        @Override
        public void onStart() {
            super.onStart();
            Dialog d = getDialog();
            if (d!=null){
                int width = ViewGroup.LayoutParams.MATCH_PARENT;
                int height = ViewGroup.LayoutParams.MATCH_PARENT;
                d.getWindow().setLayout(width, height);
            }
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            View root = inflater.inflate(R.layout.my_fragment, container, false);
            return root;
        }

    }

}

