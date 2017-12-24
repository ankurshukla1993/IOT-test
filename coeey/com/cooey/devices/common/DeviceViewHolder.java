package com.cooey.devices.common;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.text.Html;
import android.view.View;
import android.view.View.OnClickListener;
import com.cooey.devices.C0910R;
import com.cooey.devices.CooeyDeviceManager;
import com.cooey.devices.body_analyzer.BodyAnalyzerDeviceActivity;
import com.cooey.devices.body_analyzer.BodyAnalyzerDeviceInputActivity;
import com.cooey.devices.bp_monitor.BPDeviceActivity;
import com.cooey.devices.bp_monitor.BPDeviceInputActvity;
import com.cooey.devices.bpmeter.VoiceBpMonitorActivity;
import com.cooey.devices.databinding.DeviceItemBinding;
import com.cooey.devices.glucometer.GlucometerActivityRead;
import com.cooey.devices.helpers.DeviceUtil;
import com.cooey.devices.vo.Device;
import com.cooey.devices.vo.DeviceType;
import com.ihealth.communication.manager.iHealthDevicesManager;
import com.ihealth.devices.IHealthDevicesScanActivity;

public class DeviceViewHolder extends ViewHolder {
    private final DeviceItemBinding deviceItemBinding;

    public DeviceViewHolder(DeviceItemBinding deviceItemBinding) {
        super(deviceItemBinding.getRoot());
        this.deviceItemBinding = deviceItemBinding;
    }

    public void bind(final Device device, final Context context) {
        if (device != null) {
            this.deviceItemBinding.deviceImage.setImageDrawable(DeviceUtil.getDeviceDrawable(device.getDeviceName(), context));
            this.deviceItemBinding.deviceName.setText(DeviceUtil.getDeviceName(device.getDeviceName()));
            final DeviceType deviceType = DeviceUtil.getDeviceTypeFromName(device.getDeviceName());
            if (deviceType != DeviceType.NEWBPMETER && this.deviceItemBinding.deviceProfileNumber.getVisibility() == 8) {
                this.deviceItemBinding.deviceProfileNumber.setVisibility(0);
                if (device.getDeviceId().equalsIgnoreCase("dummy") || device.getDeviceId().equalsIgnoreCase("dummy_weight") || device.getDeviceId().equalsIgnoreCase("dummy_BP")) {
                    this.deviceItemBinding.deviceProfileNumber.setText("");
                    this.deviceItemBinding.takeReadingButton.setText("Pair Device");
                } else {
                    this.deviceItemBinding.deviceProfileNumber.setText(Html.fromHtml("Measure using profile <B>button " + String.valueOf(device.getDeviceUserNumber()) + "</B>"));
                }
            }
            this.deviceItemBinding.takeReadingButton.setOnClickListener(new OnClickListener() {
                public void onClick(View view) {
                    if (deviceType == DeviceType.SPYHGOMANOMETER && !device.getDeviceId().equalsIgnoreCase("dummy_BP")) {
                        ((Activity) context).startActivityForResult(new Intent(context, BPDeviceInputActvity.class), CooeyDeviceManager.DEVICE_READ_REQUEST);
                    } else if (deviceType == DeviceType.SPYHGOMANOMETER && device.getDeviceId().equalsIgnoreCase("dummy_BP")) {
                        ((Activity) context).startActivityForResult(new Intent(context, BPDeviceActivity.class), CooeyDeviceManager.DEVICE_READ_REQUEST);
                    } else if (deviceType == DeviceType.BODY_ANALYZER && device.getDeviceId().equalsIgnoreCase("dummy_weight")) {
                        ((Activity) context).startActivityForResult(new Intent(context, BodyAnalyzerDeviceActivity.class), CooeyDeviceManager.DEVICE_READ_REQUEST);
                    } else if (deviceType == DeviceType.NEWBPMETER) {
                        ((Activity) context).startActivityForResult(new Intent(context, VoiceBpMonitorActivity.class), CooeyDeviceManager.DEVICE_READ_REQUEST);
                    } else if (deviceType == DeviceType.BODY_ANALYZER && !device.getDeviceId().equalsIgnoreCase("dummy_weight")) {
                        ((Activity) context).startActivityForResult(new Intent(context, BodyAnalyzerDeviceInputActivity.class), CooeyDeviceManager.DEVICE_READ_REQUEST);
                    } else if (deviceType == DeviceType.IHEALTH_BP5_MONITOR) {
                        if (device.getDeviceId().equalsIgnoreCase("dummy_bp5")) {
                            i = new Intent(context, IHealthDevicesScanActivity.class);
                            i.putExtra("deviceType", iHealthDevicesManager.TYPE_BP5);
                            ((Activity) context).startActivityForResult(i, 4872);
                            return;
                        }
                        i = new Intent(context, IHealthDevicesScanActivity.class);
                        i.putExtra("deviceType", iHealthDevicesManager.TYPE_BP5);
                        i.putExtra(iHealthDevicesManager.IHEALTH_DEVICE_MAC, device.getDeviceId());
                        ((Activity) context).startActivityForResult(i, 4872);
                    } else if (deviceType == DeviceType.IHEALTH_PULSE_OXIMETER_3) {
                        if (device.getDeviceId().equalsIgnoreCase("dummy_po3")) {
                            i = new Intent(context, IHealthDevicesScanActivity.class);
                            i.putExtra("deviceType", iHealthDevicesManager.TYPE_PO3);
                            ((Activity) context).startActivityForResult(i, 3984);
                            return;
                        }
                        i = new Intent(context, IHealthDevicesScanActivity.class);
                        i.putExtra("deviceType", iHealthDevicesManager.TYPE_PO3);
                        i.putExtra(iHealthDevicesManager.IHEALTH_DEVICE_MAC, device.getDeviceId());
                        ((Activity) context).startActivityForResult(i, 3984);
                    } else if (deviceType != DeviceType.IHEALTH_BG5_MONITOR) {
                    } else {
                        if (device.getDeviceId().equalsIgnoreCase("dummy_bg5")) {
                            i = new Intent(context, IHealthDevicesScanActivity.class);
                            i.putExtra("deviceType", iHealthDevicesManager.TYPE_BG5);
                            ((Activity) context).startActivityForResult(i, 4848);
                            return;
                        }
                        i = new Intent(context, IHealthDevicesScanActivity.class);
                        i.putExtra("deviceType", iHealthDevicesManager.TYPE_BG5);
                        i.putExtra(iHealthDevicesManager.IHEALTH_DEVICE_MAC, device.getDeviceId());
                        ((Activity) context).startActivityForResult(i, 4848);
                    }
                }
            });
        } else {
            this.deviceItemBinding.deviceImage.setImageResource(C0910R.drawable.gluco);
            this.deviceItemBinding.deviceName.setText("Glucometer");
            this.deviceItemBinding.takeReadingButton.setOnClickListener(new OnClickListener() {
                public void onClick(View view) {
                    ((AppCompatActivity) context).startActivityForResult(new Intent(context, GlucometerActivityRead.class), CooeyDeviceManager.DEVICE_READ_REQUEST);
                }
            });
        }
        this.deviceItemBinding.executePendingBindings();
    }
}
