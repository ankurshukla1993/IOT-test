package com.ihealth.devices.po3;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import com.cooey.devices.R;
import com.ihealth.communication.control.Po3Control;
import com.ihealth.communication.control.PoProfile;
import com.ihealth.communication.manager.iHealthDevicesManager;
import com.ihealth.devices.DeviceDataProcessor;
import com.ihealth.devices.IHealthResultCallback;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

public class PO3ValueFragment extends Fragment implements DeviceDataProcessor {
    private static final String MAC = "MAC";
    private static final String TAG = "DeviceData";
    private static IHealthResultCallback iHealthResultCallback;
    private TextView bloodOxygenTextView;
    private OnFragmentInteractionListener mListener;
    private Po3Control mPo3Control;
    private String mac;
    private int oxygen;
    private int pulseRate;
    private TextView pulseRateTextView;
    private Button saveButton;

    class C21861 implements OnClickListener {
        C21861() {
        }

        public void onClick(View view) {
            if (PO3ValueFragment.this.getActivity() != null) {
                Intent i = new Intent();
                i.putExtra("SPO2", PO3ValueFragment.this.oxygen);
                i.putExtra("PULSE_RATE", PO3ValueFragment.this.pulseRate);
                PO3ValueFragment.this.getActivity().setResult(3984, i);
                PO3ValueFragment.this.getActivity().finish();
            }
        }
    }

    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
    }

    public static PO3ValueFragment newInstance(String mac, IHealthResultCallback iHealthResultCallback) {
        iHealthResultCallback = iHealthResultCallback;
        PO3ValueFragment fragment = new PO3ValueFragment();
        Bundle args = new Bundle();
        args.putString(MAC, mac);
        fragment.setArguments(args);
        return fragment;
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            this.mac = getArguments().getString(MAC);
            this.mPo3Control = iHealthDevicesManager.getInstance().getPo3Control(this.mac);
            if (this.mPo3Control != null) {
                this.mPo3Control.startMeasure();
            }
        }
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_po3_value, container, false);
        this.bloodOxygenTextView = (TextView) view.findViewById(R.id.blood_oxygen_text);
        this.pulseRateTextView = (TextView) view.findViewById(R.id.pulse_rate);
        this.saveButton = (Button) view.findViewById(R.id.save_button);
        this.saveButton.setOnClickListener(new C21861());
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
    }

    public void process(String mac, String deviceType, String action, String message) {
        Log.d(TAG, "mac:" + mac + "--type:" + deviceType + "--action:" + action + "--message:" + message);
        JSONTokener jsonTokener = new JSONTokener(message);
        Object obj = -1;
        switch (action.hashCode()) {
            case 255504424:
                if (action.equals(PoProfile.ACTION_LIVEDA_PO)) {
                    obj = null;
                    break;
                }
                break;
            case 534859735:
                if (action.equals(PoProfile.ACTION_RESULTDATA_PO)) {
                    obj = 1;
                    break;
                }
                break;
            case 1866394993:
                if (action.equals(PoProfile.ACTION_BATTERY_PO)) {
                    obj = 2;
                    break;
                }
                break;
        }
        JSONObject jsonObject;
        float PI;
        JSONArray jsonArray;
        int[] wave;
        int i;
        switch (obj) {
            case null:
                try {
                    jsonObject = (JSONObject) jsonTokener.nextValue();
                    this.oxygen = jsonObject.getInt(PoProfile.BLOOD_OXYGEN_PO);
                    this.pulseRate = jsonObject.getInt(PoProfile.PULSE_RATE_PO);
                    PI = (float) jsonObject.getDouble(PoProfile.PI_PO);
                    jsonArray = jsonObject.getJSONArray(PoProfile.PULSE_WAVE_PO);
                    wave = new int[3];
                    for (i = 0; i < jsonArray.length(); i++) {
                        wave[i] = jsonArray.getInt(i);
                    }
                    Log.i(TAG, "oxygen:" + this.oxygen + "--pulseRateTextView:" + this.pulseRate + "--Pi:" + PI + "-wave1:" + wave[0] + "-wave2:" + wave[1] + "--wave3:" + wave[2]);
                    this.bloodOxygenTextView.setText(String.valueOf(this.oxygen));
                    this.pulseRateTextView.setText(String.valueOf(this.pulseRate));
                    this.saveButton.setVisibility(0);
                    return;
                } catch (JSONException e) {
                    e.printStackTrace();
                    return;
                }
            case 1:
                try {
                    jsonObject = (JSONObject) jsonTokener.nextValue();
                    String dataId = jsonObject.getString("dataID");
                    int oxygen = jsonObject.getInt(PoProfile.BLOOD_OXYGEN_PO);
                    int pulseRate = jsonObject.getInt(PoProfile.PULSE_RATE_PO);
                    PI = (float) jsonObject.getDouble(PoProfile.PI_PO);
                    jsonArray = jsonObject.getJSONArray(PoProfile.PULSE_WAVE_PO);
                    wave = new int[3];
                    for (i = 0; i < jsonArray.length(); i++) {
                        wave[i] = jsonArray.getInt(i);
                    }
                    Log.i(TAG, "dataId:" + dataId + "--oxygen:" + oxygen + "--pulseRateTextView:" + pulseRate + "--Pi:" + PI + "-wave1:" + wave[0] + "-wave2:" + wave[1] + "--wave3:" + wave[2]);
                    this.bloodOxygenTextView.setText(String.valueOf(oxygen));
                    this.pulseRateTextView.setText(String.valueOf(pulseRate));
                    this.saveButton.setVisibility(0);
                    return;
                } catch (JSONException e2) {
                    e2.printStackTrace();
                    return;
                }
            case 2:
                try {
                    Log.d(TAG, "battery:" + ((JSONObject) jsonTokener.nextValue()).getInt("battery"));
                } catch (JSONException e22) {
                    e22.printStackTrace();
                }
                Message message3 = new Message();
                message3.what = 1;
                message3.obj = message;
                return;
            default:
                return;
        }
    }
}
