package com.ihealth.devices.bp5;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import chatkit.holders.BloodPressureCardViewHolder;
import com.cooey.devices.R;
import com.ihealth.communication.control.Bp5Control;
import com.ihealth.communication.control.BpProfile;
import com.ihealth.communication.manager.iHealthDevicesManager;
import com.ihealth.devices.DeviceDataProcessor;
import com.ihealth.devices.IHealthResultCallback;
import org.json.JSONException;
import org.json.JSONObject;

public class BP5ValueFragment extends Fragment implements DeviceDataProcessor {
    private static final int HANDLER_MESSAGE = 101;
    private static final int HANDLER_MESSAGE_BATTERY = 102;
    private static final int HANDLER_MESSAGE_ERROR = 103;
    private static final int HANDLER_MESSAGE_PRESSURE = 105;
    private static final int HANDLER_MESSAGE_RESULT = 104;
    private static final int HANDLER_MESSAGE_WAVE = 106;
    private static final String MAC = "MAC";
    private static final String TAG = "DEVICES";
    private Bp5Control bp5Control;
    private TextView diastolicTextView;
    private TextView heartRateTextView;
    private String highPressure;
    private String lowPressure;
    private OnFragmentInteractionListener mListener;
    private String mac;
    Handler myHandler = new C21853();
    private String pulse;
    private Button saveButton;
    private Button startReadingButton;
    private TextView systolicTextView;

    class C21831 implements OnClickListener {
        C21831() {
        }

        public void onClick(View v) {
            if (BP5ValueFragment.this.bp5Control != null) {
                BP5ValueFragment.this.bp5Control.startMeasure();
                BP5ValueFragment.this.startReadingButton.setVisibility(8);
            }
        }
    }

    class C21842 implements OnClickListener {
        C21842() {
        }

        public void onClick(View view) {
            if (BP5ValueFragment.this.getActivity() != null) {
                Intent i = new Intent();
                i.putExtra(BloodPressureCardViewHolder.SYSTOLIC, BP5ValueFragment.this.highPressure);
                i.putExtra(BloodPressureCardViewHolder.DIASTOLIC, BP5ValueFragment.this.lowPressure);
                i.putExtra("HEART_RATE", BP5ValueFragment.this.pulse);
                BP5ValueFragment.this.getActivity().setResult(4872, i);
                BP5ValueFragment.this.getActivity().finish();
            }
        }
    }

    class C21853 extends Handler {
        C21853() {
        }
    }

    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
    }

    public static BP5ValueFragment newInstance(String mac, IHealthResultCallback iHealthResultCallback) {
        BP5ValueFragment fragment = new BP5ValueFragment();
        Bundle args = new Bundle();
        args.putString(MAC, mac);
        fragment.setArguments(args);
        return fragment;
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            this.mac = getArguments().getString(MAC);
            this.bp5Control = iHealthDevicesManager.getInstance().getBp5Control(this.mac);
        }
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_bp5_value, container, false);
        this.systolicTextView = (TextView) view.findViewById(R.id.systolic_text);
        this.diastolicTextView = (TextView) view.findViewById(R.id.diastolic_text);
        this.heartRateTextView = (TextView) view.findViewById(R.id.pulse_rate_text);
        this.startReadingButton = (Button) view.findViewById(R.id.start_reading_button);
        this.startReadingButton.setOnClickListener(new C21831());
        this.saveButton = (Button) view.findViewById(R.id.save_button);
        this.saveButton.setOnClickListener(new C21842());
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

    public void process(String mac, String deviceType, String action, String message) {
        Log.i(TAG, "mac: " + mac);
        Log.i(TAG, "deviceType: " + deviceType);
        Log.i(TAG, "action: " + action);
        Log.i(TAG, "message: " + message);
        Message msg;
        if (BpProfile.ACTION_BATTERY_BP.equals(action)) {
            try {
                String battery = new JSONObject(message).getString("battery");
                msg = new Message();
                msg.what = 102;
                msg.obj = "battery: " + battery;
                msg.obj = message;
                this.myHandler.sendMessage(msg);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        } else if ("error_bp".equals(action)) {
            try {
                String num = new JSONObject(message).getString("error");
                msg = new Message();
                msg.what = 103;
                msg.obj = "error num: " + num;
                msg.obj = message;
                this.myHandler.sendMessage(msg);
            } catch (JSONException e2) {
                e2.printStackTrace();
            }
        } else if (BpProfile.ACTION_INTERRUPTED_BP.equals(action)) {
            this.bp5Control = iHealthDevicesManager.getInstance().getBp5Control(mac);
        } else if ("online_pressure_bp".equals(action)) {
            try {
                pressure = new JSONObject(message).getString("pressure");
                msg = new Message();
                msg.what = 105;
                msg.obj = "pressure: " + pressure;
                msg.obj = message;
                this.myHandler.sendMessage(msg);
            } catch (JSONException e22) {
                e22.printStackTrace();
            }
        } else if (BpProfile.ACTION_ONLINE_PULSEWAVE_BP.equals(action)) {
            try {
                info = new JSONObject(message);
                pressure = info.getString("pressure");
                String wave = info.getString(BpProfile.PULSEWAVE_BP);
                String heartbeat = info.getString("heartbeat");
                msg = new Message();
                msg.what = 106;
                msg.obj = "pressure:" + pressure + "\nwave: " + wave + "\n - heartbeat:" + heartbeat;
                msg.obj = message;
                this.myHandler.sendMessage(msg);
            } catch (JSONException e222) {
                e222.printStackTrace();
            }
        } else if ("online_result_bp".equals(action)) {
            try {
                info = new JSONObject(message);
                this.highPressure = info.getString("sys");
                this.lowPressure = info.getString("dia");
                String ahr = info.getString("arrhythmia");
                this.pulse = info.getString("heartRate");
                msg = new Message();
                msg.what = 104;
                msg.obj = "highPressure: " + this.highPressure + "lowPressure: " + this.lowPressure + "ahr: " + ahr + "pulse: " + this.pulse;
                msg.obj = message;
                this.systolicTextView.setText(this.highPressure);
                this.diastolicTextView.setText(this.lowPressure);
                this.heartRateTextView.setText(this.pulse);
                this.saveButton.setVisibility(0);
                this.myHandler.sendMessage(msg);
                stopMeasure();
            } catch (JSONException e2222) {
                e2222.printStackTrace();
            }
        } else if (BpProfile.ACTION_ZOREING_BP.equals(action)) {
            msg = new Message();
            msg.what = 101;
            msg.obj = "zoreing";
            this.myHandler.sendMessage(msg);
        } else if (BpProfile.ACTION_ZOREOVER_BP.equals(action)) {
            msg = new Message();
            msg.what = 101;
            msg.obj = "zoreover";
            this.myHandler.sendMessage(msg);
        } else {
            if (!action.equals(iHealthDevicesManager.IHEALTH_COMM_TIMEOUT)) {
            }
        }
    }

    private void stopMeasure() {
    }
}
