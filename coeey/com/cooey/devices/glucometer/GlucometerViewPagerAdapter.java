package com.cooey.devices.glucometer;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

public class GlucometerViewPagerAdapter extends FragmentStatePagerAdapter {
    String status = "";

    public void setStatus(String status) {
        this.status = status;
    }

    public GlucometerViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return PlugGlucometerFragment.newInstance(null, null);
            case 1:
                return IntroFragment.newInstance(this.status);
            default:
                return PlugGlucometerFragment.newInstance(null, null);
        }
    }

    public int getCount() {
        return 2;
    }
}
