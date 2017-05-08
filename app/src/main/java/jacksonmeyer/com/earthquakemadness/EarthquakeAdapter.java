package jacksonmeyer.com.earthquakemadness;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import jacksonmeyer.com.earthquakemadness.models.Earthquake;

/**
 * Created by jacksonmeyer on 5/6/17.
 */

public class EarthquakeAdapter extends ArrayAdapter<Earthquake> {
public EarthquakeAdapter(Context context, ArrayList<Earthquake> EarthShakes) {
        super(context, 0, EarthShakes);
        }

@Override
public View getView(int position, View convertView, ViewGroup parent) {
    // Get the data for the item in this position
    Earthquake earthquake = getItem(position);

    // Check if an existing view is being reused, otherwise inflate the view
    if (convertView == null) {convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent, false);
    }

    // bind views in list_item to fill with data
    TextView DateTextView = (TextView) convertView.findViewById(R.id.dateTextView);
    TextView MagnitudeTextView = (TextView) convertView.findViewById(R.id.magnitudeTextView);
    TextView DepthTextView = (TextView) convertView.findViewById(R.id.depthTextView);

    // Populate the data into the template view using the data object
    DateTextView.setText(earthquake.getDatetime());
    Double magnitude = earthquake.getMagnitude();


    /*visually differentiating betweent the different volcanos, if it on the richter scale greater than 6.0
    it will make the background a more pronouced background */


//    if (magnitude >= 6.0) {
//        convertView.setBackgroundResource(R.color.colorAccent);
//    } else {
//        convertView.setBackgroundResource(R.color.colorPrimary);
//    }

    MagnitudeTextView.setText(String.valueOf(magnitude));
    Integer depth = earthquake.getDepth();
    String dep = depth.toString();

    //placeholder in Resources not working
    DepthTextView.setText("depth: " + earthquake.getDepth() + "km.");
    Double lat = earthquake.getLat();
    Double lng = earthquake.getLng();

    // Return the completed view to render on screen
        return convertView;
        }
}

