package com.cooey.devices.five_in_one;

import android.bluetooth.BluetoothDevice;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.cooey.devices.C0910R;
import java.util.ArrayList;
import java.util.HashMap;

public class BluetoothDeviceAdapter extends BaseAdapter {
    private ArrayList<BluetoothDevice> mDevices;
    private LayoutInflater mInflater;
    private HashMap<String, Integer> mRssiMap;

    public BluetoothDeviceAdapter(Context context, ArrayList<BluetoothDevice> devices) {
        this.mInflater = LayoutInflater.from(context);
        this.mDevices = devices;
    }

    public int getCount() {
        return this.mDevices.size();
    }

    public Object getItem(int position) {
        return this.mDevices.get(position);
    }

    public long getItemId(int position) {
        return 0;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        LinearLayout llItem;
        BluetoothDevice dev = (BluetoothDevice) this.mDevices.get(position);
        if (convertView != null) {
            llItem = (LinearLayout) convertView;
        } else {
            llItem = (LinearLayout) this.mInflater.inflate(C0910R.layout.devices_dialog_bluetooth_item, null);
        }
        TextView tvName = (TextView) llItem.findViewById(C0910R.id.tvBtItemName);
        TextView tvAddr = (TextView) llItem.findViewById(C0910R.id.tvBtItemAddr);
        if (dev.getName().contains("Berry")) {
            tvName.setText("Five in One");
        } else {
            tvName.setText(dev.getName());
        }
        tvAddr.setText("MAC: " + dev.getAddress());
        return llItem;
    }
}
