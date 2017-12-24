package com.github.mikephil.charting.formatter;

import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.utils.ViewPortHandler;
import java.text.DecimalFormat;

public class LargeValueFormatter implements ValueFormatter, YAxisValueFormatter {
    private static final int MAX_LENGTH = 4;
    private static String[] SUFFIX = new String[]{"", "k", "m", "b", "t"};
    private DecimalFormat mFormat;
    private String mText;

    public LargeValueFormatter() {
        this.mText = "";
        this.mFormat = new DecimalFormat("###E0");
    }

    public LargeValueFormatter(String appendix) {
        this();
        this.mText = appendix;
    }

    public String getFormattedValue(float value, Entry entry, int dataSetIndex, ViewPortHandler viewPortHandler) {
        return makePretty((double) value) + this.mText;
    }

    public String getFormattedValue(float value, YAxis yAxis) {
        return makePretty((double) value) + this.mText;
    }

    public void setAppendix(String appendix) {
        this.mText = appendix;
    }

    public void setSuffix(String[] suff) {
        if (suff.length == 5) {
            SUFFIX = suff;
        }
    }

    private String makePretty(double number) {
        String r = this.mFormat.format(number);
        r = r.replaceAll("E[0-9]", SUFFIX[Character.getNumericValue(r.charAt(r.length() - 1)) / 3]);
        while (true) {
            if (r.length() <= 4 && !r.matches("[0-9]+\\.[a-z]")) {
                return r;
            }
            r = r.substring(0, r.length() - 2) + r.substring(r.length() - 1);
        }
    }
}
