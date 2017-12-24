package com.cooey.devices.body_analyzer;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.cooey.devices.C0910R;

public class BodyAnalyzerDeviceFragment extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private float basalMetabolism;
    private float bodyFatRatio;
    private float bodyWaterRatio;
    private float boneDensity;
    private OnFragmentInteractionListener mListener;
    private String mParam1;
    private String mParam2;
    private float muscleMassRatio;
    private float visceralFatLevel;
    private double weight;

    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
    }

    public static BodyAnalyzerDeviceFragment newInstance(String param1, String param2) {
        BodyAnalyzerDeviceFragment fragment = new BodyAnalyzerDeviceFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            this.mParam1 = getArguments().getString(ARG_PARAM1);
            this.mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(C0910R.layout.fragment_body_analyzer_device, container, false);
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
}
