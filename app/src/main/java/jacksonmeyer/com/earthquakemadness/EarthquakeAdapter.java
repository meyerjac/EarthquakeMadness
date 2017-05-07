package jacksonmeyer.com.earthquakemadness;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by jacksonmeyer on 5/6/17.
 */

public class EarthquakeAdapter extends ArrayAdapter<Earthquake> {
public EarthquakeAdapter(Context context, ArrayList<Earthquake> EarthShakes) {
        super(context, 0, EarthShakes);
        }

@Override
public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        Earthquake earthquake = getItem(position);

        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
        convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent, false);
        }
        // Lookup view for data population
        TextView MagnitudeTextView = (TextView) convertView.findViewById(R.id.magnitudeTextView);
        TextView DateTextView = (TextView) convertView.findViewById(R.id.dateTextView);
        // Populate the data into the template view using the data object
        MagnitudeTextView.setText("yo");
        DateTextView.setText("YO");
        // Return the completed view to render on screen
        return convertView;
        }
}

