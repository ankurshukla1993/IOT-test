package com.cooey.devices.bp_monitor;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.cooey.devices.C0910R;
import com.cooey.devices.databinding.FragmentBloodPressureValueBinding;

public class BloodPressureValueFragment extends Fragment {
    FragmentBloodPressureValueBinding binding;
    private float diastolic;
    private float heartRateValue;
    private OnFragmentInteractionListener mListener;
    private String mParam1;
    private String mParam2;
    private float systolic;

    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
    }

    public static BloodPressureValueFragment newInstance() {
        BloodPressureValueFragment fragment = new BloodPressureValueFragment();
        fragment.setArguments(new Bundle());
        return fragment;
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        this.binding = (FragmentBloodPressureValueBinding) DataBindingUtil.inflate(inflater, C0910R.layout.fragment_blood_pressure_value, container, false);
        this.binding.systolicValue.setText(String.valueOf((int) this.systolic));
        this.binding.diastolicValue.setText(String.valueOf((int) this.diastolic));
        this.binding.heartRateValue.setText(String.valueOf((int) this.heartRateValue));
        return this.binding.getRoot();
    }

    public void onButtonPressed(Uri uri) {
        if (this.mListener != null) {
            this.mListener.onFragmentInteraction(uri);
        }
    }

    public void onAttach(Context context) {
        super.onAttach(context);
    }

    public void onDetach() {
        super.onDetach();
        this.mListener = null;
    }

    public void setValues(float systolic, float diastolic, float heartRate, float meanArterialPressure) {
        this.systolic = systolic;
        this.diastolic = diastolic;
        this.heartRateValue = heartRate;
        if (this.binding != null && this.binding.systolicValue != null && this.binding.diastolicValue != null && this.binding.heartRateValue != null) {
            this.binding.systolicValue.setText(String.valueOf((int) systolic));
            this.binding.diastolicValue.setText(String.valueOf((int) diastolic));
            this.binding.heartRateValue.setText(String.valueOf((int) this.heartRateValue));
        }
    }
}
