package jacksonmeyer.com.earthquakemadness;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

import static jacksonmeyer.com.earthquakemadness.R.id.earthquakeListView;
import static jacksonmeyer.com.earthquakemadness.R.id.imageButton;

public class MainActivity extends ActionBarActivity implements View.OnClickListener {
    private static AlertDialog lastDialog;
    @Bind(earthquakeListView)
    ListView EarthquakeListView;
    @Bind(imageButton)
    ImageButton ReloadButton;

    public ArrayList<Earthquake> earthquakeResults = new ArrayList<>();
    private EarthquakeAdapter mAdapter;
    private ProgressDialog EarthquakeDialog;
    //had to create static dialog for testing purposes
    public static AlertDialog getLastDialog() {
        return lastDialog;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        ReloadButton.setOnClickListener(this);

        /*onStart check what the state is of the device and if it will be able to fetch API endpoint
        if avaliable it calls API and populated ListView, otherwise it shows a toast and displays a 'retry' button
         */

        if (internetIsAvailable()) {
            ReloadButton.setVisibility(View.INVISIBLE);
            createEarthquakeDialog();
            createAndShowDialogBox();
            getEarthquakeData();
        } else {
            EarthquakeListView.setVisibility(View.INVISIBLE);
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
              showDialogForm();
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    //lets user know we are getting data for them
    public void createAndShowDialogBox() {
        createEarthquakeDialog();
        EarthquakeDialog.show();
    }
    //method checking connected state
    public boolean internetIsAvailable() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        return cm.getActiveNetworkInfo() != null;
    }
    //dialog creation BABY
    private void createEarthquakeDialog() {
        EarthquakeDialog = new ProgressDialog(this, R.style.dialog_box);
        EarthquakeDialog.setTitle(R.string.finding_earthquakes);
        EarthquakeDialog.setMessage(this.getString(R.string.groundbreaking_work));
        EarthquakeDialog.setCancelable(false);
    }

    /*The meat of the app, this creates an instance of my Earthquake service and
     returns nicely parsed data as objects into arrayList
     */

    private void getEarthquakeData() {
        final EarthquakeService earthquakeService = new EarthquakeService();
        earthquakeService.getEarthquakeData(new Callback() {

            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
                delayEarthquakeDialog();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                earthquakeResults = EarthquakeService.processResults(response);
                mAdapter = new EarthquakeAdapter(getApplicationContext(), earthquakeResults);

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        EarthquakeListView.setAdapter(mAdapter);
                        delayEarthquakeDialog();

                        EarthquakeListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                                String lat = String.valueOf(earthquakeResults.get(i).getLat());
                                String lng = String.valueOf(earthquakeResults.get(i).getLng());
                                Intent intent = new Intent(MainActivity.this, MapsActivity.class);
                                intent.putExtra("lat", lat);
                                intent.putExtra("lng", lng);
                                startActivity(intent);
                            }
                        });
                    }
                });
            }
        });
    }

    private void delayEarthquakeDialog() {
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                EarthquakeDialog.dismiss();
            }
        }, 2000);
    }

    private void showDialogForm() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(this, R.style.dialog_box);
        LayoutInflater inflater = this.getLayoutInflater();
        builder.setView(inflater.inflate(R.layout.info_fragment, null));
        // Add action buttons
        builder.setPositiveButton("ok", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
            }
        });
        builder.setIcon(android.R.drawable.ic_dialog_alert);
        AlertDialog dialog = builder.create();
        lastDialog = dialog;
        dialog.show();
    }

    //only click happens will only be visible if there is no internet
    @Override
    public void onClick(View view) {
        if (view == ReloadButton) {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }
    }
}

