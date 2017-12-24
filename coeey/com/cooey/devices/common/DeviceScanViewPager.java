package com.cooey.devices.common;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import com.cooey.devices.C0910R;
import com.cooey.devices.DevicePairingCompleteFragment;
import com.cooey.devices.help.DeviceHelpFragment;
import com.cooey.devices.vo.DeviceType;

public class DeviceScanViewPager extends FragmentStatePagerAdapter {
    private final DeviceType deviceType;

    public DeviceScanViewPager(FragmentManager fm, DeviceType deviceType) {
        super(fm);
        this.deviceType = deviceType;
    }

    public Fragment getItem(int position) {
        if (this.deviceType == DeviceType.SPYHGOMANOMETER) {
            return getBPFragments(position);
        }
        if (this.deviceType == DeviceType.IHEALTH_BP5_MONITOR) {
            return getiHealthBP5Fragments(position);
        }
        if (this.deviceType == DeviceType.IHEALTH_BG5_MONITOR) {
            return getiHealthBG5Fragments(position);
        }
        return getBodyAnalyzerFragments(position);
    }

    private Fragment getBodyAnalyzerFragments(int position) {
        if (position == 0) {
            return DeviceHelpFragment.newInstance(C0910R.drawable.body_analyzer_back_button, "Press and hold Button the small button on the back of the devices.");
        }
        if (position == 1) {
            return DeviceHelpFragment.newInstance(C0910R.drawable.body_analyzer_pairing, "Wait for the dancing circles on the screen to appear and press next.");
        }
        if (position == 2) {
            return DeviceScanFragment.newInstance("Name");
        }
        if (position == 3) {
            return DevicePairingCompleteFragment.newInstance(this.deviceType);
        }
        return null;
    }

    private Fragment getBPFragments(int position) {
        if (position == 0) {
            return DeviceHelpFragment.newInstance(C0910R.drawable.scaning_bp_device_image_1, "Press and hold Button '2'");
        }
        if (position == 1) {
            return DeviceHelpFragment.newInstance(C0910R.drawable.scanning_bp_device_image_2, "Wait for the dancing circles on the screen to appear and press next.");
        }
        if (position == 2) {
            return DeviceScanFragment.newInstance("Name");
        }
        if (position == 3) {
            return DevicePairingCompleteFragment.newInstance(this.deviceType);
        }
        return null;
    }

    private Fragment getiHealthBP5Fragments(int position) {
        if (position == 0) {
            return DeviceHelpFragment.newInstance(C0910R.drawable.scaning_bp_device_image_1, "This is to turn on the bp meter scan mode");
        }
        if (position == 1) {
            return DeviceScanFragment.newInstance("Name");
        }
        return null;
    }

    private Fragment getiHealthBG5Fragments(int position) {
        if (position == 0) {
            return DeviceHelpFragment.newInstance(C0910R.drawable.scaning_bp_device_image_1, "This is to turn on the Blood glucometer scan mode");
        }
        if (position == 1) {
            return DeviceScanFragment.newInstance("Name");
        }
        return null;
    }

    public int getCount() {
        if (this.deviceType == DeviceType.IHEALTH_BP5_MONITOR || DeviceType.IHEALTH_BG5_MONITOR == this.deviceType) {
            return 2;
        }
        return 4;
    }
}
