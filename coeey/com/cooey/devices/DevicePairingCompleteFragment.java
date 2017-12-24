package com.cooey.devices;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import com.cooey.devices.body_analyzer.BodyAnalyzerDeviceInputActivity;
import com.cooey.devices.bp_monitor.BPDeviceInputActvity;
import com.cooey.devices.vo.DeviceType;

public class DevicePairingCompleteFragment extends Fragment {
    private static final String DEVICE_TYPE = "DEVICE_TYPE";
    private DeviceType deviceType;
    private OnFragmentInteractionListener mListener;

    class C09081 implements OnClickListener {
        C09081() {
        }

        public void onClick(View view) {
            DevicePairingCompleteFragment.this.getActivity().startActivityForResult(new Intent(DevicePairingCompleteFragment.this.getContext(), DevicesActivity.class), CooeyDeviceManager.DEVICE_READ_REQUEST);
        }
    }

    class C09092 implements OnClickListener {
        C09092() {
        }

        public void onClick(View view) {
            if (DevicePairingCompleteFragment.this.deviceType.equals(DeviceType.BODY_ANALYZER)) {
                DevicePairingCompleteFragment.this.getActivity().startActivityForResult(new Intent(DevicePairingCompleteFragment.this.getContext(), BodyAnalyzerDeviceInputActivity.class), CooeyDeviceManager.DEVICE_READ_REQUEST);
                return;
            }
            DevicePairingCompleteFragment.this.getActivity().startActivityForResult(new Intent(DevicePairingCompleteFragment.this.getContext(), BPDeviceInputActvity.class), CooeyDeviceManager.DEVICE_READ_REQUEST);
        }
    }

    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
    }

    public static DevicePairingCompleteFragment newInstance(DeviceType deviceType) {
        DevicePairingCompleteFragment fragment = new DevicePairingCompleteFragment();
        Bundle args = new Bundle();
        args.putString(DEVICE_TYPE, deviceType.toString());
        fragment.setArguments(args);
        return fragment;
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            String deviceTypeString = getArguments().getString(DEVICE_TYPE);
            if (deviceTypeString != null) {
                this.deviceType = (DeviceType) Enum.valueOf(DeviceType.class, deviceTypeString);
            }
        }
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(C0910R.layout.fragment_device_pairing_complete, container, false);
        Button takeReadingButton = (Button) view.findViewById(C0910R.id.take_reading_button);
        ((Button) view.findViewById(C0910R.id.ok_button)).setOnClickListener(new C09081());
        takeReadingButton.setOnClickListener(new C09092());
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
