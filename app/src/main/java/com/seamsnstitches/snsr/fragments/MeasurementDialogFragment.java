package com.seamsnstitches.snsr.fragments;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.seamsnstitches.snsr.R;
import com.seamsnstitches.snsr.databinding.ItemShirtMeasurementBinding;
import com.seamsnstitches.snsr.databinding.ItemTrouserMesurementBinding;
import com.seamsnstitches.snsr.models.ShirtMeasurement;
import com.seamsnstitches.snsr.models.TrouserMeasurement;
import com.seamsnstitches.snsr.utility.AppConstants;

public class MeasurementDialogFragment extends DialogFragment {
    private ItemShirtMeasurementBinding shirtMeasurementBinding;
    private ItemTrouserMesurementBinding trouserMesurementBinding;

    public MeasurementDialogFragment() {
        super();
    }

    /**
     * This method helps in creating a new instance of the DialogFragment ad passes a bundle value to it
     *
     * @param args bundle
     * @return an instance of MeasurementDialogFragment
     */
    public static MeasurementDialogFragment newInstance(Bundle args) {
        MeasurementDialogFragment dialogFragment = new MeasurementDialogFragment();
        dialogFragment.setArguments(args);
        return dialogFragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = null;
        if (isShirtMeasurementFragment(getArguments())) {
            shirtMeasurementBinding = DataBindingUtil
                    .inflate(inflater, R.layout.item_shirt_measurement, container, false);
            view = shirtMeasurementBinding.getRoot();
            bindDataWithShirtMeasurementViews(getArguments());
        } else {
            trouserMesurementBinding = DataBindingUtil
                    .inflate(inflater, R.layout.item_trouser_mesurement, container, false);
            view = trouserMesurementBinding.getRoot();
            bindDataWithTrouserMeasurementViews(getArguments());
        }
        return view;
    }

    /**
     * Checks to which type of fragment is required if its to display shirt or trouser measurements
     *
     * @param args bundle
     * @return true if its shirt measurement hat is required
     */
    private boolean isShirtMeasurementFragment(Bundle args) {
        if (args != null && args.containsKey(AppConstants.MEASUREMENT_DIALOG_FRAGMENT)) {
            switch (args.getInt(AppConstants.MEASUREMENT_DIALOG_FRAGMENT)) {
                case AppConstants.SHIRT_MEASUREMENT_FRAG:
                    return true;
                case AppConstants.TROOUSER_MEASUREMENT_FRAG:
                    return false;
                default:
                    return false;
            }
        }
        throw new IllegalArgumentException();
    }

    /**
     * If ShirtMeasurement Fragment is requested, the method help binds the data with the views
     *
     * @param args bundle
     */
    private void bindDataWithShirtMeasurementViews(Bundle args) {
        if (getShirtMeasurement(args) == null) return;
        ShirtMeasurement shirtMeasurement = getShirtMeasurement(args);
        appendToTextView(shirtMeasurementBinding.shirtMeasurementChest,
                String.valueOf(shirtMeasurement.getChest()));
        appendToTextView(shirtMeasurementBinding.shirtMeasurementCollar,
                String.valueOf(shirtMeasurement.getCollar()));
        appendToTextView(shirtMeasurementBinding.shirtMeasurementNeck,
                String.valueOf(shirtMeasurement.getNeck()));
        appendToTextView(shirtMeasurementBinding.shirtMeasurementRoundWrist,
                String.valueOf(shirtMeasurement.getRoundWrist()));
        appendToTextView(shirtMeasurementBinding.shirtMeasurementShortSleeveLength,
                String.valueOf(shirtMeasurement.getShortSleeveLength()));
        appendToTextView(shirtMeasurementBinding.shirtMeasurementShoulder,
                String.valueOf(shirtMeasurement.getShoulder()));
        appendToTextView(shirtMeasurementBinding.shirtMeasurementSleeveLength,
                String.valueOf(shirtMeasurement.getSleeveLength()));
        appendToTextView(shirtMeasurementBinding.shirtMeasurementTopLength,
                String.valueOf(shirtMeasurement.getTopLength()));
    }

    /**
     * If TrouserMeasurement Fragment is requested, the method help binds the data with the views
     *
     * @param args bundle
     */
    private void bindDataWithTrouserMeasurementViews(Bundle args) {
        if (getTrouserMeasurement(args) == null) return;
        TrouserMeasurement trouserMeasurement = getTrouserMeasurement(args);
        appendToTextView(trouserMesurementBinding.fragmentTrouserMeasurementHip,
                String.valueOf(trouserMeasurement.getHip()));
        appendToTextView(trouserMesurementBinding.trouserMeasurementAnkle,
                String.valueOf(trouserMeasurement.getAnkle()));
        appendToTextView(trouserMesurementBinding.trouserMeasurementFullLength,
                String.valueOf(trouserMeasurement.getFullLength()));
        appendToTextView(trouserMesurementBinding.trouserMeasurementShortsLength,
                String.valueOf(trouserMeasurement.getShortsLength()));
        appendToTextView(trouserMesurementBinding.trouserMeasurementThigh,
                String.valueOf(trouserMeasurement.getThigh()));
        appendToTextView(trouserMesurementBinding.trouserMeasurementWaist,
                String.valueOf(trouserMeasurement.getWaist()));
        appendToTextView(trouserMesurementBinding.trouserMeasurementKnee,
                String.valueOf(trouserMeasurement.getKnee()));
    }

    /**
     * checks if ShirtMeasurement exist in the Bundle and returns it
     *
     * @param args bundle
     * @return instance of ShirtMeasurement if it exists in bundle else returns null
     */
    private ShirtMeasurement getShirtMeasurement(Bundle args) {
        if (args != null && args.containsKey(AppConstants.SHIRT_MEASUREMENT_TAG)) {
            return args.getParcelable(AppConstants.SHIRT_MEASUREMENT_TAG);
        }
        return null;
    }

    /**
     * checks if TrouserMeasurement exist in the Bundle and returns it
     *
     * @param args bundle
     * @return instance of TrouserMeasurement if it exists in bundle else returns null
     */
    private TrouserMeasurement getTrouserMeasurement(Bundle args) {
        if (args != null && args.containsKey(AppConstants.TROUSER_MEASUREMENT_TAG)) {
            return args.getParcelable(AppConstants.TROUSER_MEASUREMENT_TAG);
        }
        return null;
    }

    /**
     * This method helps setting textView text, if the  string passed to the method is null or empty
     * it sets the text to 3 dots, to indicate missing values
     *
     * @param textView the textView to set the text
     * @param string   the string value to set textView to
     */
    private void appendToTextView(TextView textView, String string) {
        if (string == null || string.isEmpty()) {
            textView.append("...");
        } else textView.append(string);
    }
}
