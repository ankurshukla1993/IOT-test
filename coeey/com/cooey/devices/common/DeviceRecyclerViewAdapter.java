package com.cooey.devices.common;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView.Adapter;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import com.cooey.devices.C0910R;
import com.cooey.devices.CooeyDeviceDataSource;
import com.cooey.devices.databinding.DeviceItemBinding;
import com.cooey.devices.vo.Device;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DeviceRecyclerViewAdapter extends Adapter<DeviceViewHolder> {
    private final Context context;
    private String devices;
    private List<Device> devicesList;

    public DeviceRecyclerViewAdapter(Context context) {
        this.context = context;
        CooeyDeviceDataSource ds = new CooeyDeviceDataSource(context);
        ds.open();
        this.devicesList = ds.getAllDevices();
        HashMap<String, Integer> devices = getDeviceCounter();
        if (devices.get("808A0") == null || (this.devicesList != null && this.devicesList.size() > 0 && devices.get("808A0") != null && ((Integer) devices.get("808A0")).intValue() < 2)) {
            addBpMonitor();
        }
        if (devices.get("1257B") == null || (this.devicesList != null && this.devicesList.size() > 0 && devices.get("1257B") != null && ((Integer) devices.get("1257B")).intValue() < 8)) {
            addWeighingScale();
        }
        addVoiceBPDevice();
        ds.close();
    }

    public DeviceRecyclerViewAdapter(Context context, String devices) {
        this.context = context;
        this.devices = devices;
        this.devicesList = new ArrayList();
        if (devices.contentEquals("IHEALTH")) {
            addiHealthBG5Machine();
            addiHealthBP5Machine();
            addiHealthPO3Machine();
        }
    }

    private void addBpMonitor() {
        Device device = new Device();
        device.setDeviceName("Bp Monitor");
        device.setDeviceId("dummy_BP");
        device.setDeviceUserNumber(0);
        this.devicesList.add(device);
    }

    private void addWeighingScale() {
        Device device1 = new Device();
        device1.setDeviceName("WeighingScale");
        device1.setDeviceId("dummy_weight");
        device1.setDeviceUserNumber(0);
        this.devicesList.add(device1);
    }

    private void addPairedDevices() {
    }

    private void addiHealthBP5Machine() {
        Device iHealthBPMachine = new Device();
        iHealthBPMachine.setDeviceName("iHealth Blood Pressure Monitor");
        iHealthBPMachine.setDeviceId("dummy_bp5");
        iHealthBPMachine.setDeviceUserNumber(0);
        this.devicesList.add(iHealthBPMachine);
    }

    private void addiHealthPO3Machine() {
        Device iHealthPulseOxymeter = new Device();
        iHealthPulseOxymeter.setDeviceName("iHealth Pulse Oximeter");
        iHealthPulseOxymeter.setDeviceId("dummy_po3");
        iHealthPulseOxymeter.setDeviceUserNumber(0);
        this.devicesList.add(iHealthPulseOxymeter);
    }

    private void addiHealthBG5Machine() {
        Device iHealthBG5 = new Device();
        iHealthBG5.setDeviceName("iHealth Smart Glucometer");
        iHealthBG5.setDeviceId("dummy_bg5");
        iHealthBG5.setDeviceUserNumber(0);
        this.devicesList.add(iHealthBG5);
    }

    private void addVoiceBPDevice() {
        Device device = new Device();
        device.setDeviceName("Voice BP Monitor");
        this.devicesList.add(device);
    }

    public HashMap<String, Integer> getDeviceCounter() {
        HashMap<String, Integer> returnDevice = new HashMap();
        int i = 0;
        if (this.devicesList != null && this.devicesList.size() > 0) {
            for (Device device : this.devicesList) {
                i++;
                returnDevice.put(device.getDeviceName(), Integer.valueOf(i));
            }
        }
        return returnDevice;
    }

    public DeviceViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new DeviceViewHolder((DeviceItemBinding) DataBindingUtil.inflate(LayoutInflater.from(this.context), C0910R.layout.device_item, parent, false));
    }

    public void onBindViewHolder(DeviceViewHolder holder, int position) {
        if (this.devices != null && this.devices.contentEquals("IHEALTH")) {
            holder.bind((Device) this.devicesList.get(position), this.context);
        } else if (position == 0) {
            holder.bind(null, this.context);
        } else {
            holder.bind((Device) this.devicesList.get(position - 1), this.context);
        }
    }

    public int getItemCount() {
        if (this.devices == null || !this.devices.contentEquals("IHEALTH")) {
            if (this.devicesList != null) {
                return this.devicesList.size() + 1;
            }
            return 1;
        } else if (this.devicesList != null) {
            return this.devicesList.size();
        } else {
            return 0;
        }
    }
}
