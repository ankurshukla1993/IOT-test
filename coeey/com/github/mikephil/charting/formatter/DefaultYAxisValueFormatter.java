package com.github.mikephil.charting.formatter;

import com.facebook.appevents.AppEventsConstants;
import com.github.mikephil.charting.components.YAxis;
import java.text.DecimalFormat;

public class DefaultYAxisValueFormatter implements YAxisValueFormatter {
    private DecimalFormat mFormat;

    public DefaultYAxisValueFormatter(int digits) {
        StringBuffer b = new StringBuffer();
        for (int i = 0; i < digits; i++) {
            if (i == 0) {
                b.append(".");
            }
            b.append(AppEventsConstants.EVENT_PARAM_VALUE_NO);
        }
        this.mFormat = new DecimalFormat("###,###,###,##0" + b.toString());
    }

    public String getFormattedValue(float value, YAxis yAxis) {
        return this.mFormat.format((double) value);
    }
}
