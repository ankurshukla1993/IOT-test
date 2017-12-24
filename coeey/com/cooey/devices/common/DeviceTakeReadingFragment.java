package com.cooey.devices.common;

import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.cooey.devices.C0910R;

public class DeviceTakeReadingFragment extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private com.cooey.devices.common.DeviceScanFragment.OnFragmentInteractionListener mListener;
    private String mParam1;
    private String mParam2;

    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
    }

    public static DeviceTakeReadingFragment newInstance(String name) {
        DeviceTakeReadingFragment fragment = new DeviceTakeReadingFragment();
        fragment.setArguments(new Bundle());
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
        View view = inflater.inflate(C0910R.layout.fragment_bpdevice_take_reading, container, false);
        ImageView img = (ImageView) view.findViewById(C0910R.id.progress_image);
        ((TextView) view.findViewById(C0910R.id.txt_scan_read)).setText("Taking Reading Please Wait....");
        ((AnimationDrawable) img.getBackground()).start();
        return view;
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
