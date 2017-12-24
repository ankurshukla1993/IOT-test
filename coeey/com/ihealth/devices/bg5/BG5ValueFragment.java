package com.ihealth.devices.bg5;

import android.content.Context;
import android.content.Intent;
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
import android.widget.Toast;
import chatkit.holders.BloodGlucoseContentViewHolder;
import com.cooey.devices.R;
import com.ihealth.communication.control.Bg5Profile;
import com.ihealth.devices.DeviceDataProcessor;
import com.ihealth.devices.IHealthResultCallback;
import org.json.JSONException;
import org.json.JSONObject;

public class BG5ValueFragment extends Fragment implements DeviceDataProcessor {
    private static final int HANDLER_MESSAGE = 101;
    private static final int HANDLER_MESSAGE_ERROR = 103;
    private static final int HANDLER_MESSAGE_GET_BLOOD = 105;
    private static final int HANDLER_MESSAGE_RESULT = 106;
    private static final int HANDLER_MESSAGE_START = 104;
    private static final int HANDLER_MESSAGE_STRIP_IN = 102;
    private static final String MAC = "MAC";
    private static final String TAG = "DEVICES";
    private TextView bloodGlucoseTextView;
    private String mac;
    private String result;
    private Button saveButton;

    class C21821 implements OnClickListener {
        C21821() {
        }

        public void onClick(View view) {
            Intent i = new Intent();
            i.putExtra(BloodGlucoseContentViewHolder.GLUCOSE, BG5ValueFragment.this.result);
            BG5ValueFragment.this.getActivity().setResult(4848, i);
            BG5ValueFragment.this.getActivity().finish();
        }
    }

    public static BG5ValueFragment newInstance(String mac, IHealthResultCallback iHealthResultCallback) {
        BG5ValueFragment fragment = new BG5ValueFragment();
        Bundle args = new Bundle();
        args.putString(MAC, mac);
        fragment.setArguments(args);
        return fragment;
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            this.mac = getArguments().getString(MAC);
        }
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_bg5_value, container, false);
        this.bloodGlucoseTextView = (TextView) view.findViewById(R.id.blood_glucose_text);
        this.saveButton = (Button) view.findViewById(R.id.save_button);
        this.saveButton.setOnClickListener(new C21821());
        return view;
    }

    public void onAttach(Context context) {
        super.onAttach(context);
    }

    public void onDetach() {
        super.onDetach();
    }

    public void process(String mac, String deviceType, String action, String message) {
        Log.i(TAG, "mac: " + mac);
        Log.i(TAG, "deviceType: " + deviceType);
        Log.i(TAG, "action: " + action);
        Log.i(TAG, "message: " + message);
        Message msg = new Message();
        msg.what = 101;
        int i = -1;
        switch (action.hashCode()) {
            case -2136929165:
                if (action.equals("action_set_time")) {
                    i = 1;
                    break;
                }
                break;
            case -2136894678:
                if (action.equals(Bg5Profile.ACTION_SET_UNIT)) {
                    i = 2;
                    break;
                }
                break;
            case -2084641900:
                if (action.equals(Bg5Profile.ACTION_DELETE_HISTORICAL_DATA)) {
                    i = 7;
                    break;
                }
                break;
            case -1871977120:
                if (action.equals(Bg5Profile.ACTION_BATTERY_BG)) {
                    i = 0;
                    break;
                }
                break;
            case -1748761709:
                if (action.equals(Bg5Profile.ACTION_GET_BOTTLEID)) {
                    i = 4;
                    break;
                }
                break;
            case -1332863427:
                if (action.equals(Bg5Profile.ACTION_STRIP_OUT)) {
                    i = 14;
                    break;
                }
                break;
            case -963134681:
                if (action.equals(Bg5Profile.ACTION_HISTORICAL_DATA_BG)) {
                    i = 6;
                    break;
                }
                break;
            case -667685444:
                if (action.equals(Bg5Profile.ACTION_ONLINE_RESULT_BG)) {
                    i = 10;
                    break;
                }
                break;
            case -477951411:
                if (action.equals(Bg5Profile.ACTION_GET_CODEINFO)) {
                    i = 5;
                    break;
                }
                break;
            case -205826645:
                if (action.equals(Bg5Profile.ACTION_KEEP_LINK)) {
                    i = 11;
                    break;
                }
                break;
            case -73788002:
                if (action.equals(Bg5Profile.ACTION_ERROR_BG)) {
                    i = 3;
                    break;
                }
                break;
            case -14423961:
                if (action.equals(Bg5Profile.ACTION_GET_BLOOD)) {
                    i = 13;
                    break;
                }
                break;
            case 1203930198:
                if (action.equals(Bg5Profile.ACTION_STRIP_IN)) {
                    i = 12;
                    break;
                }
                break;
            case 1373073272:
                if (action.equals(Bg5Profile.ACTION_SET_BOTTLE_MESSAGE_SUCCESS)) {
                    i = 8;
                    break;
                }
                break;
            case 2030553144:
                if (action.equals(Bg5Profile.ACTION_START_MEASURE)) {
                    i = 9;
                    break;
                }
                break;
        }
        switch (i) {
            case 0:
            case 1:
            case 2:
            case 3:
                msg.what = 103;
                msg.obj = message;
                return;
            case 4:
            case 5:
            case 6:
            case 7:
            case 8:
            case 9:
                msg.what = 104;
                return;
            case 10:
                try {
                    JSONObject obj = new JSONObject((String) msg.obj);
                    this.result = obj.getString("result");
                    this.bloodGlucoseTextView.setText(obj.getString("result"));
                    this.saveButton.setVisibility(0);
                    break;
                } catch (JSONException e) {
                    e.printStackTrace();
                    break;
                }
            case 11:
                break;
            case 12:
                msg.what = 102;
                msg.obj = "strip in";
                Toast.makeText(getActivity(), "Strip inserted", 0).show();
                return;
            case 13:
                msg.what = 105;
                msg.obj = "get blood";
                Toast.makeText(getActivity(), "Blood Taken", 0).show();
                return;
            case 14:
                msg.obj = "strip out";
                Toast.makeText(getActivity(), "Strip Removed", 0).show();
                return;
            default:
                return;
        }
        msg.obj = message;
    }
}
