package com.biz.health.cooey_app.dashboard.history;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import com.biz.health.cooey_app.C0644R;
import com.cooey.common.vo.User;

public class HistoryViewPagerAdapter extends FragmentStatePagerAdapter {
    private Context context;
    private User user;

    public HistoryViewPagerAdapter(Context context, FragmentManager fm, User user) {
        super(fm);
        this.user = user;
        this.context = context;
    }

    public Fragment getItem(int position) {
        return null;
    }

    public int getCount() {
        return 2;
    }

    public CharSequence getPageTitle(int position) {
        if (position == 0) {
            return this.context.getResources().getString(C0644R.string.timeline);
        }
        if (position == 1) {
            return this.context.getResources().getString(C0644R.string.graphs);
        }
        return this.context.getResources().getString(C0644R.string.graphs);
    }
}
