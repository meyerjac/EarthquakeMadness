package jacksonmeyer.com.earthquakemadness;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by jacksonmeyer on 5/6/17.
 */

public class EarthquakeAdapter extends ArrayAdapter<Earthquake> {

    //define adapter class
public EarthquakeAdapter(Context context, ArrayList<Earthquake> EarthShakes){
        super(context, 0, EarthShakes);
        }

@Override
public View getView(int position, View convertView, ViewGroup parent) {
    // Get the data for the item in this position
    Earthquake earthquake = getItem(position);
    // Check if an existing view is being reused, otherwise inflate the view...this improves performance baby!
    if (convertView == null) {
        convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent, false);
    }



    // define views in list_item.xml to fill with data
    TextView DateTextView = (TextView) convertView.findViewById(R.id.dateTextView);
    TextView MagnitudeTextView = (TextView) convertView.findViewById(R.id.magnitudeTextView);
    TextView DepthTextView = (TextView) convertView.findViewById(R.id.depthTextView);
    RelativeLayout RelativeLayoutView = (RelativeLayout) convertView.findViewById(R.id.relativeLayout);
    RelativeLayoutView.setTag(position);

    // Populate the data into the template view using the data object
    Double magnitude = earthquake.getMagnitude();
    Integer depth = earthquake.getDepth();
    String date = earthquake.getDatetime();

    DepthTextView.setText("depth: " + earthquake.getDepth() + " km.");
    SimpleDateFormat input = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    SimpleDateFormat output = new SimpleDateFormat("dd MMM, yyyy");
    try {
        Date formattedTripDate = input.parse(date);                 // parse input
        DateTextView.setText(output.format(formattedTripDate));// format output
    } catch (ParseException e) {
        e.printStackTrace();
    }


    if (magnitude >= 6) {

        MagnitudeTextView.setText(String.valueOf(magnitude) + "!");
    } else {
        MagnitudeTextView.setText(String.valueOf(magnitude));
    }

    if (depth >= 75) {
        RelativeLayoutView.setBackgroundResource(R.color.colorPrimaryDark);
    } else if (depth >= 20 && depth < 75){
        RelativeLayoutView.setBackgroundResource(R.color.colorPrimary);
    } else {
        RelativeLayoutView.setBackgroundResource(R.color.colorAccent);
    }
    // Return the completed view to render on screen
        return convertView;
    }
}

