package com.cooey.common;

import android.databinding.BindingAdapter;
import android.support.design.widget.TabLayout;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.Adapter;
import android.view.View;
import android.widget.TextView;
import com.cooey.common.stores.StyleStore;
import com.cooey.common.views.TimelineItemView;
import com.github.thunder413.datetimeutils.DateTimeUtils;
import java.util.Date;

public class BindingAdapters {
    public static final String GRID = "GRID";
    public static final String HORIZONTAL = "HORIZONTAL";
    public static final String LINEAR = "LINEAR";
    public static final String VERITCAL = "VERITCAL";

    @BindingAdapter({"adapter"})
    public static void onSetViewPager(ViewPager viewPager, PagerAdapter pagerAdapter) {
        viewPager.setAdapter(pagerAdapter);
    }

    @BindingAdapter({"adapter"})
    public static void onSetRecyclerViewAdapter(RecyclerView recyclerView, Adapter adapter) {
        recyclerView.setAdapter(adapter);
    }

    @BindingAdapter({"viewPager"})
    public static void onSetViewPager(TabLayout tabLayout, ViewPager viewPager) {
        tabLayout.setupWithViewPager(viewPager);
    }

    @BindingAdapter({"layoutManager"})
    public static void onSetLayoutManager(RecyclerView recyclerView, String layoutType) {
        if (layoutType.contentEquals("LINEAR")) {
            recyclerView.setLayoutManager(new LinearLayoutManager(recyclerView.getContext()));
        }
        if (layoutType.contentEquals("GRID")) {
            recyclerView.setLayoutManager(new GridLayoutManager(recyclerView.getContext(), 3));
        }
    }

    @BindingAdapter({"divider"})
    public static void onSetDivider(RecyclerView recyclerView, String orientation) {
        if (orientation.contentEquals(VERITCAL)) {
            recyclerView.addItemDecoration(new DividerItemDecoration(recyclerView.getContext(), 1));
        } else {
            recyclerView.addItemDecoration(new DividerItemDecoration(recyclerView.getContext(), 0));
        }
    }

    @BindingAdapter({"font"})
    public static void onSetFont(TextView view, String font) {
        view.setTypeface(new StyleStore(view.getContext()).getFont(font));
    }

    @BindingAdapter({"visible"})
    public static void onSetVisible(View view, boolean visible) {
        if (visible) {
            view.setVisibility(0);
        } else {
            view.setVisibility(8);
        }
    }

    @BindingAdapter({"date"})
    public static void onSetDate(TextView textView, long timestamp) {
        try {
            textView.setText(DateTimeUtils.getTimeAgo(textView.getContext(), new Date(timestamp)));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @BindingAdapter({"time"})
    public static void onSetTime(TextView textView, long timestamp) {
        try {
            textView.setText(DateTimeUtils.getTimeAgo(textView.getContext(), new Date(timestamp)));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @BindingAdapter({"timestamp"})
    public static void onSetTimestamp(TimelineItemView timelineItemView, long timestamp) {
        timelineItemView.setTimestamp(timestamp);
    }
}
