package com.cooey.devices.body_analyzer;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import com.cooey.devices.C0910R;
import com.cooey.devices.help.DeviceHelpFragment;

public class BodyAnalyzerViewPagerAdapter extends FragmentStatePagerAdapter {
    public BodyAnalyzerViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    public Fragment getItem(int position) {
        if (position == 0) {
            return DeviceHelpFragment.newInstance(C0910R.drawable.body_analyzer_back_button, "Press and hold Button on the back of the device");
        }
        if (position == 1) {
            return DeviceHelpFragment.newInstance(C0910R.drawable.scanning_bp_device_image_2, "Body analyzer pairing");
        }
        if (position == 2) {
            return BodyAnalyzerDeviceFragment.newInstance("", "");
        }
        return null;
    }

    public int getCount() {
        return 3;
    }
}
