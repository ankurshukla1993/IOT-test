package com.biz.health.cooey_app;

import android.databinding.BindingAdapter;
import android.support.v4.view.ViewPager;
import com.cooey.common.views.VitalsParametersView;
import com.cooey.common.vo.Vital;
import com.github.vivchar.viewpagerindicator.ViewPagerIndicator;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

public class BindingAdapters {
    static DateTimeFormatter builder = DateTimeFormat.forPattern("hh:mm a");
    static DateTimeFormatter longbuilder = DateTimeFormat.forPattern("dd-MM-yyyy hh:mm a");

    @BindingAdapter({"app:pager"})
    public static void onSetCircleIndicatorAdapter(ViewPagerIndicator viewPagerIndicator, ViewPager viewPager) {
        if (viewPager.getAdapter() != null) {
            viewPagerIndicator.setupWithViewPager(viewPager);
        }
    }

    @BindingAdapter({"app:vital"})
    public static void onSetParameters(VitalsParametersView vitalsParametersView, Vital vital) {
        vitalsParametersView.setVital(vital);
    }
}
