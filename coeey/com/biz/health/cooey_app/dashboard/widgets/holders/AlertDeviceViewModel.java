package com.biz.health.cooey_app.dashboard.widgets.holders;

import android.content.Context;
import android.content.Intent;
import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.net.Uri;
import android.view.View;
import com.cooey.common.vo.DeviceAlert;
import java.util.Date;
import java.util.Locale;

public class AlertDeviceViewModel extends BaseObservable {
    String battery;
    Context context;
    DeviceAlert deviceAlert;
    String location = "";
    String temperature;
    private long timestamp;

    public AlertDeviceViewModel(Context context, DeviceAlert deviceAlert) {
        this.deviceAlert = deviceAlert;
        this.context = context;
        this.temperature = deviceAlert.getTemperature();
        this.battery = deviceAlert.getBatteryStatus();
        if (deviceAlert.getTimestamp() > 0) {
            this.timestamp = deviceAlert.getTimestamp();
        } else {
            this.timestamp = new Date().getTime();
        }
    }

    public DeviceAlert getDeviceAlert() {
        return this.deviceAlert;
    }

    public void setDeviceAlert(DeviceAlert deviceAlert) {
        this.deviceAlert = deviceAlert;
    }

    @Bindable
    public String getTemperature() {
        return this.temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
        notifyPropertyChanged(47);
    }

    @Bindable
    public String getBattery() {
        return this.battery;
    }

    public void setBattery(String battery) {
        this.battery = battery;
        notifyPropertyChanged(2);
    }

    @Bindable
    public String getLocation() {
        return this.location;
    }

    public void setLocation(String location) {
        this.location = location;
        notifyPropertyChanged(18);
    }

    @Bindable
    public long getTimestamp() {
        return this.timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
        notifyPropertyChanged(49);
    }

    public void OnLocationClick(View view) {
        Intent intent = new Intent("android.intent.action.VIEW", Uri.parse(String.format(Locale.ENGLISH, "geo:%f,%f?q=%f,%f", new Object[]{Double.valueOf(this.deviceAlert.getLatitude()), Double.valueOf(this.deviceAlert.getLongitude()), Double.valueOf(this.deviceAlert.getLatitude()), Double.valueOf(this.deviceAlert.getLongitude())})));
        intent.setPackage("com.google.android.apps.maps");
        this.context.startActivity(intent);
    }
}
