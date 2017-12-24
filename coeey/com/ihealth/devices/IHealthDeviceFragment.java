package com.ihealth.devices;

import android.content.Context;
import android.os.Message;
import android.support.v4.app.Fragment;

public abstract class IHealthDeviceFragment extends Fragment {
    private Message message;
    private DeviceDataProcessor messageNotifier;

    public void setMessage(Message message) {
        this.message = message;
    }

    public void setMessageNotifyCallback(DeviceDataProcessor messageNotifier) {
        this.messageNotifier = messageNotifier;
    }

    public void onAttach(Context context) {
        super.onAttach(context);
    }

    public void onDetach() {
        super.onDetach();
    }
}
