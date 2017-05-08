package jacksonmeyer.com.earthquakemadness;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

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
    if (convertView == null) {convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent, false);
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
    DateTextView.setText(earthquake.getDatetime());
    DepthTextView.setText("depth: " + earthquake.getDepth() + " km.");
    Double lat = earthquake.getLat();
    Double lng = earthquake.getLng();


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

    // Attach the click event listener
    RelativeLayoutView.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            // Access the row position here to get the correct data item
            int position = (Integer) view.getTag();
            Earthquake earthquake = getItem(position);
            Toast.makeText(getContext(), earthquake.getDatetime(), Toast.LENGTH_SHORT).show();
        }
    });

    // Return the completed view to render on screen
        return convertView;
    }
}

