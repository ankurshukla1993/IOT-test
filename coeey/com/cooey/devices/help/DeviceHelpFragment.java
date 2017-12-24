package com.cooey.devices.help;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.cooey.devices.C0910R;
import com.cooey.devices.databinding.FragmentDeviceHelpBinding;

public class DeviceHelpFragment extends Fragment {
    private static final String HELP_DRAWABLE = "param1";
    private static final String HELP_TEXT = "param2";
    private int helpDrawable;
    private String helpText;
    private OnFragmentInteractionListener mListener;

    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
    }

    public static DeviceHelpFragment newInstance(int helpDrawableResource, String helpText) {
        DeviceHelpFragment fragment = new DeviceHelpFragment();
        Bundle args = new Bundle();
        args.putInt(HELP_DRAWABLE, helpDrawableResource);
        args.putString(HELP_TEXT, helpText);
        fragment.setArguments(args);
        return fragment;
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            this.helpDrawable = getArguments().getInt(HELP_DRAWABLE);
            this.helpText = getArguments().getString(HELP_TEXT);
        }
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        FragmentDeviceHelpBinding fragmentDeviceHelpBinding = (FragmentDeviceHelpBinding) DataBindingUtil.inflate(inflater, C0910R.layout.fragment_device_help, container, false);
        fragmentDeviceHelpBinding.helpImage.setImageResource(this.helpDrawable);
        fragmentDeviceHelpBinding.helpText.setText(this.helpText);
        return fragmentDeviceHelpBinding.getRoot();
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
