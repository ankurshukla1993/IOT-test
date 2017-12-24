package com.cooey.devices.common;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import com.cooey.devices.C0910R;
import com.cooey.devices.body_analyzer.BodyAnalyzerDeviceFragment;
import com.cooey.devices.bp_monitor.BloodPressureValueFragment;
import com.cooey.devices.help.DeviceHelpFragment;
import com.cooey.devices.vo.DeviceType;

public class DeviceInputPagerAdapter extends FragmentStatePagerAdapter {
    BloodPressureValueFragment bloodPressureValueFragment = BloodPressureValueFragment.newInstance();
    BodyAnalyzerDeviceFragment bodyAnalyzerDeviceFragment;
    private final DeviceType deviceType;

    public BodyAnalyzerDeviceFragment getBodyAnalyzerDeviceFragment() {
        return this.bodyAnalyzerDeviceFragment;
    }

    public void setBodyAnalyzerDeviceFragment(BodyAnalyzerDeviceFragment bodyAnalyzerDeviceFragment) {
        this.bodyAnalyzerDeviceFragment = bodyAnalyzerDeviceFragment;
    }

    public BloodPressureValueFragment getBloodPressureValueFragment() {
        return this.bloodPressureValueFragment;
    }

    public void setBloodPressureValueFragment(BloodPressureValueFragment bloodPressureValueFragment) {
        this.bloodPressureValueFragment = bloodPressureValueFragment;
    }

    public DeviceInputPagerAdapter(FragmentManager fm, DeviceType deviceType) {
        super(fm);
        this.deviceType = deviceType;
    }

    public Fragment getItem(int position) {
        if (this.deviceType == DeviceType.SPYHGOMANOMETER) {
            return getBPFragment(position);
        }
        return getBodyAnalyzerFragment(position);
    }

    private Fragment getBodyAnalyzerFragment(int position) {
        if (position == 0) {
            return DeviceHelpFragment.newInstance(C0910R.drawable.weighscale_foot, "Please stand on the scale with bare foot");
        }
        if (position == 1) {
            return DeviceTakeReadingFragment.newInstance("Name");
        }
        if (position == 2) {
            return DeviceTakeReadingFragment.newInstance("");
        }
        return null;
    }

    private Fragment getBPFragment(int position) {
        if (position == 0) {
            return DeviceHelpFragment.newInstance(C0910R.drawable.cooey_vector_cuff, "Please strap the cuff on to your arm");
        }
        if (position == 1) {
            return DeviceHelpFragment.newInstance(C0910R.drawable.scaning_bp_device_image_1, "Press the button of the profile that the devices was paired earlier with");
        }
        if (position == 2) {
            return DeviceTakeReadingFragment.newInstance("");
        }
        if (position == 3) {
            return this.bloodPressureValueFragment;
        }
        return null;
    }

    public int getCount() {
        if (this.deviceType == DeviceType.SPYHGOMANOMETER) {
            return 4;
        }
        return 3;
    }
}
