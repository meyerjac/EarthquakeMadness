package jacksonmeyer.com.earthquakemadness;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;

/**
 * Created by jacksonmeyer on 5/7/17.
 */

public class infoDialogBoxFragment extends DialogFragment {

    public static infoDialogBoxFragment newInstance(int title) {
        infoDialogBoxFragment frag = new infoDialogBoxFragment();
        Bundle args = new Bundle();
        args.putInt("title", title);
        frag.setArguments(args);
        return frag;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        int title = getArguments().getInt("title");

        return new AlertDialog.Builder(getActivity())
                .setIcon(R.drawable.volcano)
                .setTitle(title)
                .create();
    }
}

