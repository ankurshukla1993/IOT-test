package com.cooey.android.utils.binding;

import android.databinding.BindingAdapter;
import android.graphics.Typeface;
import android.support.design.widget.TabLayout;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.Adapter;
import android.widget.TextView;
import humanize.Humanize;
import java.util.Date;

public class BindingAdapters {
    public static final String GRID = "GRID";
    public static final String LINEAR = "LINEAR";

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

    @BindingAdapter({"font"})
    public static void onSetFont(TextView view, String fontFile) {
        view.setTypeface(Typeface.createFromAsset(view.getContext().getApplicationContext().getAssets(), "fonts/" + fontFile));
    }

    @BindingAdapter({"date"})
    public static void onSetDate(TextView textView, long timestamp) {
        try {
            textView.setText(Humanize.naturalTime(new Date(timestamp)));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
