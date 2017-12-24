package com.github.mikephil.charting.interfaces;

import com.github.mikephil.charting.data.BarData;

public interface BarDataProvider extends BarLineScatterCandleBubbleDataProvider {
    BarData getBarData();

    boolean isDrawBarShadowEnabled();

    boolean isDrawHighlightArrowEnabled();

    boolean isDrawValueAboveBarEnabled();
}
