package com.ihealth.devices;

import android.net.Uri;
import android.os.Bundle;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.cooey.devices.R;

public class IHealthScanDeviceFragment extends IHealthDeviceFragment {
    private OnFragmentInteractionListener mListener;

    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
    }

    public static IHealthScanDeviceFragment newInstance(Message message) {
        IHealthScanDeviceFragment fragment = new IHealthScanDeviceFragment();
        fragment.setMessage(message);
        return fragment;
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_ihealth_scan_device, container, false);
    }
}
